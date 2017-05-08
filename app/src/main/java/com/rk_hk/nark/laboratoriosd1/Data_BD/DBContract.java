package com.rk_hk.nark.laboratoriosd1.Data_BD;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Arrays;

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
        public static int DB_SCHEMA_VERSION = 1;

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
         * Codigos de Paises
         */

        public static final String[] LSCOD_ANIO = {"yr2005", "yr2006", "yr2007", "yr2008", "yr2009", "yr2010", "yr2011", "yr2012","yr2013", "yr2014", "yr2015"};

        public static final String[] lsCodPais = {"ARG", "BRA", "BOL", "CHL", "COL", "ECU", "PER", "PRY", "PAN", "URY", "VEN"};

        public static final String FIND_COD_PAIS(String op) {
            switch (op) {
                case "ARG" : return  "Argentina";
                case "BRA" : return  "Brasil";
                case "BOL" : return  "Bolivia";
                case "CHL" : return  "Chile";
                case "COL" : return  "Colombia";
                case "ECU" : return  "Ecuador";
                case "PER" : return  "Perù";
                case "PRY" : return  "Paraguay";
                case "PAN" : return  "Panamà";
                case "URY" : return  "Uruguay";
                case "VEN" : return  "Venezuela";
                default: return "";
            }
        }
        /**
         * Codigo de Series
         */

        public static final ArrayList<String> LS_COD_SERIES =new ArrayList<>(Arrays.asList("SH.DYN.AIDS", "SP.DYN.SMAM.MA", "SP.DYN.SMAM.FE", "SH.STA.DIAB.ZS", "SH.MED.BEDS.ZS", "SH.TBS.INCD", "SP.DYN.LE00.IN", "SH.MED.PHYS.ZS"));
        public static final ArrayList<String> LS_CONT_SERIES =new ArrayList<>(Arrays.asList("Personas mayores de 15 años, viviendo con VIH"
                , "Edad promedio de hombres, en su primer matrimonio"
                , "Edad promedio de mujeres, en su primer matrimonio"
                , "Prevalencia de diabetes (%poblacion entre 25 y 79 años"
                , "Camas de hospital (por cada 1000 personas)"
                , "Incidencia de TBC (por cada 1000 personas)"
                , "Expectativa de vida al nacer (en años)"
                , "Doctores (por cada 1000 personas)"));


        public static final String FIND_COD_SERIE(String op){
            switch (op){
                case "SH.DYN.AIDS": return "Personas mayores de 15 años, viviendo con VIH";
                case "SP.DYN.SMAM.MA": return "Edad promedio de hombres, en su primer matrimonio";
                case "SP.DYN.SMAM.FE": return "Edad promedio de mujeres, en su primer matrimonio";
                case "SH.STA.DIAB.ZS": return "Prevalencia de diabetes (%poblacion entre 25 y 79 años";
                case "SH.MED.BEDS.ZS": return "Camas de hospital (por cada 1000 personas)";
                case "SH.TBS.INCD": return "Incidencia de TBC (por cada 1000 personas)";
                case "SP.DYN.LE00.IN": return "Expectativa de vida al nacer (en años)";
                case "SH.MED.PHYS.ZS": return "Doctores (por cada 1000 personas)";
                default: return "";
            }
        }

        /**
         * Consultas preparadas
         */

        public static ArrayList<String> LS_ANIOS = new ArrayList<>();
        public static ArrayList<String> LS_SERIES;
        public static ArrayList<String> LS_PAISES;

        public static String getDbName() {
            return DB_NAME;
        }

        public static ArrayList<String> getLsAnios() {
            return LS_ANIOS;
        }

        public static void setLsAnios(ArrayList<String> lsAnios) {
            LS_ANIOS = lsAnios;
        }

        public static ArrayList<String> getLsSeries() {
            return LS_SERIES;
        }

        public static void setLsSeries(ArrayList<String> lsSeries) {
            LS_SERIES = lsSeries;
        }

        public static ArrayList<String> getLsPaises() {
            return LS_PAISES;
        }

        public static void setLsPaises(ArrayList<String> lsPaises) {
            LS_PAISES = lsPaises;
        }

        public static final String QUERY_CREAR_TABLA= "CREATE TABLE "+TABLE_NAME+" (" +
                ID_TABLA +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                SERIES_CODE +" TEXT NOT NULL,"+
                COUNTRY_CODE +" TEXT NOT NULL, "+
                INF_2005 +" REAL, "+
                INF_2006 +" REAL, "+
                INF_2007 +" REAL, "+
                INF_2008 +" REAL, "+
                INF_2009 +" REAL, "+
                INF_2010 +" REAL, "+
                INF_2011 +" REAL, "+
                INF_2012 +" REAL, "+
                INF_2013 +" REAL, "+
                INF_2014 +" REAL, "+
                INF_2015 +" REAL );";


        public static final String Consultar_x_años_serie(ArrayList<String> cod_pais, String cod_series, String[] anios){
            String consulta ="SELECT "+COUNTRY_CODE+", "+ SERIES_CODE;
            for (int i=0 ; i<anios.length;i++){
                consulta = consulta+", "+anios[i];
            }
            consulta = consulta+" FROM "+ TABLE_NAME+" WHERE "+SERIES_CODE+" = '"+cod_series+"' AND "+COUNTRY_CODE+" IN ('"+cod_pais.get(0)+"' ";
            for (int i = 1 ; i <cod_pais.size();i++){
                consulta = consulta +",'"+cod_pais.get(i)+"' ";
            }
            consulta = consulta + ")";
            System.out.println("DBContract -> "+consulta);
            return consulta;
        }

    }


}
