package com.example.proyectoplanificador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {

    EditText nombre;
    TextView hora;
    Button eliminar;
    String f, usuario;
    BaseDatos BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        nombre = findViewById(R.id.ElimNom);
        hora = findViewById(R.id.HoraElim);
        eliminar = findViewById(R.id.Eliminar);
        BD = new BaseDatos(this);
        Bundle bundle = this.getIntent().getExtras();
        f = bundle.getString("fecha");
        usuario = bundle.getString("user");
        hora.setText(f);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();

                Boolean eliminacion = BD.eliminarAct(nom,f,usuario);
                if (eliminacion==true){
                    Toast.makeText(Eliminar.this, "Eliminacion fallida!", Toast.LENGTH_SHORT) .show();
                    nombre.getText().clear();
                } else {
                    Toast.makeText(Eliminar.this, "Eliminacion realizada!", Toast.LENGTH_SHORT) .show();
                    nombre.getText().clear();
                }
            }
        });

    }
}