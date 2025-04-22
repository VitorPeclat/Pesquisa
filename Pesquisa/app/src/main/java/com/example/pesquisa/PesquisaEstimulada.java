package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PesquisaEstimulada extends AppCompatActivity {

    private RadioGroup rgCandidatos;
    private Button btConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_estimulada);

        rgCandidatos = findViewById(R.id.rgCandidatos);
        btConfirmar = findViewById(R.id.btConfirmar);

        exibirCandidato(R.id.imgCandidato1, R.id.tvCandidato1, 1);
        exibirCandidato(R.id.imgCandidato2, R.id.tvCandidato2, 2);
        exibirCandidato(R.id.imgCandidato3, R.id.tvCandidato3, 3);
        exibirCandidato(R.id.imgCandidato4, R.id.tvCandidato4, 4);
        exibirCandidato(R.id.imgCandidato5, R.id.tvCandidato5, 5);

        atribuirCandidato(R.id.rbCandidato1, 1);
        atribuirCandidato(R.id.rbCandidato2, 2);
        atribuirCandidato(R.id.rbCandidato3, 3);
        atribuirCandidato(R.id.rbCandidato4, 4);
        atribuirCandidato(R.id.rbCandidato5, 5);
        atribuirCandidato(R.id.rbNulo, -1);

        btConfirmar.setOnClickListener(v -> processarVoto());
    }

    private void exibirCandidato(int imgId, int txtId, int candidatoId) {
        Global.Candidato cand = Global.getCandidatoId(candidatoId);
        if (cand != null) {
            ImageView imageView = findViewById(imgId);
            TextView textView = findViewById(txtId);

            imageView.setImageResource(cand.foto);
            textView.setText(cand.nome);
        }
    }

    private void atribuirCandidato(int rbId, int candidatoId) {
        View rootView = findViewById(rbId).getRootView();
        View radioButton = findViewById(rbId);

        rootView.setOnClickListener(v -> {
            rgCandidatos.check(rbId);
            if (candidatoId > 0) {
                Toast.makeText(this, "Selecionado: " + Global.getCandidatoId(candidatoId).nome,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Voto nulo selecionado", Toast.LENGTH_SHORT).show();
            }
        });
        radioButton.setOnClickListener(v -> {
            rgCandidatos.check(rbId);
        });
    }

    private void processarVoto() {
        int selectedId = rgCandidatos.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedId == R.id.rbNulo) {
            Global.registrarVotoNulo();
            Toast.makeText(this, "Voto nulo registrado", Toast.LENGTH_SHORT).show();
        } else {
            int candidatoId = 0;
            if (selectedId == R.id.rbCandidato1) candidatoId = 1;
            else if (selectedId == R.id.rbCandidato2) candidatoId = 2;
            else if (selectedId == R.id.rbCandidato3) candidatoId = 3;
            else if (selectedId == R.id.rbCandidato4) candidatoId = 4;
            else if (selectedId == R.id.rbCandidato5) candidatoId = 5;

            if (candidatoId > 0) {
                Global.registrarVotoEstimulado(candidatoId);
                Global.Candidato cand = Global.getCandidatoId(candidatoId);
                Toast.makeText(this, "Voto registrado para " + cand.nome, Toast.LENGTH_SHORT).show();
            }
        }

        startActivity(new Intent(this, ProblemasCidade.class));
        finish();
    }
}