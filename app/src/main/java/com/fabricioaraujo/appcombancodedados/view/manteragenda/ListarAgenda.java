package com.fabricioaraujo.appcombancodedados.view.manteragenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Agenda;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoAgenda;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoTatuador;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ListarClienteParam;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ListarAgenda extends AppCompatActivity {

    private DaoAgenda dao_agenda;

    private TextInputEditText txt_nome;
    private Button btn_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_agenda);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_agenda = new DaoAgenda(this);

        // Funções
        txt_nome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (txt_nome.getText().equals("")) {
                            Intent intent_listar_param = new Intent(getApplicationContext(), ListarAgendaParam.class);
                            startActivity(intent_listar_param);
                        } else {
                            String nome = txt_nome.getText().toString();
                            Agenda agenda = new Agenda(nome);

                            listar(agenda);
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
                    Intent intent_listar_param = new Intent(getApplicationContext(), ListarAgendaParam.class);
                    startActivity(intent_listar_param);
                } else {
                    String nome = txt_nome.getText().toString();
                    Agenda agenda = new Agenda(nome);

                    listar(agenda);
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

    public void listar(Agenda agenda) {
        DaoCliente dao_cliente = new DaoCliente(this);
        DaoTatuador dao_tatuador = new DaoTatuador(this);
        List<Agenda> lista_agenda = dao_agenda.listar(agenda, dao_cliente, dao_tatuador);

        if (lista_agenda.isEmpty()) {
            Intent lista_agendas = new Intent(this, ListarAgendaParam.class);
            startActivity(lista_agendas);
        } else {
            Agenda agendamento = lista_agenda.get(0);

            AlertDialog dialogo = new AlertDialog.Builder(this)
                    .setTitle("Lista")
                    .setMessage(
                            "Id = " + agendamento.getId() + "\n" +
                            "Cliente = " + agendamento.getCliente().getNome() + "\n" +
                            "Tatuador = " + agendamento.getTatuador().getNome() + "\n" +
                            "Horário = " + agendamento.getHorario() + "\n" +
                            "Valor = " + agendamento.getValor() + "\n"
                    )
                    .setPositiveButton("Ok", null)
                    .create();

            dialogo.show();
        }
    }

}