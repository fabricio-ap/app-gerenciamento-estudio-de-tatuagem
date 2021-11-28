package com.fabricioaraujo.appcombancodedados.view.mantercliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoUsuario;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ManterUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class InserirCliente extends AppCompatActivity {

    private DaoCliente dao_cliente;
    private Cliente cliente = null;

    private TextInputEditText txt_nome, txt_idade, txt_genero, txt_obs, txt_detalhes_tatuagem;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_cliente);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_cliente = new DaoCliente(this);

        // Funções
        Intent intent = getIntent();
        if (intent.hasExtra("cliente")) {
            cliente = (Cliente) intent.getSerializableExtra("cliente");
            txt_nome.setText(cliente.getNome());
            txt_idade.setText(cliente.getIdade());
            txt_genero.setText(cliente.getGenero());
            txt_obs.setText(cliente.getObs());
            txt_detalhes_tatuagem.setText(cliente.getDetalhesTattoo());
        }

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cliente == null) {
                    String nome = txt_nome.getText().toString();
                    String idade = txt_idade.getText().toString();
                    String genero = txt_genero.getText().toString();
                    String obs = txt_obs.getText().toString();
                    String detalhes_tatuagem = txt_detalhes_tatuagem.getText().toString();

                    Cliente cliente = new Cliente(0, nome, idade, genero, obs, detalhes_tatuagem);
                    long id = dao_cliente.inserir(cliente);

                    Snackbar.make(view, "Cliente cadastrado com o id: " + id, Snackbar.LENGTH_SHORT).show();

                } else {
                    cliente.setNome(txt_nome.getText().toString());
                    cliente.setIdade(txt_idade.getText().toString());
                    cliente.setGenero(txt_genero.getText().toString());
                    cliente.setObs(txt_obs.getText().toString());
                    cliente.setDetalhesTattoo(txt_detalhes_tatuagem.getText().toString());

                    dao_cliente.atualizar(cliente);

                    Snackbar.make(view, "Cliente atualizado", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_nome = findViewById(R.id.txt_nome);
        txt_idade = findViewById(R.id.txt_idade);
        txt_genero = findViewById(R.id.txt_genero);
        txt_obs = findViewById(R.id.txt_obs);
        txt_detalhes_tatuagem = findViewById(R.id.txt_detalhes_tatuagem);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterCliente.class);
        startActivity(myIntent);
        return true;
    }
}