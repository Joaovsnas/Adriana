package org.example.e2_;

public class Livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;

    // Construtor
    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    // Métodos para exibir as informações
    public String mostrarDetalhes() {
        return "Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao;
    }

    public String saudacaoLivro() {
        return "Este livro é incrível!";
    }
}
