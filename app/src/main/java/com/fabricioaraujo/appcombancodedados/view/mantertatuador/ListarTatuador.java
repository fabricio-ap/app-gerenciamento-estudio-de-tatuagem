package com.fabricioaraujo.appcombancodedados.view.mantertatuador;

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
import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ListarClienteParam;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ListarUsuarioParam;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ListarTatuador extends AppCompatActivity {

    private DaoTatuador dao_tatuador;

    private TextInputEditText txt_nome;
    private Button btn_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tatuador);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_tatuador = new DaoTatuador(this);

        // Funções
        txt_nome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (txt_nome.getText().equals("")) {
                            Intent intent_listar_param = new Intent(getApplicationContext(), ListarTatuadorParam.class);
                            startActivity(intent_listar_param);
                        } else {
                            String nome = txt_nome.getText().toString();
                            Tatuador tatuador = new Tatuador(nome);

                            listar(tatuador);
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
                    Intent intent_listar_param = new Intent(getApplicationContext(), ListarTatuadorParam.class);
                    startActivity(intent_listar_param);
                } else {
                    String nome = txt_nome.getText().toString();
                    Tatuador tatuador = new Tatuador(nome);

                    listar(tatuador);
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_nome = findViewById(R.id.txt_nome);
        btn_listar = findViewById(R.id.btn_listar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterTatuador.class);
        startActivity(myIntent);
        return true;
    }

    public void listar(Tatuador tatuador) {
        List<Tatuador> lista_tatuador = dao_tatuador.listar(tatuador);

        if (lista_tatuador.isEmpty()) {
            Intent lista_tatuadores = new Intent(this, ListarTatuadorParam.class);
            startActivity(lista_tatuadores);
        } else {
            AlertDialog dialogo = new AlertDialog.Builder(this)
                    .setTitle("Lista")
                    .setMessage(lista_tatuador.get(0).toString())
                    .setPositiveButton("Ok", null)
                    .create();

            dialogo.show();
        }
    }

}