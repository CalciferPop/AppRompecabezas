package com.example.hugopc.rompecabezas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Ranking16Activity extends AppCompatActivity {

    ListView list_ranking16;
    ArrayList<String> lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking16);
        list_ranking16 = (ListView) findViewById(R.id.listRanking16);

        UsuariosDbHelper objBD = new UsuariosDbHelper(this, "ROMPECABEZAS", null, 1);

        lista = objBD.m_llenar_ranking16();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        list_ranking16.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itm4piezas:
                Intent ListarRanking4 = new Intent(Ranking16Activity.this, Raking4Activity.class);
                Ranking16Activity.this.startActivity(ListarRanking4);
                break;
            case R.id.itm8piezas:
                Intent ListarRanking8 = new Intent(Ranking16Activity.this, Ranking8Activity.class);
                Ranking16Activity.this.startActivity(ListarRanking8);
                break;
            case R.id.itm16piezas:
                Intent ListarRanking16 = new Intent(Ranking16Activity.this, Ranking16Activity.class);
                Ranking16Activity.this.startActivity(ListarRanking16);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
