package com.example.proyectoplanificador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaEventos extends AppCompatActivity {

    ListView le;
    ArrayList<String> list;
    ArrayAdapter adapter;
    BaseDatos BD;
    String fec;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        le = findViewById(R.id.Lista);
        BD = new BaseDatos(this);
        Bundle bundle = this.getIntent().getExtras();
        fec = bundle.getString("fecha");
        usuario = bundle.getString("user");
        list = BD.consultarAct(fec,usuario);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        le.setAdapter(adapter);
    }
}