package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MenuPesquisador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pesquisador);

        Button btIniciarPesquisa = findViewById(R.id.btIniciarPesquisa);
        Button btDeslogar = findViewById(R.id.btDeslogar);

        btIniciarPesquisa.setOnClickListener(v ->
                startActivity(new Intent(this, PesquisaEspontanea.class)));

        btDeslogar.setOnClickListener(v -> mostrarDialogoSaida());
    }

    private void mostrarDialogoSaida() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Deseja realmente sair?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    startActivity(new Intent(this, Login.class));
                    finish();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}