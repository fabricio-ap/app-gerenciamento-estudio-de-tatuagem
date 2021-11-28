package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListarUsuarioParam extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private ListView list_usuario;
    private List<Usuario> usuarios;
    private List<Usuario> usuario_filtrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario_param);

        // Botão voltar no topo
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Funções
        dao_usuario = new DaoUsuario(getApplicationContext());
        usuarios = dao_usuario.listar_params();
        usuario_filtrado.addAll(usuarios);

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);
        list_usuario.setAdapter(adaptador);

        registerForContextMenu(list_usuario);
    }

    public void iniciar_componentes() {
        list_usuario = findViewById(R.id.list_usuario);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterUsuario.class);
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
        final Usuario usuario_excluir = usuario_filtrado.get(menu_info.position);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Excluir")
                .setMessage("Deseja excluir este usuário?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuario_filtrado.remove(usuario_excluir);
                        usuarios.remove(usuario_excluir);
                        dao_usuario.excluir(usuario_excluir);
                        list_usuario.invalidateViews();
                    }
                }).create();

        dialogo.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Usuario usuario_atualizar = usuario_filtrado.get(menu_info.position);

        Intent intent = new Intent(this, InserirUsuario.class);
        intent.putExtra("usuario", usuario_atualizar);
        startActivity(intent);
    }

}