package com.rk_hk.nark.laboratoriosd1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk_hk.nark.laboratoriosd1.Modelo.Resultado;

import java.util.ArrayList;

public class ResultadosConsultas_Activity extends AppCompatActivity implements View.OnClickListener{

    TextView tvdatos;
    ArrayList<Resultado> lista;
    Button btvolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultadosconsultas_vista);

        tvdatos=(TextView)findViewById(R.id.tvdatos);
        btvolver=(Button)findViewById(R.id.btvolver);
        btvolver.setOnClickListener(this);

        //lvdatos=(ListView)findViewById(R.id.lvdatos);
        Intent intent=getIntent();
        Bundle extra=intent.getExtras();
        if (extra!=null){
            //String dato=extra.getString("dato");
            lista=(ArrayList<Resultado>)getIntent().getSerializableExtra("lista");
            String dato=llenarCampoa(lista);
            tvdatos.setText(dato);
        }
    }

    public String llenarCampoa(ArrayList<Resultado> lista){
        String dato="";
        int n=lista.size();
        for (int j=0;j<n;j++){
            int m=lista.get(j).getIndices().size();
            for (int i=0;i<m;i++){
                dato=dato +lista.get(j).getCodpais()+" "+lista.get(j).getCodserie()+" "+lista.get(j).getIndices().get(i)+"\n";
                System.out.println(dato);

            }

        }
        return dato;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btvolver){
            Intent intent = new Intent(ResultadosConsultas_Activity.this,Consultas_Activity.class);
            startActivity(intent);
        }

    }
}
