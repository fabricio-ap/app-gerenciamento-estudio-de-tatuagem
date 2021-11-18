package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

import java.util.ArrayList;
import java.util.List;

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

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = db.query("usuario", new String[]{"id", "login", "senha", "status", "tipo"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario(0, "", "", "", "");

            usuario.setId(cursor.getInt(0));
            usuario.setLogin(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setStatus(cursor.getString(3));
            usuario.setTipo(cursor.getString(4));

            usuarios.add(usuario);
        }

        return usuarios;
    }

}
