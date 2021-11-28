package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class DaoTatuador {

    private Conexao conexao;
    private SQLiteDatabase db;

    public DaoTatuador (Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir(Tatuador tatuador) {
        ContentValues values = new ContentValues();
        values.put("nome", tatuador.getNome());
        values.put("idade", tatuador.getIdade());
        values.put("genero", tatuador.getGenero());
        values.put("especialidade", tatuador.getEspecialidade());
        values.put("horario", tatuador.getHorario());

        return db.insert("tatuador", null, values);
    }

    public List<Tatuador> listar(Tatuador tatuador) {
        List<Tatuador> tatuadores = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tatuador where nome = ?", new String[]{String.valueOf(tatuador.getNome())});

        while (cursor.moveToNext()) {
            tatuador.setId(cursor.getInt(0));
            tatuador.setNome(cursor.getString(1));
            tatuador.setIdade(cursor.getString(2));
            tatuador.setGenero(cursor.getString(3));
            tatuador.setEspecialidade(cursor.getString(4));
            tatuador.setHorario(cursor.getString(5));

            tatuadores.add(tatuador);
        }

        return tatuadores;
    }

    public List<Tatuador> listar_params() {
        List<Tatuador> tatuadores = new ArrayList<>();
        Cursor cursor = db.query("tatuador", new String[]{"id", "nome", "idade", "genero", "especialidade", "horario"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Tatuador tatuador = new Tatuador(0, "", "", "", "", "");

            tatuador.setId(cursor.getInt(0));
            tatuador.setNome(cursor.getString(1));
            tatuador.setIdade(cursor.getString(2));
            tatuador.setGenero(cursor.getString(3));
            tatuador.setEspecialidade(cursor.getString(4));
            tatuador.setHorario(cursor.getString(5));

            tatuadores.add(tatuador);
        }

        return tatuadores;
    }

    public void excluir(Tatuador tatuador) {
        db.delete("tatuador", "id = ?", new String[]{String.valueOf(tatuador.getId())});
    }

    public void atualizar(Tatuador tatuador) {
        ContentValues values = new ContentValues();
        values.put("nome", tatuador.getNome());
        values.put("idade", Integer.parseInt(tatuador.getIdade()));
        values.put("genero", tatuador.getGenero());
        values.put("especialidade", tatuador.getEspecialidade());
        values.put("horario", tatuador.getHorario());

        System.out.println(db.update("tatuador", values, "id = ?", new String[]{String.valueOf(tatuador.getId())}));
    }

    public Tatuador buscar(Tatuador tatuador) {
        Cursor cursor = db.rawQuery("select * from tatuador where id = ?", new String[]{String.valueOf(tatuador.getId())});

        if (cursor.moveToNext()) {
            tatuador.setId(cursor.getInt(0));
            tatuador.setNome(cursor.getString(1));
            tatuador.setIdade(cursor.getString(2));
            tatuador.setGenero(cursor.getString(3));
            tatuador.setEspecialidade(cursor.getString(4));
            tatuador.setHorario(cursor.getString(5));
        }

        return tatuador;
    }

}
