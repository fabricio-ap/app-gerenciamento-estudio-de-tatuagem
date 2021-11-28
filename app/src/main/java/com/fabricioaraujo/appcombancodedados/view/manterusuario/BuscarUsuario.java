package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLOutput;
import java.util.UUID;

public class BuscarUsuario extends AppCompatActivity {

    private DaoUsuario dao_usuario;

    private TextInputEditText txt_id;
    private Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_usuario = new DaoUsuario(this);

        txt_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        String id = txt_id.getText().toString();

                        if (id.equals("")) {
                            Snackbar.make(v, "Usuário não encontrado", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Usuario usuario = new Usuario(Integer.parseInt(id));
                            buscar(usuario);
                        }

                        return true;
                    }
                }

                return false;
            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txt_id.getText().toString();

                if (id.equals("")) {
                    Snackbar.make(v, "Usuário não encontrado", Snackbar.LENGTH_SHORT).show();
                } else {
                    Usuario usuario = new Usuario(Integer.parseInt(id));
                    buscar(usuario);
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_id = findViewById(R.id.txt_id);
        btn_buscar = findViewById(R.id.btn_buscar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Menu.class);
        startActivity(myIntent);
        return true;
    }

    public void buscar(Usuario usuario) {
        Usuario busca_usuario = dao_usuario.buscar(usuario);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Busca")
                .setMessage(busca_usuario.toString())
                .setPositiveButton("Ok", null)
                .create();

        dialogo.show();
    }

}