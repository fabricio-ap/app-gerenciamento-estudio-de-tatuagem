package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente {

    private Conexao conexao;
    private SQLiteDatabase db;

    public DaoCliente (Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("idade", cliente.getIdade());
        values.put("genero", cliente.getGenero());
        values.put("obs", cliente.getObs());
        values.put("detalhestattoo", cliente.getDetalhesTattoo());

        return db.insert("cliente", null, values);
    }

    public List<Cliente> listar(Cliente cliente) {
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from cliente where nome = ?", new String[]{String.valueOf(cliente.getNome())});

        while (cursor.moveToNext()) {
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setIdade(cursor.getString(2));
            cliente.setGenero(cursor.getString(3));
            cliente.setObs(cursor.getString(4));
            cliente.setDetalhesTattoo(cursor.getString(5));

            clientes.add(cliente);
        }

        return clientes;
    }

    public List<Cliente> listar_params() {
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = db.query("cliente", new String[]{"id", "nome", "idade", "genero", "obs", "detalhestattoo"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente(0, "", "", "", "", "");

            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setIdade(cursor.getString(2));
            cliente.setGenero(cursor.getString(3));
            cliente.setObs(cursor.getString(4));
            cliente.setDetalhesTattoo(cursor.getString(5));

            clientes.add(cliente);
        }

        return clientes;
    }

    public void excluir(Cliente cliente) {
        db.delete("cliente", "id = ?", new String[]{String.valueOf(cliente.getId())});
    }

    public void atualizar(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("idade", Integer.parseInt(cliente.getIdade()));
        values.put("genero", cliente.getGenero());
        values.put("obs", cliente.getObs());
        values.put("detalhestattoo", cliente.getDetalhesTattoo());

        System.out.println(db.update("cliente", values, "id = ?", new String[]{String.valueOf(cliente.getId())}));
    }

    public Cliente buscar(Cliente cliente) {
        Cursor cursor = db.rawQuery("select * from cliente where id = ?", new String[]{String.valueOf(cliente.getId())});

        if (cursor.moveToNext()) {
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setIdade(cursor.getString(2));
            cliente.setGenero(cursor.getString(3));
            cliente.setObs(cursor.getString(4));
            cliente.setDetalhesTattoo(cursor.getString(5));
        }

        return cliente;
    }

}
