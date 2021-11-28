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
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ListarUsuarioParam;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ManterUsuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ListarCliente extends AppCompatActivity {

    private DaoCliente dao_cliente;

    private TextInputEditText txt_nome;
    private Button btn_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_cliente = new DaoCliente(this);

        // Funções
        txt_nome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (txt_nome.getText().equals("")) {
                            Intent intent_listar_param = new Intent(getApplicationContext(), ListarClienteParam.class);
                            startActivity(intent_listar_param);
                        } else {
                            String nome = txt_nome.getText().toString();
                            Cliente cliente = new Cliente(nome);

                            listar(cliente);
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
                if (txt_nome.getText().equals("")) {
                    Intent intent_listar_param = new Intent(getApplicationContext(), ListarClienteParam.class);
                    startActivity(intent_listar_param);
                } else {
                    String nome = txt_nome.getText().toString();
                    Cliente cliente = new Cliente(nome);

                    listar(cliente);
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_nome = findViewById(R.id.txt_nome);
        btn_listar = findViewById(R.id.btn_listar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterCliente.class);
        startActivity(myIntent);
        return true;
    }

    public void listar(Cliente cliente) {
        List<Cliente> lista_cliente = dao_cliente.listar(cliente);

        if (lista_cliente.isEmpty()) {
            Intent lista_clientes = new Intent(this, ListarClienteParam.class);
            startActivity(lista_clientes);
        } else {
            AlertDialog dialogo = new AlertDialog.Builder(this)
                    .setTitle("Lista")
                    .setMessage(lista_cliente.get(0).toString())
                    .setPositiveButton("Ok", null)
                    .create();

            dialogo.show();
        }
    }

}