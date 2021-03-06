package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ListarUsuario extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private TextInputEditText txt_login;
    private Button btn_listar;

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
        txt_login.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (txt_login.getText().equals("")) {
                            Intent intent_listar_param = new Intent(getApplicationContext(), ListarUsuarioParam.class);
                            startActivity(intent_listar_param);
                        } else {
                            String login = txt_login.getText().toString();
                            Usuario usuario = new Usuario(login);

                            listar(usuario);
                        }

                        return true;
                    }
                }

                return false;
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_login.getText().equals("")) {
                    Intent intent_listar_param = new Intent(getApplicationContext(), ListarUsuarioParam.class);
                    startActivity(intent_listar_param);
                } else {
                    String login = txt_login.getText().toString();
                    Usuario usuario = new Usuario(login);

                    listar(usuario);
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_login = findViewById(R.id.txt_login);
        btn_listar = findViewById(R.id.btn_listar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterUsuario.class);
        startActivity(myIntent);
        return true;
    }

    public void listar(Usuario usuario) {
        List<Usuario> lista_usuario = dao_usuario.listar(usuario);

        if (lista_usuario.isEmpty()) {
            Intent lista_usuarios = new Intent(this, ListarUsuarioParam.class);
            startActivity(lista_usuarios);
        } else {
            AlertDialog dialogo = new AlertDialog.Builder(this)
                    .setTitle("Lista")
                    .setMessage(lista_usuario.get(0).toString())
                    .setPositiveButton("Ok", null)
                    .create();

            dialogo.show();
        }
    }

}