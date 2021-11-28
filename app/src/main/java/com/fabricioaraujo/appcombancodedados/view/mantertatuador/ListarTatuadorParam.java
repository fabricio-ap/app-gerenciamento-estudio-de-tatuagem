package com.fabricioaraujo.appcombancodedados.view.mantertatuador;

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
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.InserirCliente;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListarTatuadorParam extends AppCompatActivity {

    private DaoTatuador dao_tatuador;

    private ListView list_tatuador;
    private List<Tatuador> tatuadores;
    private List<Tatuador> tatuador_filtrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tatuador_param);

        // Botão voltar no topo
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Funções
        dao_tatuador = new DaoTatuador(getApplicationContext());
        tatuadores = dao_tatuador.listar_params();
        tatuador_filtrado.addAll(tatuadores);

        ArrayAdapter<Tatuador> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tatuadores);
        list_tatuador.setAdapter(adaptador);

        registerForContextMenu(list_tatuador);
    }

    public void iniciar_componentes() {
        list_tatuador = findViewById(R.id.list_tatuador);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterTatuador.class);
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
        final Tatuador tatuador_excluir = tatuador_filtrado.get(menu_info.position);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Excluir")
                .setMessage("Deseja excluir este tatuador?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tatuador_filtrado.remove(tatuador_excluir);
                        tatuadores.remove(tatuador_excluir);
                        dao_tatuador.excluir(tatuador_excluir);
                        list_tatuador.invalidateViews();
                    }
                }).create();

        dialogo.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Tatuador tatuador_atualizar = tatuador_filtrado.get(menu_info.position);

        System.out.println(tatuador_atualizar);

        Intent intent = new Intent(this, InserirTatuador.class);
        intent.putExtra("tatuador", tatuador_atualizar);
        startActivity(intent);
    }
}