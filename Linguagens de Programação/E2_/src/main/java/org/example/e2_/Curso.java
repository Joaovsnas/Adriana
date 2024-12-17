package org.example.e2_;

public class Curso {

    private String nome;
    private String codigo;
    private int duracao; // em anos

    // Construtor
    public Curso(String nome, String codigo, int duracao) {
        this.nome = nome;
        this.codigo = codigo;
        this.duracao = duracao;
    }

    // Métodos para exibir as informações
    public String mostrarInformacoes() {
        return "Curso: " + nome + ", Código: " + codigo + ", Duração: " + duracao + " anos";
    }

    public String saudacao() {
        return "Este curso é ótimo!";
    }
}
