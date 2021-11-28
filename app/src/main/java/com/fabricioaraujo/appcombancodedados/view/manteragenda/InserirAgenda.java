package com.fabricioaraujo.appcombancodedados.view.manteragenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Agenda;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.bean.Tatuador;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoAgenda;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class InserirAgenda extends AppCompatActivity {

    private DaoAgenda dao_agenda;
    private DaoCliente dao_cliente;
    private DaoTatuador dao_tatuador;

    private Cliente cliente = null;
    private Tatuador tatuador = null;
    private Agenda agenda = null;

    private List<Cliente> lista_clientes = new ArrayList<>();
    private List<Tatuador> lista_tatuadores = new ArrayList<>();

    private Spinner sp_cliente, sp_tatuador;
    private TextInputEditText txt_horario, txt_valor;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_agenda);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar compontentes
        iniciar_componentes();

        // Instâncias
        dao_cliente = new DaoCliente(this);
        dao_tatuador = new DaoTatuador(this);
        dao_agenda = new DaoAgenda(this);

        // Listas
        lista_clientes = dao_cliente.listar_params();
        lista_tatuadores = dao_tatuador.listar_params();

        ArrayAdapter adaptador_cliente = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_clientes);
        sp_cliente.setAdapter(adaptador_cliente);

        ArrayAdapter adaptador_tatuador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_tatuadores);
        sp_tatuador.setAdapter(adaptador_tatuador);

        // Funções
        Intent intent = getIntent();
        if (intent.hasExtra("agenda")) {
            agenda = (Agenda) intent.getSerializableExtra("agenda");
            sp_cliente.setSelection(getIndex(sp_cliente, agenda.getCliente().toString()));
            sp_tatuador.setSelection(getIndex(sp_tatuador, agenda.getTatuador().toString()));
            txt_horario.setText(agenda.getHorario());
            txt_valor.setText(agenda.getValor());
        }

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (agenda == null) {
                    Cliente cliente = (Cliente) sp_cliente.getSelectedItem();
                    Integer id_cliente = cliente.getId();
                    Tatuador tatuador = (Tatuador) sp_tatuador.getSelectedItem();
                    Integer id_tatuador = tatuador.getId();
                    String horario = txt_horario.getText().toString();
                    String valor = txt_valor.getText().toString();

                    Agenda agenda = new Agenda(0, id_cliente, id_tatuador, horario, valor);
                    long id = dao_agenda.inserir(agenda);

                    Snackbar.make(view, "Cliente cadastrado com o id: " + id, Snackbar.LENGTH_SHORT).show();

                } else {
                    Cliente cliente = (Cliente) sp_cliente.getSelectedItem();
                    agenda.setIdCliente(cliente.getId());
                    Tatuador tatuador = (Tatuador) sp_tatuador.getSelectedItem();
                    agenda.setIdTatuador(tatuador.getId());
                    agenda.setHorario(txt_horario.getText().toString());
                    agenda.setValor(txt_valor.getText().toString());

                    dao_cliente.atualizar(cliente);

                    Snackbar.make(view, "Cliente atualizado", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciar_componentes() {
        sp_cliente = findViewById(R.id.sp_cliente);
        sp_tatuador = findViewById(R.id.sp_tatuador);
        txt_horario = findViewById(R.id.txt_horario);
        txt_valor = findViewById(R.id.txt_valor);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManterAgenda.class);
        startActivity(myIntent);
        return true;
    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

}