package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.MainActivity;
import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class InserirUsuario extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private TextInputEditText txt_usuario, txt_senha, txt_status, txt_tipo;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_usuario);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_usuario = new DaoUsuario(this);

        // Funções
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = txt_usuario.getText().toString();
                String senha = txt_senha.getText().toString();
                String status = txt_status.getText().toString();
                String tipo = txt_tipo.getText().toString();

                Usuario usuario = new Usuario(0, login, senha, status, tipo);
                long id = dao_usuario.inserir_usuario(usuario);

                Snackbar.make(view, "Usuário cadastrado com o id: " + id, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void iniciar_componentes() {
        txt_usuario = findViewById(R.id.txt_usuario);
        txt_senha = findViewById(R.id.txt_senha);
        txt_status = findViewById(R.id.txt_status);
        txt_tipo = findViewById(R.id.txt_tipo);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }

}