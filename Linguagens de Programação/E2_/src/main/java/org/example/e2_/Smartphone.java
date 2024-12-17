package org.example.e2_;

public class Smartphone {

    private String marca;
    private String modelo;
    private int ano;

    // Construtor
    public Smartphone() {
    }

    // Métodos para preencher os dados do smartphone
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // Métodos para obter os dados do smartphone
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    // Método para retornar os dados do smartphone em formato de array (para a tabela)
    public String[] getDadosSmartphone() {
        return new String[]{marca, modelo, String.valueOf(ano)};
    }
}
