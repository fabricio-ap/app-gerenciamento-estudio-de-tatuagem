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
import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.model.bean.Agenda;
import com.fabricioaraujo.appcombancodedados.model.bean.Cliente;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoAgenda;
import com.fabricioaraujo.appcombancodedados.model.dao.DaoCliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class BuscarAgenda extends AppCompatActivity {

    private DaoAgenda dao_agenda;

    private TextInputEditText txt_id;
    private Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_agenda);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes
        iniciar_componentes();

        // Instâncias
        dao_agenda = new DaoAgenda(this);

        txt_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        String id = txt_id.getText().toString();

                        if (id.equals("")) {
                            Snackbar.make(v, "Agendamento não encontrado", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Agenda agenda = new Agenda(Integer.parseInt(id));
                            buscar(agenda);
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
                    Agenda agenda = new Agenda(Integer.parseInt(id));
                    buscar(agenda);
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

    public void buscar(Agenda agenda) {
        Agenda busca_agenda = dao_agenda.buscar(agenda);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Busca")
                .setMessage(busca_agenda.toString())
                .setPositiveButton("Ok", null)
                .create();

        dialogo.show();
    }
}