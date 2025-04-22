package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroDados extends AppCompatActivity {

    private EditText etNome, etCelular, etLocalizacao;
    private TextView tvDataHora;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dados);

        etNome = findViewById(R.id.etNome);
        etCelular = findViewById(R.id.etCelular);
        etLocalizacao = findViewById(R.id.etLocalizacao);
        tvDataHora = findViewById(R.id.tvDataHora);
        btSalvar = findViewById(R.id.btSalvar);

        tvDataHora.setText(Global.getDataHora());

        btSalvar.setOnClickListener(v -> {
            if (validarCampos()) {
                salvarDadosEleitor();
                Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                finalizar();
            }
        });
    }

    private boolean validarCampos() {
        if (etNome.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Preencha o nome completo", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCelular.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Preencha o número de celular", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etLocalizacao.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Preencha a localização", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void salvarDadosEleitor() {
        String nome = etNome.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String localizacao = etLocalizacao.getText().toString().trim();

        Global.Eleitor novoEleitor = new Global.Eleitor(nome, celular, localizacao, Global.getDataHora());
        Global.adicionarEleitor(novoEleitor);
    }
    private void finalizar() {
        Toast.makeText(this, "Pesquisa finalizada com sucesso!", Toast.LENGTH_SHORT).show();
        Intent intent;
        if (Global.isAdmin()) {
            intent = new Intent(this, MenuAdmin.class);
        } else {
            intent = new Intent(this, MenuPesquisador.class);
        }

        startActivity(intent);
        finish();
    }
}