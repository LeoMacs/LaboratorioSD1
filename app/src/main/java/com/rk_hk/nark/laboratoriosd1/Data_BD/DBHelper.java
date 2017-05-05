package com.rk_hk.nark.laboratoriosd1.Data_BD;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.rk_hk.nark.laboratoriosd1.MainActivity;

import java.io.*;
import java.util.ArrayList;

import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.*;

/**
 * Created by nark_ on 02/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final Context MY_CONTEXT;
    private SQLiteDatabase MY_DATA_BASES;
    private static DBHelper DBInstance = null;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
        MY_CONTEXT=context;
        try{
            crearDataBase();
            openDataBases();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static DBHelper instance(Context myContext) {

        if (DBInstance == null) {
            DBInstance = new DBHelper(myContext);
        }
        return DBInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void crearDataBase() throws IOException{
        boolean dbExists = checkDataBase();
        SQLiteDatabase db_read = null;

        if (dbExists){

        }else{
            db_read = this.getReadableDatabase();
            db_read.close();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("Ptm fallo otra vez");
            }
        }
    }

    public void copyDataBase() throws  IOException{
        try {
            InputStream myInputStream = MY_CONTEXT.getAssets().open(DB_NAME);
            String outFileName = DB_PATH+DB_NAME;
            OutputStream myOutputStream = new FileOutputStream(outFileName);
            byte[] buffer =  new byte[1024];
            int length;
            while ((length=myInputStream.read(buffer))>0){
                myOutputStream.write(buffer,0,length);
            }

            myOutputStream.flush();
            myOutputStream.close();
            myInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDataBases(){
        String myPath = DB_PATH+DB_NAME;
        MY_DATA_BASES = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
    }

    public boolean checkDataBase(){
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
