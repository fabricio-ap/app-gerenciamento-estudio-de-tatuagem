package com.fabricioaraujo.appcombancodedados.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fabricioaraujo.appcombancodedados.model.bean.Agenda;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class DaoAgenda {

    private Conexao conexao;
    private SQLiteDatabase db;

    public DaoAgenda (Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put("id_cliente", agenda.getIdCliente());
        values.put("id_tatuador", agenda.getIdTatuador());
        values.put("horario", agenda.getHorario());
        values.put("valor", agenda.getValor());

        return db.insert("agenda", null, values);
    }

    public List<Agenda> listar(Agenda agenda, DaoCliente dao_cliente, DaoTatuador dao_tatuador) {
        List<Agenda> agendamentos = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from agenda where horario = ?", new String[]{String.valueOf(agenda.getHorario())});

        while (cursor.moveToNext()) {
            agenda.setId(cursor.getInt(0));
            agenda.setIdCliente(cursor.getInt(1));
            agenda.setIdTatuador(cursor.getInt(2));
            agenda.setHorario(cursor.getString(3));
            agenda.setValor(cursor.getString(4));

            agendamentos.add(agenda);
        }

        for (Agenda agendamento : agendamentos) {
            Cliente cliente_entrada = new Cliente(agendamento.getIdCliente());
            Cliente busca_cliente = dao_cliente.buscar(cliente_entrada);
            agendamento.setCliente(busca_cliente);

            Tatuador tatuador_entrada = new Tatuador(agendamento.getIdTatuador());
            Tatuador tatuador_saida = dao_tatuador.buscar(tatuador_entrada);
            agendamento.setTatuador(tatuador_saida);
        }

        return agendamentos;
    }

    public List<Agenda> listar_params(DaoCliente dao_cliente, DaoTatuador dao_tatuador) {
        List<Agenda> agendamentos = new ArrayList<>();
        Cursor cursor = db.query("agenda", new String[]{"id", "id_cliente", "id_tatuador", "horario", "valor"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Agenda agenda = new Agenda(0, 0, 0, "", "");

            agenda.setId(cursor.getInt(0));
            agenda.setIdCliente(cursor.getInt(1));
            agenda.setIdTatuador(cursor.getInt(2));
            agenda.setHorario(cursor.getString(3));
            agenda.setValor(cursor.getString(4));

            agendamentos.add(agenda);
        }

        for (Agenda agendamento : agendamentos) {
            Cliente cliente_entrada = new Cliente(agendamento.getIdCliente());
            Cliente busca_cliente = dao_cliente.buscar(cliente_entrada);
            agendamento.setCliente(busca_cliente);

            Tatuador tatuador_entrada = new Tatuador(agendamento.getIdTatuador());
            Tatuador tatuador_saida = dao_tatuador.buscar(tatuador_entrada);
            agendamento.setTatuador(tatuador_saida);
        }

        return agendamentos;
    }

    public void excluir(Agenda agenda) {
        db.delete("agenda", "id = ?", new String[]{String.valueOf(agenda.getId())});
    }

    public Agenda buscar(Agenda agenda) {
        Cursor cursor = db.rawQuery("select * from agenda where id = ?", new String[]{String.valueOf(agenda.getId())});

        if (cursor.moveToNext()) {
            agenda.setId(cursor.getInt(0));
            agenda.setIdCliente(cursor.getInt(1));
            agenda.setIdTatuador(cursor.getInt(2));
            agenda.setHorario(cursor.getString(3));
            agenda.setValor(cursor.getString(4));
        }

        return agenda;
    }

}
