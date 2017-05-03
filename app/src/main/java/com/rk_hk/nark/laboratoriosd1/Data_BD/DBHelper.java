package com.rk_hk.nark.laboratoriosd1.Data_BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.*;

/**
 * Created by nark_ on 02/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper{


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  ArrayList<ArrayList<String>> ConsultarBD(String cod_pais, String[] cod_series){
        ArrayList<ArrayList<String>> lsresultadoTotal = new ArrayList<>();


        SQLiteDatabase db = this.getWritableDatabase();
        String consulta ="SELECT * FROM "+ TABLE_NAME+" WHERE "+COUNTRY_CODE+" = '"+cod_pais+"' AND "+SERIES_CODE+" = '"+cod_series[0]+"';";
        ArrayList<String> lsresult_unit;

        for (int i = 1 ; i <cod_series.length;i++){
            consulta = consulta +" OR "+SERIES_CODE+" = "+cod_series[i];
        }

        if(db!= null){
            Cursor c = db.rawQuery(consulta,null);
            if(c.moveToFirst()){
                do{
                    lsresult_unit = new ArrayList<>();
                    for(int j =0 ;j<CANT_COLS;j++){
                        lsresult_unit.add(c.getString(j));
                    }
                    lsresultadoTotal.add(lsresult_unit);
                }while(c.moveToNext());
            }
        }
        return lsresultadoTotal;
    }

}
