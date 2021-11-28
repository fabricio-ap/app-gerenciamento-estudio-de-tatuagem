package com.fabricioaraujo.appcombancodedados.view.mantercliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fabricioaraujo.appcombancodedados.MainActivity;
import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.acesso.Menu;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.BuscarUsuario;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.InserirUsuario;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ListarUsuario;

public class ManterCliente extends AppCompatActivity {

    private Button btn_inserir, btn_listar, btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_cliente);

        // Iniciar componentes;
        iniciar_componentes();

        btn_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_inserir = new Intent(getApplicationContext(), InserirCliente.class);
                startActivity(intent_inserir);
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_listar = new Intent(getApplicationContext(), ListarCliente.class);
                startActivity(intent_listar);
            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_buscar = new Intent(getApplicationContext(), BuscarCliente.class);
                startActivity(intent_buscar);
            }
        });
    }

    public void iniciar_componentes() {
        btn_inserir = findViewById(R.id.btn_inserir);
        btn_listar = findViewById(R.id.btn_listar);
        btn_buscar = findViewById(R.id.btn_buscar);
    }

}