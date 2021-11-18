package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

public class DaoUsuario {

    private Conexao conexao;
    private SQLiteDatabase db;

    public DaoUsuario (Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir_usuario (Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("status", usuario.getStatus());
        values.put("tipo", usuario.getTipo());

        return db.insert("usuario", null, values);
    }

}
