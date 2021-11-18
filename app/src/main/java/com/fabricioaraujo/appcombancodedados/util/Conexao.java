package com.fabricioaraujo.appcombancodedados.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String db_name = "banco.db";
    private static final int db_version = 1;

    public Conexao(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table usuario (" +
                        "id integer primary key autoincrement," +
                        "login varchar(50)," +
                        "senha varchar(50)," +
                        "status varchar(50)," +
                        "tipo varchar(50)" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
