package org.example.e2_;

public class Estudante {

    private String nome;
    private String matricula;
    private String curso;

    // Construtor
    public Estudante(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    // Métodos para exibir as informações
    public String mostrarInformacoes() {
        return "Nome: " + nome + ", Matrícula: " + matricula + ", Curso: " + curso;
    }

    public String saudacao() {
        return "Olá, " + nome + "! Bem-vindo ao curso de " + curso + "!";
    }
}
