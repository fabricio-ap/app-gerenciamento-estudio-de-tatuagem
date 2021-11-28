package com.fabricioaraujo.appcombancodedados.view.mantercliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.InserirUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListarClienteParam extends AppCompatActivity {

    private DaoCliente dao_cliente;

    private ListView list_cliente;
    private List<Cliente> clientes;
    private List<Cliente> cliente_filtrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente_param);

        // Botão voltar no topo
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Funções
        dao_cliente = new DaoCliente(getApplicationContext());
        clientes = dao_cliente.listar_params();
        cliente_filtrado.addAll(clientes);

        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);
        list_cliente.setAdapter(adaptador);

        registerForContextMenu(list_cliente);
    }

    public void iniciar_componentes() {
        list_cliente = findViewById(R.id.list_cliente);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterCliente.class);
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
        final Cliente cliente_excluir = cliente_filtrado.get(menu_info.position);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Excluir")
                .setMessage("Deseja excluir este cliente?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cliente_filtrado.remove(cliente_excluir);
                        clientes.remove(cliente_excluir);
                        dao_cliente.excluir(cliente_excluir);
                        list_cliente.invalidateViews();
                    }
                }).create();

        dialogo.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente cliente_atualizar = cliente_filtrado.get(menu_info.position);

        Intent intent = new Intent(this, InserirCliente.class);
        intent.putExtra("cliente", cliente_atualizar);
        startActivity(intent);
    }

}