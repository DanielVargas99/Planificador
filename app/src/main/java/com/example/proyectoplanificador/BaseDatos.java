package com.example.proyectoplanificador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    public static final String NombreBD = "proyecto.db";
    public BaseDatos(Context context) {
        super(context, "proyecto.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL("create Table login(usuario TEXT primary key, contraseña TEXT)");
        BD.execSQL("create Table actividades(nombre TEXT primary key, fecha TEXT, hora TEXT, nombreUser TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int i, int i1) {
        BD.execSQL("drop Table if exists login");
        BD.execSQL("drop Table if exists actividades");
    }

    public Boolean insertarDatos(String usuario, String contraseña){
        SQLiteDatabase BD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", usuario);
        contentValues.put("contraseña", contraseña);
        long resultado = BD.insert("login"  , null, contentValues);
        if (resultado==1) return false;
        else
            return true;
    }

    public Boolean insertarAct(String nombre, String fecha, String hora, String usuario){
        SQLiteDatabase BD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("fecha", fecha);
        contentValues.put("hora", hora);
        contentValues.put("nombreUser", usuario);
        long consulta = BD.insert("actividades", null, contentValues);
        if (consulta==1) return false;
        else
            return true;
    }

    public ArrayList consultarAct(String fecha, String usuario){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase BD = this.getWritableDatabase();
        Cursor cursor = BD.rawQuery("Select * from actividades where fecha = ? and nombreUser = ?",new String[]{fecha,usuario});
        if (cursor.moveToFirst()){
            do {
                lista.add(cursor.getString(0) + " - " + cursor.getString(2));
            } while(cursor.moveToNext());
        }
        return lista;
    }

    public Boolean eliminarAct(String nombreAct, String fecha, String usuario){
        SQLiteDatabase BD =this.getWritableDatabase();
        Cursor cursor = BD.rawQuery("Delete from actividades where nombre = ? and fecha = ? and nombreUser = ?", new String[]{nombreAct,fecha,usuario});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean chequearUsuario(String usuario){
        SQLiteDatabase BD = this.getWritableDatabase();
        Cursor cursor = BD.rawQuery("Select * from login where usuario = ?", new String[] {usuario});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean chequearUsuarioContraseña(String usuario, String contraseña){
        SQLiteDatabase BD = this.getWritableDatabase();
        Cursor cursor = BD.rawQuery("Select * from login where usuario = ? and contraseña = ?", new String[] {usuario,contraseña});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
