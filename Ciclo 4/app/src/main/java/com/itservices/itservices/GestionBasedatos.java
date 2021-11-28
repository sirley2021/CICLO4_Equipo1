package com.itservices.itservices;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestionBasedatos extends SQLiteOpenHelper {

    public GestionBasedatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Usuarios(Identifacion int primary key, Nombres TEXT," +
                "Apellidos TEXT,Email TEXT,Telefono TEXT,Direccion TEXT,Contrasena TEXT,PLANUSER TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
