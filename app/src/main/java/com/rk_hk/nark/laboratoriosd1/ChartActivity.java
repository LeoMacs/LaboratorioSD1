package com.rk_hk.nark.laboratoriosd1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Random;

import static com.rk_hk.nark.laboratoriosd1.Consultas_Activity.years_select;
import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.RESULTADO_CONSULTA;

public class ChartActivity extends AppCompatActivity {

    float barWidth= 0.3f;
    float barSpace = 0f;
    float groupSpace = 0.4f;

    private ArrayList<ArrayList<String>> resultConsulta;

    private BarChart chart;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "No se que pasa", Toast.LENGTH_SHORT).show();

        resultConsulta = RESULTADO_CONSULTA;

        chart = (BarChart) findViewById(R.id.myBarChart);
        BarData data = new BarData(getDataSet());
        chart.setData(data);
        Description description = new Description();
        description.setText("Probando Chart");
        chart.setDescription(description);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        for(int j = 0 ; j<resultConsulta.size();j++) {
            ArrayList<String> xColumna = resultConsulta.get(j);
            ArrayList<BarEntry> valxPais = new ArrayList<>();
            for (int i = 2; i < xColumna.size(); i++) {
                System.out.print(xColumna.get(i) + " - ");
                BarEntry val_fecha = new BarEntry((Float.parseFloat(xColumna.get(i))), i-2);
                valxPais.add(val_fecha);
            }
            System.out.println("\n");

            BarDataSet dataxPais = new BarDataSet(valxPais, xColumna.get(0));
            dataxPais.setColor(randomColor());
            dataSets.add(dataxPais);
        }
        return dataSets;

    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> YEARS_SELECT = new ArrayList<>();
        for(int i = 0 ; i<years_select.length;i++)
            YEARS_SELECT.add(years_select[i]);
        return YEARS_SELECT;
    }

    private int randomColor(){
        Random rand = new Random();
        int R = rand.nextInt(256);
        int G = rand.nextInt(256);
        int B= rand.nextInt(256);
       return  Color.rgb(R,G,B);
    }
}
