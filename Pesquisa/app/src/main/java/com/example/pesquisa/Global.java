package com.example.pesquisa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Global {
    public static boolean isAdmin = false;
    public static int votosNulos = 0;
    public static int contSeguranca = 0;
    public static int contSaude = 0;
    public static int contEducacao = 0;

    public static void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public static boolean isAdmin() {
        return isAdmin;
    }
    public static class Candidato {
        public int id;
        public String nome;
        public int foto;
        public int votosEspontaneos;
        public int votosEstimulados;

        public Candidato(int id, String nome, int foto) {
            this.id = id;
            this.nome = nome;
            this.foto = foto;
            this.votosEspontaneos = 0;
            this.votosEstimulados = 0;
        }
    }

    public static List<Candidato> TODOS_CANDIDATOS = Arrays.asList(
            new Candidato(1, "goku", R.drawable.goku),
            new Candidato(2, "celta preto", R.drawable.celta),
            new Candidato(3, "julian casablancas", R.drawable.julian),
            new Candidato(4, "ze da manga", R.drawable.ze),
            new Candidato(5, "yujiro hanma", R.drawable.yujiro)
    );

    public static Candidato getCandidatoId(int id) {
        for (Candidato cand : TODOS_CANDIDATOS) {
            if (cand.id == id) {
                return cand;
            }
        }
        return null;
    }

    public static List<Eleitor> todosEleitores = new ArrayList<>();
    public static class Eleitor {
        public String nome;
        public String celular;
        public String localizacao;
        public String dataHora;

        public Eleitor(String nome, String celular, String localizacao, String dataHora) {
            this.nome = nome;
            this.celular = celular;
            this.localizacao = localizacao;
            this.dataHora = dataHora;
        }
    }

    public static void adicionarEleitor(Eleitor eleitor) {
        todosEleitores.add(eleitor);
    }
    public static String getDataHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }
    public static void registrarVotoEspontaneo(int idCandidato) {
        Candidato cand = getCandidatoId(idCandidato);
        if (cand != null) {
            cand.votosEspontaneos++;
        }
    }
    public static void registrarVotoEstimulado(int idCandidato) {
        for (Candidato cand : TODOS_CANDIDATOS) {
            if (cand.id == idCandidato) {
                cand.votosEstimulados++;
                break;
            }
        }
    }
    public static void registrarVotoNulo() {
        votosNulos++;
    }
    public static void contProblema(String problema) {
        switch (problema) {
            case "Segurança":
                contSeguranca++;
                break;
            case "Saúde":
                contSaude++;
                break;
            case "Educação":
                contEducacao++;
                break;
        }
    }
}