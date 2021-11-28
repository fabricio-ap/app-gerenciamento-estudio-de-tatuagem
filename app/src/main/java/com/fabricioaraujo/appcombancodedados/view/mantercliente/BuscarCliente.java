package com.fabricioaraujo.appcombancodedados.view.mantercliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ManterUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class BuscarCliente extends AppCompatActivity {

    private DaoCliente dao_cliente;

    private TextInputEditText txt_id;
    private Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_cliente = new DaoCliente(this);

        txt_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        String id = txt_id.getText().toString();

                        if (id.equals("")) {
                            Snackbar.make(v, "Usuário não encontrado", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Cliente cliente = new Cliente(Integer.parseInt(id));
                            buscar(cliente);
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
                    Snackbar.make(v, "Cliente não encontrado", Snackbar.LENGTH_SHORT).show();
                } else {
                    Cliente cliente = new Cliente(Integer.parseInt(id));
                    buscar(cliente);
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

    public void buscar(Cliente cliente) {
        Cliente busca_cliente = dao_cliente.buscar(cliente);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Busca")
                .setMessage(busca_cliente.toString())
                .setPositiveButton("Ok", null)
                .create();

        dialogo.show();
    }

}