package com.example.proyectoplanificador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Eventos extends AppCompatActivity implements View.OnClickListener {

    TextView msj, hora;
    EditText nombre;
    Button registrar, consultar, eliminar, modificar;
    String f;
    BaseDatos BD;
    String elUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        nombre = findViewById(R.id.NomAct);
        hora = findViewById(R.id.Hora);
        msj =findViewById(R.id.date);
        registrar = findViewById(R.id.RegAct);
        consultar = findViewById(R.id.ConAct);
        eliminar = findViewById(R.id.ElimAct);
        modificar = findViewById(R.id.ModAct);
        BD = new BaseDatos(this);
        Bundle bundle = this.getIntent().getExtras();
        f = bundle.getString("fecha");
        elUsuario = bundle.getString("user");
        msj.setText(f);
        hora.setOnClickListener(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();
                String time = hora.getText().toString();

                Boolean insert = BD.insertarAct(nom,f,time,elUsuario);
                if (insert==true){
                    Toast.makeText(Eventos.this, "Registro realizado!", Toast.LENGTH_SHORT) .show();
                    nombre.getText().clear();
                    hora.setText("Hora de la actividad");
                } else {
                    Toast.makeText(Eventos.this, "Registro fallido!", Toast.LENGTH_SHORT) .show();
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaEventos.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("fecha", f);
                bundle1.putString("user", elUsuario);
                intent.putExtras(bundle1);
                startActivity(intent);
                nombre.getText().clear();
                hora.setText("Hora de la actividad");
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Eliminar.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("fecha", f);
                bundle2.putString("user", elUsuario);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==hora){
            final Calendar cal = Calendar.getInstance();
            int horas = cal.get(Calendar.HOUR_OF_DAY);
            int minutos = cal.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora.setText(hourOfDay+":"+minute);
                }
            },horas,minutos,false);
            timePickerDialog.show();
        }
    }
}