package com.fabricioaraujo.appcombancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ManterUsuario;

public class MainActivity extends AppCompatActivity {

    private Button btn_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Esconde a barra do topo
        getSupportActionBar().hide();

        // Iniciar componentes;
        iniciar_componentes();

        btn_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_usuario = new Intent(getApplicationContext(), ManterUsuario.class);
                startActivity(intent_usuario);
            }
        });
    }

    public void iniciar_componentes() {
        btn_usuario = findViewById(R.id.btn_usuario);
    }
}