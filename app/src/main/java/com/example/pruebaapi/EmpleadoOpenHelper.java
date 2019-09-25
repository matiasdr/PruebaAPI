package com.example.pruebaapi;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmpleadoOpenHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_TABLA="empleados";
    private String sql= "CREATE TABLE " + this.NOMBRE_TABLA + " (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT NOT NULL, nombre TEXT, domicilio TEXT, telefono TEXT, email TEXT, habilitado INT," +
            " password TEXT)";


    // id, nombre, domicilio, telefono, email, habilitado(int), password

    public EmpleadoOpenHelper(Context context){
        super(context, "ites.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ this.NOMBRE_TABLA);
        db.execSQL(sql);
    }
}
