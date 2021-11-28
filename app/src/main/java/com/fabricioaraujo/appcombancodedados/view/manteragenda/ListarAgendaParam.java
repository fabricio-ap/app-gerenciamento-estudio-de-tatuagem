package com.fabricioaraujo.appcombancodedados.view.manteragenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Agenda;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoAgenda;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.InserirCliente;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListarAgendaParam extends AppCompatActivity {

    private DaoAgenda dao_agenda;

    private ListView list_agenda;
    private List<Agenda> agendamentos;
    private List<Agenda> agenda_filtrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_agenda_param);

        // Botão voltar no topo
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Funções
        dao_agenda = new DaoAgenda(getApplicationContext());
        DaoCliente dao_cliente = new DaoCliente(getApplicationContext());
        DaoTatuador dao_tatuador = new DaoTatuador(getApplicationContext());

        agendamentos = dao_agenda.listar_params(dao_cliente, dao_tatuador);
        agenda_filtrado.addAll(agendamentos);

        ArrayAdapter<Agenda> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agendamentos);
        list_agenda.setAdapter(adaptador);

        registerForContextMenu(list_agenda);
    }

    public void iniciar_componentes() {
        list_agenda = findViewById(R.id.list_agenda);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterAgenda.class);
        startActivity(myIntent);
        return true;
    }

    // Menu do item da lista
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menu_info) {
        super.onCreateContextMenu(menu, view, menu_info);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Agenda agenda_excluir = agenda_filtrado.get(menu_info.position);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Excluir")
                .setMessage("Deseja excluir este agendamento?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        agenda_filtrado.remove(agenda_excluir);
                        agendamentos.remove(agenda_excluir);
                        dao_agenda.excluir(agenda_excluir);
                        list_agenda.invalidateViews();
                    }
                }).create();

        dialogo.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Agenda agenda_atualizar = agenda_filtrado.get(menu_info.position);

        Intent intent = new Intent(this, InserirAgenda.class);
        intent.putExtra("agenda", agenda_atualizar);
        startActivity(intent);
    }

}