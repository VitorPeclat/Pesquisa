package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Button btRealizarPesquisa = findViewById(R.id.btRealizarPesquisa);
        Button btResultados = findViewById(R.id.btResultados);
        Button btDeslogar = findViewById(R.id.btDeslogar);

        btRealizarPesquisa.setOnClickListener(v ->
                startActivity(new Intent(this, PesquisaEspontanea.class)));

        btResultados.setOnClickListener(v ->
                startActivity(new Intent(this, Resultado.class)));

        btDeslogar.setOnClickListener(v -> mostrarDialogoSaida());
    }

    private void mostrarDialogoSaida() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Deseja realmente sair?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    Global.setAdmin(false);
                    startActivity(new Intent(this, Login.class));
                    finish();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}