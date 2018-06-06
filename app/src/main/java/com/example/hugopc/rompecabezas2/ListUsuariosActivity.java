package com.example.hugopc.rompecabezas2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListUsuariosActivity extends AppCompatActivity {

    ListView list_usuarios;
    ArrayList<String> lista;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);
        list_usuarios = (ListView) findViewById(R.id.listViewUsuarios);

        UsuariosDbHelper objBD = new UsuariosDbHelper(this,"ROMPECABEZAS",null,1);

        lista = objBD.m_llenar_lista();

        objBD.m_imprimeLista(objBD.m_llenar_lista());
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        list_usuarios.setAdapter(adapter);


    }



}
