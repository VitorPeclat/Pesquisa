package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProblemasCidade extends AppCompatActivity {

    private CheckBox checkSeguranca, checkSaude, checkEducacao;
    private Button btDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problemas_cidade);

        checkSeguranca = findViewById(R.id.checkSeguranca);
        checkSaude = findViewById(R.id.checkSaude);
        checkEducacao = findViewById(R.id.checkEducacao);
        btDados = findViewById(R.id.btDados);

        btDados.setOnClickListener(v -> {
            if (!checkSeguranca.isChecked() && !checkSaude.isChecked() && !checkEducacao.isChecked()) {
                Toast.makeText(this, "Selecione pelo menos um problema", Toast.LENGTH_SHORT).show();
                return;
            }
            registrarProblemas();
            startActivity(new Intent(this, CadastroDados.class));
        });
    }

    private void registrarProblemas() {
        if (checkSeguranca.isChecked()) {
            Global.contProblema("Segurança");
        }
        if (checkSaude.isChecked()) {
            Global.contProblema("Saúde");
        }
        if (checkEducacao.isChecked()) {
            Global.contProblema("Educação");
        }
    }

}