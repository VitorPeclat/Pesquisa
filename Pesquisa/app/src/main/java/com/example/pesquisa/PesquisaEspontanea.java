package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PesquisaEspontanea extends AppCompatActivity {

    private EditText edNomeCandidato;
    private Button btConfirmar;
    private TextView txMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_espontanea);

        edNomeCandidato = findViewById(R.id.edNomeCandidato);
        btConfirmar = findViewById(R.id.btConfirmar);
        txMensagem = findViewById(R.id.txMensagem);

        btConfirmar.setOnClickListener(v -> {
            String nomeDigitado = edNomeCandidato.getText().toString().trim();

            if (nomeDigitado.isEmpty()) {
                txMensagem.setText("Digite o nome do candidato");
                return;
            }

            for (Global.Candidato cand : Global.TODOS_CANDIDATOS) {
                if (cand.nome.equalsIgnoreCase(nomeDigitado)) {
                    Global.registrarVotoEspontaneo(cand.id);
                    Toast.makeText(this, "Voto registrado para " + cand.nome, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, PesquisaEstimulada.class));
                    finish();
                    return;
                }
            }

            txMensagem.setText("Candidato não encontrado");
        });
    }
}