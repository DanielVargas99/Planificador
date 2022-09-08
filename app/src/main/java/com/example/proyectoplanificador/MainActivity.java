package com.example.proyectoplanificador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    TextView registrar;
    Button inicioS;
    BaseDatos BD;
    String users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.Username);
        contraseña = findViewById(R.id.Password);
        registrar = findViewById(R.id.BotonRg);
        inicioS = findViewById(R.id.BotonIS);
        BD = new BaseDatos(this);

        inicioS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = usuario.getText().toString();
                String pass = contraseña.getText().toString();

                if (users.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Ingrese todos los campos!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean chequearUserPass = BD.chequearUsuarioContraseña(users, pass);
                    if (chequearUserPass==true){
                        Toast.makeText(MainActivity.this, "Inicio exitoso!", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getApplicationContext(), PagPrincipal.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("user", users);
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                        usuario.getText().clear();
                        contraseña.getText().clear();
                    } else {
                        Toast.makeText(MainActivity.this, "Datos invalidos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarActivity.class);
                startActivity(intent);
                usuario.getText().clear();
                contraseña.getText().clear();
            }
        });
    }
}