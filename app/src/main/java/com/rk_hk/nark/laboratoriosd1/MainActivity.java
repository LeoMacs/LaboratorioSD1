package com.rk_hk.nark.laboratoriosd1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rk_hk.nark.laboratoriosd1.Data_BD.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DBHelper dbHelper = new DBHelper(this);
    private String cod_pais = "ARG";
    private String[] cod_series = new String[]{"SH.DYN.AIDS","SH.STA.DIAB.ZS"};
    private String[] anios = new String[]{"yr2007","yr2008","yr2009","yr2010","yr2011","yr2012"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SQLiteDatabase db = dbHelper.getWritableDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ArrayList<String>> resultado = dbHelper.ConsultarBD(cod_pais,cod_series,anios);
                ArrayList<String> fila = resultado.get(0);
                Toast.makeText(MainActivity.this, fila.get(0)+" "+fila.get(1)+" "+fila.get(2)+" "+fila.get(3)+" "+fila.get(4)+" "+fila.get(5)+" "+fila.get(6), Toast.LENGTH_SHORT).show();

                /** Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
