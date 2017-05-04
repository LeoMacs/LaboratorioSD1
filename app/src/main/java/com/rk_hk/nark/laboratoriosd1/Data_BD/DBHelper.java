package com.rk_hk.nark.laboratoriosd1.Data_BD;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.*;
import java.util.ArrayList;

import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.*;

/**
 * Created by nark_ on 02/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final Context MY_CONTEXT;
    private SQLiteDatabase MY_DATA_BASES;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
        MY_CONTEXT=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(QUERY_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = MY_CONTEXT.getAssets().open("MiPrimeraBD");
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        MY_DATA_BASES = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (MY_DATA_BASES != null)
            MY_DATA_BASES.close();

        super.close();
    }

    public  ArrayList<ArrayList<String>> ConsultarBD(String cod_pais, String[] cod_series, String[] anios){
        ArrayList<ArrayList<String>> lsresultadoTotal = new ArrayList<>();
        int cant_cols = cod_series.length+anios.length;
        System.out.println("CANT_COLS:"+cant_cols);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> lsresult_unit;
        String consulta = Consultar_x_años(cod_pais,cod_series,anios);
        System.out.println(consulta);

        if(db!= null){
            Cursor c = db.rawQuery(consulta,null);
            if(c.moveToFirst()){
                do{
                    lsresult_unit = new ArrayList<>();
                    for(int j =0 ;j<cant_cols;j++){
                        lsresult_unit.add(c.getString(j));
                    }
                    lsresultadoTotal.add(lsresult_unit);
                }while(c.moveToNext());
            }
        }
        return lsresultadoTotal;
    }

}
