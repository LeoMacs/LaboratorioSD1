package com.rk_hk.nark.laboratoriosd1.Data_BD;

import android.provider.BaseColumns;

/**
 * Created by nark_ on 02/05/2017.
 */

public class DBContract {

    public static abstract class DataQuerysEntry implements BaseColumns{

        /**
         * Nombre de la BASE_DE_DATOS
         */
        public static final String DB_NAME = "laboratorio1.db";
        public static final String DB_PATH = "/data/data/com.rk_hk.nark.laboratoriosd1/databases/";
        public static final int DB_SCHEMA_VERSION = 1;

        /**
         * Nombre de la TABLA
         */
        public static final String TABLE_NAME = "table_data";
        public static final int CANT_COLS = 13;

        /**
         * CAMPOS o ATRIBUTOS de la TABLA
         */

        public static final String ID_TABLA = "id_table";
        public static final String SERIES_CODE = "series_code";
        public static final String COUNTRY_CODE = "country_code";
        public static final String INF_2005 = "yr2005";
        public static final String INF_2006 = "yr2006";
        public static final String INF_2007 = "yr2007";
        public static final String INF_2008 = "yr2008";
        public static final String INF_2009 = "yr2009";
        public static final String INF_2010 = "yr2010";
        public static final String INF_2011 = "yr2011";
        public static final String INF_2012 = "yr2012";
        public static final String INF_2013 = "yr2013";
        public static final String INF_2014 = "yr2014";
        public static final String INF_2015 = "yr2015";

        /**
         * Consultas preparadas
         */

        public static final String QUERY_CREAR_TABLA= "CREATE TABLE "+TABLE_NAME+" (" +
                ID_TABLA +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                SERIES_CODE +" TEXT NOT NULL,"+
                COUNTRY_CODE +" TEXT NOT NULL, "+
                INF_2005 +" REAL, "+
                INF_2006 +" REAL, "+
                INF_2007 +" REAL, "+
                INF_2008 +" REAL, "+
                INF_2009 +" REAL, "+
                INF_2011 +" REAL, "+
                INF_2012 +" REAL, "+
                INF_2013 +" REAL, "+
                INF_2014 +" REAL, "+
                INF_2015 +" REAL );";

        public static final String Consultar_x_años(String cod_pais, String[] cod_series, String[] anios){
            String consulta ="SELECT "+COUNTRY_CODE+", "+ SERIES_CODE;
            for (int i=0 ; i<anios.length;i++){
                consulta = consulta+", "+anios[i];
            }
            consulta = consulta+" FROM "+ TABLE_NAME+" WHERE "+COUNTRY_CODE+" = '"+cod_pais+"' AND "+SERIES_CODE+" = '"+cod_series[0]+"' ";
            for (int i = 1 ; i <cod_series.length;i++){
                consulta = consulta +" OR "+SERIES_CODE+" = '"+cod_series[i]+"' ";
            }
            return consulta;
        }
    }


}
