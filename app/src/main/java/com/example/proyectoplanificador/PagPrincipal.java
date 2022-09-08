package com.example.proyectoplanificador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class PagPrincipal extends AppCompatActivity {

    TextView mensaje;
    CalendarView calendar;
    String fecha;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_principal);
        calendar = findViewById(R.id.Calendario);
        mensaje = findViewById(R.id.Msg);

        Bundle bundle = this.getIntent().getExtras();
        n = bundle.getString("user");

        mensaje.setText("Bienvenido " + n);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(PagPrincipal.this, Eventos.class);
                fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                Bundle bundle = new Bundle();
                bundle.putString("fecha", fecha);
                bundle.putString("user", n);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}