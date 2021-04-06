package com.example.aplicacionbancaria.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.aplicacionbancaria.usuario.DatosUsuario;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context,"BDusuario.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create TABLE DatosUsuario (nombre TEXT primary key, apellido TEXT, edad INT, correo TEXT)");
        db.execSQL("create TABLE DatosUsuario (documento TEXT PRIMARY KEY, cuenta TEXT,nombre TEXT, " +
                "email TEXT, pass TEXT, saldo Double, status TEXT, tipo TEXT);");
        db.execSQL("INSERT INTO DatosUsuario (documento,cuenta,nombre,email,pass,saldo,status,tipo) VALUES " +
                "(1234567890, 0000000000, 'Administrador','admin@wposs.com','Admin123*','0','Activo','Admin');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE if exists DatosUsuario");
    }

    public Boolean registrar (DatosUsuario datos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento",datos.getDocumento());
        contentValues.put("cuenta",datos.getCuenta());
        contentValues.put("nombre",datos.getNombres());
        contentValues.put("email",datos.getEmail());
        contentValues.put("pass",datos.getPass());
        contentValues.put("saldo",datos.getSaldo());
        contentValues.put("status", datos.getStatus());
        contentValues.put("tipo", datos.getTipo());
        System.out.println(contentValues.getAsString("documento"));
        try {
            db.insert("DatosUsuario", null, contentValues);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }





    public Bundle verificarUsuario(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from DatosUsuario WHERE email = '"+email+"' AND pass = '"+pass+"'",null);
        Bundle datosP = new Bundle();


        if(cursor.moveToFirst()){
            datosP.putString("documento", cursor.getString(0));
            datosP.putString("cuenta", cursor.getString(1));
            datosP.putString("nombre", cursor.getString(2));
            datosP.putString("email", cursor.getString(3));
            datosP.putString("pass", cursor.getString(4));
            datosP.putDouble("saldo", cursor.getDouble(5));
            datosP.putString("status", cursor.getString(6));
            datosP.putString("tipo", cursor.getString(7));
        }
        return datosP;
    }

    public Bundle retirarSaldo(Long cuenta, Double saldo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("update DatosUsuario set saldo='"+saldo+"' WHERE cuenta='"+cuenta+"'",null);
        //Cursor cursor2 = db.rawQuery("Select * from DatosUsuario WHERE cuenta = '"+cuenta+"'",null);
        Bundle datosP = new Bundle();

        if(cursor.moveToFirst()){
            datosP.putString("documento", cursor.getString(0));
            datosP.putString("cuenta", cursor.getString(1));
            datosP.putString("nombre", cursor.getString(2));
            datosP.putString("email", cursor.getString(3));
            datosP.putString("pass", cursor.getString(4));
            datosP.putDouble("saldo", cursor.getDouble(5));
            datosP.putString("status", cursor.getString(6));
            datosP.putString("tipo", cursor.getString(7));
        }
        return datosP;
    }

    public Bundle verificarSaldo(Long cuenta, Double saldo){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("update DatosUsuario set saldo='"+saldo+"' WHERE cuenta='"+cuenta+"'",null);
        Cursor cursor2 = db.rawQuery("Select * from DatosUsuario WHERE cuenta = '"+cuenta+"'",null);
        Bundle datosP = new Bundle();

        if(cursor2.moveToFirst()){
            datosP.putString("documento", cursor2.getString(0));
            datosP.putString("cuenta", cursor2.getString(1));
            datosP.putString("nombre", cursor2.getString(2));
            datosP.putString("email", cursor2.getString(3));
            datosP.putString("pass", cursor2.getString(4));
            datosP.putDouble("saldo", cursor2.getDouble(5));
            datosP.putString("status", cursor2.getString(6));
            datosP.putString("tipo", cursor2.getString(7));
        }
        return datosP;
    }




}
