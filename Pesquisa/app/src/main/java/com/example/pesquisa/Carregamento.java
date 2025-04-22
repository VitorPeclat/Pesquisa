package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Carregamento extends AppCompatActivity {

    private static final int SPLASH_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Carregamento.this, Login.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}