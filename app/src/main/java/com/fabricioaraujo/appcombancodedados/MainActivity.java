package com.fabricioaraujo.appcombancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private TextInputEditText txt_login, txt_senha;
    private Button btn_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Esconde a barra do topo
        getSupportActionBar().hide();

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_usuario = new DaoUsuario(this);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = txt_login.getText().toString();
                String senha = txt_senha.getText().toString();

                Usuario usuario = new Usuario(login, senha);

                valida(v, usuario);
            }
        });
    }

    public void iniciar_componentes() {
        btn_entrar = findViewById(R.id.btn_entrar);
        txt_login = findViewById(R.id.txt_login);
        txt_senha = findViewById(R.id.txt_senha);
    }

    public void valida(View view, Usuario usuario) {
        final Usuario usuario_logado = dao_usuario.valida_login(usuario);

        System.out.println(usuario_logado);

        if (usuario_logado == null) {
            Snackbar.make(view, "Login ou senha inválidos", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Menu.class);
            intent.putExtra("usuario", usuario_logado);
            startActivity(intent);
        }
    }

}