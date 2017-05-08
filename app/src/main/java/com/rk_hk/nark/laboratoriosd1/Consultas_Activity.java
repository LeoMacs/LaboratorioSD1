package com.rk_hk.nark.laboratoriosd1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.rk_hk.nark.laboratoriosd1.Data_BD.DBHelper;

import java.util.ArrayList;

import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.LSCOD_ANIO;
import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.LS_COD_SERIES;
import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.LS_CONT_SERIES;
import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.lsCodPais;

public class Consultas_Activity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    ListView lvdatos;
    private Button btConsultar;
    private Spinner spinner;

    private CheckBox[] arrCheck= new CheckBox[11];
    RangeBar rangeBar ;

    private DBHelper dbHelper ;

    private String serie_select;
    private ArrayList<String>  country_select;
    private String[]  years_select;
    private ArrayList<ArrayList<String>> lsResult_Consulta;
    private int limInf, limSup;
    private int tamArrayYears;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultas_vistas);

        dbHelper= DBHelper.instance(this);

        spinner = (Spinner) findViewById(R.id.id_cv_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,LS_CONT_SERIES);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        arrCheck[0] = (CheckBox) findViewById(R.id.id_cv_arg);
        arrCheck[1] = (CheckBox) findViewById(R.id.id_cv_bol);
        arrCheck[2] = (CheckBox) findViewById(R.id.id_cv_bra);
        arrCheck[3] = (CheckBox) findViewById(R.id.id_cv_chi);
        arrCheck[4] = (CheckBox) findViewById(R.id.id_cv_col);
        arrCheck[5] = (CheckBox) findViewById(R.id.id_cv_ecu);
        arrCheck[6] = (CheckBox) findViewById(R.id.id_cv_pan);
        arrCheck[7] = (CheckBox) findViewById(R.id.id_cv_per);
        arrCheck[8] = (CheckBox) findViewById(R.id.id_cv_par);
        arrCheck[9] = (CheckBox) findViewById(R.id.id_cv_uru);
        arrCheck[10] = (CheckBox) findViewById(R.id.id_cv_ven);

        rangeBar = (RangeBar) findViewById(R.id.rangebar);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                limInf=leftPinIndex;limSup=rightPinIndex;
                tamArrayYears=(rightPinIndex-leftPinIndex)+1;
                System.out.println("LIMITES : [ "+limInf+" - "+limSup+" ] ->>> TAM :"+ tamArrayYears);
            }
        });




        /**
         * Aqui pones tu logica de captura de serie , paises y años.
         */


        btConsultar=(Button)findViewById(R.id.btConsultar);
        lsResult_Consulta = new ArrayList<>();

        btConsultar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btConsultar:
                /**
                 * Inicializaremos los parametros necesarios, cuando presionemos el boton
                 */
                country_select = crearArregloPaises();

                years_select=crearArregloAnios();

                /**
                 * Verificamos si todos los paremetros tienen algun valor para proceder hacer la consulta
                 */
                if(country_select!=null && serie_select!= null && years_select!=null){
                    lsResult_Consulta=dbHelper.ConsultarBD_Series(country_select,serie_select,years_select);
                    mostrarConsulta();
                }

                else
                    Toast.makeText(this, "Seleccione los Campos", Toast.LENGTH_SHORT).show();break;

        }
    }

    public void mostrarConsulta(){
        String cadena = "Serie";
        ArrayList<String> fila = new ArrayList<>();
        for (int i = 0; i < lsResult_Consulta.size();i++){
            fila=lsResult_Consulta.get(i);
            cadena = cadena + "FILA "+i+" : ";
            for (int j = 0 ;j <fila.size();j++ ){
                cadena = cadena+ " "+fila.get(j);
            }
            cadena = cadena + "\n";
        }
        System.out.println(cadena);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        serie_select = LS_COD_SERIES.get(position);
        Toast.makeText(this, serie_select, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public  String[] crearArregloAnios(){
        String[] years = new String[tamArrayYears];
        for (int i=0 ; i<tamArrayYears;i++){
            years[i]=LSCOD_ANIO[limInf+i];
            System.out.println("ANIOS -> "+years[i]);
        }

        return years;
    }
    public ArrayList<String> crearArregloPaises(){
        ArrayList<String> paises = new ArrayList<>();
        for (int i=0;i<arrCheck.length;i++){
            if(arrCheck[i].isChecked()){
                paises.add(lsCodPais[i]);
                System.out.println("PAIS -> "+lsCodPais[i]);
            }
        }
        return paises;
    }
}
