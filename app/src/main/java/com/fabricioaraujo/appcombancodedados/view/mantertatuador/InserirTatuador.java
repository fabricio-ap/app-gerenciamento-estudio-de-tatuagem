package com.fabricioaraujo.appcombancodedados.view.mantertatuador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class InserirTatuador extends AppCompatActivity {

    private DaoTatuador dao_tatuador;
    private Tatuador tatuador = null;

    private TextInputEditText txt_nome, txt_idade, txt_genero, txt_especialidade, txt_horario;
    private Button btn_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_tatuador);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_tatuador = new DaoTatuador(this);

        // Funções
        Intent intent = getIntent();
        if (intent.hasExtra("tatuador")) {
            tatuador = (Tatuador) intent.getSerializableExtra("tatuador");
            txt_nome.setText(tatuador.getNome());
            txt_idade.setText(tatuador.getIdade());
            txt_genero.setText(tatuador.getGenero());
            txt_especialidade.setText(tatuador.getEspecialidade());
            txt_horario.setText(tatuador.getHorario());
        }

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tatuador == null) {
                    String nome = txt_nome.getText().toString();
                    String idade = txt_idade.getText().toString();
                    String genero = txt_genero.getText().toString();
                    String especialidade = txt_especialidade.getText().toString();
                    String horario = txt_horario.getText().toString();

                    Tatuador tatuador = new Tatuador(0, nome, idade, genero, especialidade, horario);
                    long id = dao_tatuador.inserir(tatuador);

                    Snackbar.make(view, "Tatuador cadastrado com o id: " + id, Snackbar.LENGTH_SHORT).show();

                } else {
                    tatuador.setNome(txt_nome.getText().toString());
                    tatuador.setIdade(txt_idade.getText().toString());
                    tatuador.setGenero(txt_genero.getText().toString());
                    tatuador.setEspecialidade(txt_especialidade.getText().toString());
                    tatuador.setHorario(txt_horario.getText().toString());

                    dao_tatuador.atualizar(tatuador);

                    Snackbar.make(view, "Tatuador atualizado", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciar_componentes() {
        txt_nome = findViewById(R.id.txt_nome);
        txt_idade = findViewById(R.id.txt_idade);
        txt_genero = findViewById(R.id.txt_genero);
        txt_especialidade = findViewById(R.id.txt_especialidade);
        txt_horario = findViewById(R.id.txt_horario);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Menu.class);
        startActivity(myIntent);
        return true;
    }

}