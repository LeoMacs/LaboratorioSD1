package com.rk_hk.nark.laboratoriosd1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

public class Consultas_Activity extends AppCompatActivity {
    ListView lvdatos;
    Button btConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultas_vistas);
        btConsultar=(Button)findViewById(R.id.btConsultar);
    }
}