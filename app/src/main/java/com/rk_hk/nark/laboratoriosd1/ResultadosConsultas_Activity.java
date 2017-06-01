package com.rk_hk.nark.laboratoriosd1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rk_hk.nark.laboratoriosd1.Modelo.Resultado;

import java.util.ArrayList;

public class ResultadosConsultas_Activity extends AppCompatActivity implements View.OnClickListener{

    //TextView tvdatos;
    ArrayList<Resultado> lista;
    Button btvolver;
    ListView lvdatos;
    AdaptadorResultado adaptador;
    TextView tvindice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultadosconsultas_vista);

        tvindice=(TextView)findViewById(R.id.tvindice);
        //tvdatos=(TextView)findViewById(R.id.tvdatos);
        btvolver=(Button)findViewById(R.id.btvolver);
        btvolver.setOnClickListener(this);

        lvdatos=(ListView)findViewById(R.id.lvdatos);
        Intent intent=getIntent();
        Bundle extra=intent.getExtras();
        if (extra!=null){
            String dato=extra.getString("dato");
            lista=(ArrayList<Resultado>)getIntent().getSerializableExtra("lista");
            tvindice.setText(extra.getString("nombreserie"));
            //String dato=llenarCampoa(lista);
            //tvdatos.setText(dato);
        }

        adaptador=new AdaptadorResultado(this);
        lvdatos.setAdapter(adaptador);



    }

//

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btvolver){
            //Intent intent = new Intent(ResultadosConsultas_Activity.this,Consultas_Activity.class);
            //startActivity(intent);
            finish();
        }
    }

    class AdaptadorResultado extends ArrayAdapter<Resultado> {

        AppCompatActivity appCompatActivity;

        AdaptadorResultado(AppCompatActivity context) {
            super(context, R.layout.resultadosconsultaitem,lista);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.resultadosconsultaitem, null);
            //LOS TEXTVIEW SOLO ACEPTAN STRING
            TextView tvpais=(TextView)item.findViewById(R.id.tvpais);
            tvpais.setText(lista.get(position).getCodpais());

            TextView tvserie=(TextView)item.findViewById(R.id.tvserie);
            tvserie.setText(lista.get(position).getCodserie());

            TextView tvano=(TextView)item.findViewById(R.id.tvano);
            tvano.setText(Integer.toString(lista.get(position).getAños()));

            TextView tvind=(TextView)item.findViewById(R.id.tvind);
            tvind.setText(lista.get(position).getIndices());

            return(item);
        }
    }


}
