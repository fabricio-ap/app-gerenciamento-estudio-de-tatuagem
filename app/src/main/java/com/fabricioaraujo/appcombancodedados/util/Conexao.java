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
        // Tabela de Usuários
        db.execSQL(
                "create table usuario (" +
                        "id integer primary key autoincrement," +
                        "login varchar(50)," +
                        "senha varchar(50)," +
                        "status varchar(50)," +
                        "tipo varchar(50)" +
                ")"
        );

        // Tabela de Clientes
        db.execSQL(
                "create table cliente (\n" +
                        "id integer primary key autoincrement," +
                        "nome varchar(255)," +
                        "idade int," +
                        "genero varchar(255)," +
                        "obs varchar(255)," +
                        "detalhestattoo varchar(255)" +
                ")"
        );

        // Tabela de Tatuadores
        db.execSQL(
                "create table tatuador (" +
                        "id integer primary key autoincrement," +
                        "nome varchar(255)," +
                        "idade int," +
                        "genero varchar(255)," +
                        "especialidade varchar(255)," +
                        "horario varchar(255)" +
                ")"
        );

        // Tabela da Agenda
        db.execSQL(
                "create table agenda (" +
                        "id integer primary key autoincrement," +
                        "id_cliente integer," +
                        "id_tatuador int," +
                        "horario varchar(255)," +
                        "valor varchar(255)," +

                        "FOREIGN KEY (id_cliente) REFERENCES cliente(id)," +
                        "FOREIGN KEY (id_tatuador) REFERENCES tatuador(id)" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
