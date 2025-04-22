package com.example.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText edUsuario, edSenha;
    private Button btLogin;
    private TextView txMensagem;

    private static final String USUARIO_ADMIN = "admin";
    private static final String SENHA_ADMIN = "admin";
    private static final String USUARIO_PESQUISADOR = "pesquisador";
    private static final String SENHA_PESQUISADOR = "pesquisador";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Global.setAdmin(false);

        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);
        btLogin = findViewById(R.id.btLogin);
        txMensagem = findViewById(R.id.txMensagem);

        btLogin.setOnClickListener(v -> {
            String usuario = edUsuario.getText().toString().trim();
            String senha = edSenha.getText().toString().trim();

            if (usuario.isEmpty() || senha.isEmpty()) {
                txMensagem.setText("Preencha usuário e senha");
            } else if (usuario.equals(USUARIO_ADMIN) && senha.equals(SENHA_ADMIN)) {
                Global.setAdmin(true);
                abrirMenuAdmin();
            } else if (usuario.equals(USUARIO_PESQUISADOR) && senha.equals(SENHA_PESQUISADOR)) {
                Global.setAdmin(false);
                abrirMenuPesquisador();
            } else {
                txMensagem.setText("Login e/ou Senha inválidas");
            }
        });
    }

    private void abrirMenuAdmin() {
        startActivity(new Intent(this, MenuAdmin.class));
        finish();
    }

    private void abrirMenuPesquisador() {
        startActivity(new Intent(this, MenuPesquisador.class));
        finish();
    }
}