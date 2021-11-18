package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;

import java.util.ArrayList;
import java.util.List;

public class ListarUsuario extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private ListView list_usuario;
    private List<Usuario> usuarios;
    private List<Usuario> usuario_filtrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_usuario = new DaoUsuario(this);

        // Funções
        usuarios = dao_usuario.listar();
        usuario_filtrado.addAll(usuarios);

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, usuarios);
        list_usuario.setAdapter(adaptador);
    }

    public void iniciar_componentes() {
        list_usuario = findViewById(R.id.list_usuario);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterUsuario.class);
        startActivity(myIntent);
        return true;
    }

}