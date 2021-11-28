package com.fabricioaraujo.appcombancodedados.acesso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fabricioaraujo.appcombancodedados.R;
import com.fabricioaraujo.appcombancodedados.model.bean.Usuario;
import com.fabricioaraujo.appcombancodedados.view.manteragenda.ManterAgenda;
import com.fabricioaraujo.appcombancodedados.view.mantercliente.ManterCliente;
import com.fabricioaraujo.appcombancodedados.view.mantertatuador.ManterTatuador;
import com.fabricioaraujo.appcombancodedados.view.manterusuario.ManterUsuario;

public class Menu extends AppCompatActivity {

    private Usuario usuario;

    private Button btn_usuario, btn_cliente, btn_tatuador, btn_agenda;
    private TextView lbl_nome, lbl_tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Iniciar componentes;
        iniciar_componentes();

        // Dados usuário logado
        Intent intent = getIntent();
        if (intent.hasExtra("usuario")) {
            usuario = (Usuario) intent.getSerializableExtra("usuario");
            lbl_nome.setText(usuario.getLogin());
            lbl_tipo.setText(usuario.getTipo() + " - " + usuario.getStatus());
        }

        // Botões
        btn_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManterUsuario.class);
                startActivity(intent);
            }
        });

        btn_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManterCliente.class);
                startActivity(intent);
            }
        });

        btn_tatuador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManterTatuador.class);
                startActivity(intent);
            }
        });

        btn_agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManterAgenda.class);
                startActivity(intent);
            }
        });
    }

    public void iniciar_componentes() {
        btn_usuario = findViewById(R.id.btn_usuario);
        btn_cliente = findViewById(R.id.btn_cliente);
        btn_tatuador = findViewById(R.id.btn_tatuador);
        btn_agenda = findViewById(R.id.btn_agenda);

        lbl_nome = findViewById(R.id.lbl_nome);
        lbl_tipo = findViewById(R.id.lbl_tipo);
    }

}