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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Random;

import static com.rk_hk.nark.laboratoriosd1.Consultas_Activity.years_select;
import static com.rk_hk.nark.laboratoriosd1.Data_BD.DBContract.DataQuerysEntry.RESULTADO_CONSULTA;

public class ChartActivity extends AppCompatActivity {

    float barWidth= 0.3f;
    float barSpace = 0f;
    float groupSpace = 0.8f;

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
        int groupCount = years_select.length;

        chart = (BarChart) findViewById(R.id.myBarChart);
        /*
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
*/

        BarData data = new BarData(getDataSet());
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);

        Description description = new Description();
        description.setText("Probando Chart");
        chart.setDescription(description);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        ModificarLeyenda();
        ModificarEjes(groupCount);
    }

    public void ModificarLeyenda(){
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(20f);
    }

    public void ModificarEjes(int tamMax){

//X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(tamMax);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(etiquetaEjeX()));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinimum(0f);
    }

    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        for(int j = 0 ; j<resultConsulta.size();j++) {
            float k =0;
            ArrayList<String> xColumna = resultConsulta.get(j);
            ArrayList<BarEntry> valxPais = new ArrayList<>();
            for (int i = 2; i < xColumna.size(); i++) {
               // BarEntry val_fecha = new BarEntry((Float.parseFloat(xColumna.get(i))),k);
                valxPais.add( new BarEntry(k,(Float.parseFloat(xColumna.get(i)))));
                k++;
            }
            BarDataSet dataxPais = new BarDataSet(valxPais, xColumna.get(0));
            dataxPais.setColor(randomColores(j));
            dataSets.add(dataxPais);
        }
        return dataSets;

    }

    private ArrayList<String> etiquetaEjeX() {
        ArrayList<String> YEARS_SELECT = new ArrayList<>();
        for(int i = 0 ; i<years_select.length;i++)
            YEARS_SELECT.add(years_select[i]);
        return YEARS_SELECT;
    }

    private int randomColores(int index){
        int[] lsColores = {Color.rgb(0,128,187),
                Color.rgb(139,234,254),
                Color.rgb(69,155,168),
                Color.rgb(145,195,64),
                Color.rgb(253,238,0),
                Color.rgb(255,75,58),
                Color.rgb(255,140,157),
                Color.rgb(138,180,209),
                Color.rgb(197,77,87),
                Color.rgb(85,99,112)};

        return lsColores[index];
    }

    private int randomColor(){
        Random rand = new Random();
        int R = rand.nextInt(256);
        int G = rand.nextInt(256);
        int B= rand.nextInt(256);
       return  Color.rgb(R,G,B);
    }
}
