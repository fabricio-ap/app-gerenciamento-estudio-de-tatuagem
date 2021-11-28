package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

    private Conexao conexao;
    private SQLiteDatabase db;

    public DaoUsuario (Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("status", usuario.getStatus());
        values.put("tipo", usuario.getTipo());

        return db.insert("usuario", null, values);
    }

    public List<Usuario> listar(Usuario usuario) {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from usuario where login = ?", new String[]{String.valueOf(usuario.getLogin())});

        while (cursor.moveToNext()) {
            usuario.setId(cursor.getInt(0));
            usuario.setLogin(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setStatus(cursor.getString(3));
            usuario.setTipo(cursor.getString(4));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public List<Usuario> listar_params() {
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

    public void excluir(Usuario usuario) {
        db.delete("usuario", "id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public void atualizar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("status", usuario.getStatus());
        values.put("tipo", usuario.getTipo());

        db.update("usuario", values, "id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public Usuario buscar(Usuario usuario) {
        Cursor cursor = db.rawQuery("select * from usuario where id = ?", new String[]{String.valueOf(usuario.getId())});

        if (cursor.moveToNext()) {
            usuario.setId(cursor.getInt(0));
            usuario.setLogin(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setStatus(cursor.getString(3));
            usuario.setTipo(cursor.getString(4));
        }

        return usuario;
    }

    public Usuario valida_login(Usuario usuario) {
        Cursor cursor = db.rawQuery("select * from usuario where login = ? and senha = ?", new String[]{usuario.getLogin(), usuario.getSenha()});

        Usuario retorno = null;

        if (cursor.moveToNext()) {
            retorno = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }

        return retorno;
    }

}
