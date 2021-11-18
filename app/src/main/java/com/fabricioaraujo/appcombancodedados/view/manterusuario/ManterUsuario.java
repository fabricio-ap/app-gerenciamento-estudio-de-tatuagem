package com.fabricioaraujo.appcombancodedados.view.manterusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.MainActivity;
import com.fabricioaraujo.appcombancodedados.R;

public class ManterUsuario extends AppCompatActivity {

    private Button btn_inserir, btn_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_usuario);

        // Botão voltar no topo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Iniciar componentes;
        iniciar_componentes();

        btn_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_inserir = new Intent(getApplicationContext(), InserirUsuario.class);
                startActivity(intent_inserir);
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_listar = new Intent(getApplicationContext(), ListarUsuario.class);
                startActivity(intent_listar);
            }
        });
    }

    public void iniciar_componentes() {
        btn_inserir = findViewById(R.id.btn_inserir);
        btn_listar = findViewById(R.id.btn_listar);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}