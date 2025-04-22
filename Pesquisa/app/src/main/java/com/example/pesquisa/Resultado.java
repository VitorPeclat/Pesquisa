package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        exibirCandidato(1, R.id.imgCandidato1, R.id.nomeCandidato1, R.id.votosEspontaneos1, R.id.votosEstimulados1);
        exibirCandidato(2, R.id.imgCandidato2, R.id.nomeCandidato2, R.id.votosEspontaneos2, R.id.votosEstimulados2);
        exibirCandidato(3, R.id.imgCandidato3, R.id.nomeCandidato3, R.id.votosEspontaneos3, R.id.votosEstimulados3);
        exibirCandidato(4, R.id.imgCandidato4, R.id.nomeCandidato4, R.id.votosEspontaneos4, R.id.votosEstimulados4);
        exibirCandidato(5, R.id.imgCandidato5, R.id.nomeCandidato5, R.id.votosEspontaneos5, R.id.votosEstimulados5);

        TextView tvSeguranca = findViewById(R.id.contSeguranca);
        TextView tvSaude = findViewById(R.id.contSaude);
        TextView tvEducacao = findViewById(R.id.contEducacao);
        TextView tvNulos = findViewById(R.id.contNulos);

        tvSeguranca.setText(String.valueOf(Global.contSeguranca));
        tvSaude.setText(String.valueOf(Global.contSaude));
        tvEducacao.setText(String.valueOf(Global.contEducacao));
        tvNulos.setText(String.valueOf(Global.votosNulos));

        LinearLayout todosEleitores = findViewById(R.id.todosEleitores);

            for (Global.Eleitor eleitor : Global.todosEleitores) {
                TextView tvEleitor = new TextView(this);
                String dadosEleitor = String.format("%s | %s | %s | %s",
                        eleitor.nome,
                        eleitor.localizacao,
                        eleitor.celular,
                        eleitor.dataHora);
                tvEleitor.setText(dadosEleitor);
                tvEleitor.setTextSize(16);
                tvEleitor.setPadding(0, 8, 0, 8);
                todosEleitores.addView(tvEleitor);
            }

        Button btnVoltar = findViewById(R.id.btVoltar);
        btnVoltar.setOnClickListener(v -> {
            if (Global.isAdmin()) {
                startActivity(new Intent(this, MenuAdmin.class));
            } else {
                startActivity(new Intent(this, MenuPesquisador.class));
            }
            finish();
        });
    }
    private void exibirCandidato(int id, int imgId, int nomeId, int espontaneosId, int estimuladosId) {
        Global.Candidato cand = Global.getCandidatoId(id);
        if (cand != null) {
            ImageView imgView = findViewById(imgId);
            TextView tvNome = findViewById(nomeId);
            TextView tvEspontaneos = findViewById(espontaneosId);
            TextView tvEstimulados = findViewById(estimuladosId);

            imgView.setImageResource(cand.foto);
            tvNome.setText(cand.nome);
            tvEspontaneos.setText("Espontâneos: " + cand.votosEspontaneos);
            tvEstimulados.setText("Estimulados: " + cand.votosEstimulados);
        }
    }
}