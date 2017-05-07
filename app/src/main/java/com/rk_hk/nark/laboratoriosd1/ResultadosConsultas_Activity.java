package com.rk_hk.nark.laboratoriosd1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rk_hk.nark.laboratoriosd1.Modelo.Consulta;

import java.util.ArrayList;

public class ResultadosConsultas_Activity extends AppCompatActivity implements View.OnClickListener {
    ListView lvdatos;
    ArrayList<Consulta> lista;
    Button btvolver;
    AdaptadorConsulta adaptadorConsulta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultadosconsultas_vista);
        lvdatos=(ListView)findViewById(R.id.lvdatos);
        /////////////////////////

        ////////////////////////
        lista=new ArrayList<>();
        Consulta c1=new Consulta("peru","21321");
        Consulta c2=new Consulta("Bolivia","2324321");
        lista.add(c1);
        lista.add(c2);
        ///////////////////////////
        adaptadorConsulta=new AdaptadorConsulta(this);
        lvdatos.setAdapter(adaptadorConsulta);
        /////////////////////////////
        btvolver=(Button)findViewById(R.id.btvolver);
        btvolver.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btvolver){
            Intent intent=new Intent(ResultadosConsultas_Activity.this,Consultas_Activity.class);
            startActivity(intent);
        }
    }

    class AdaptadorConsulta extends ArrayAdapter<Consulta> {

        AppCompatActivity appCompatActivity;

        AdaptadorConsulta(AppCompatActivity context) {
            super(context, R.layout.resultadosconsultaitem,lista);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.resultadosconsultaitem, null);
            //LOS TEXTVIEW SOLO ACEPTAN STRING
            TextView tv1=(TextView)item.findViewById(R.id.tv1);
            tv1.setText(lista.get(position).getPais());

            TextView tv2=(TextView)item.findViewById(R.id.tv2);
            tv2.setText(lista.get(position).getSerie());

            return(item);
        }
    }

}
