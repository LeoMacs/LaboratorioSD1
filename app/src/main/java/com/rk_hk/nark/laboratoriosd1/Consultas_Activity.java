package com.rk_hk.nark.laboratoriosd1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Consultas_Activity extends AppCompatActivity implements View.OnClickListener{
    ListView lvdatos;
    Button btConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultas_vistas);
        btConsultar=(Button)findViewById(R.id.btConsultar);
        btConsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btConsultar){
            Intent intent=new Intent(Consultas_Activity.this,ResultadosConsultas_Activity.class);
            startActivity(intent);



        }

    }
}
