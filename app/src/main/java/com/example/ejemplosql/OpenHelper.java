package com.example.ejemplosql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper  extends SQLiteOpenHelper {

    public OpenHelper (Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)  {
        super(context ,nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      //Crear Tabla
        db.execSQL("CREATE TABLE clientes (idCliente integer primary key ,nombre text ,telefono text ,direccion text );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS clientes");
    db.execSQL("CREATE TABLE clientes(idCliente integer primary key ,nombre text ,telefono text ,direccion text );");
    }
}
