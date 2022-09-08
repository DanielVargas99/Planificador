package com.example.proyectoplanificador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    EditText nombre, usuario, contraseña, confirmarContraseña;
    Button registrarDatos;
    BaseDatos DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre = findViewById(R.id.Nombre);
        usuario = findViewById(R.id.Username1);
        contraseña = findViewById(R.id.Password1);
        confirmarContraseña = findViewById(R.id.RePassword);
        registrarDatos = findViewById(R.id.BotonReg);
        DB = new BaseDatos(this);

        registrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String password = contraseña.getText().toString();
                String repass = confirmarContraseña.getText().toString();

                if (user.equals("")||password.equals("")||repass.equals(""))
                    Toast.makeText(RegistrarActivity.this, "Ingrese todos los campos!", Toast.LENGTH_SHORT).show();
                else{
                    if (password.equals(repass)){
                        Boolean chequearUser = DB.chequearUsuario(user);
                        if (chequearUser==false){
                            Boolean insertar = DB.insertarDatos(user,password);
                            if (insertar==true){
                                Toast.makeText(RegistrarActivity.this, "Registro realizado!", Toast.LENGTH_SHORT) .show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrarActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrarActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}