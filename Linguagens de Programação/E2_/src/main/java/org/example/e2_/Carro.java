package org.example.e2_;

public class Carro {

    private String modelo;
    private String cor;
    private int ano;
    private int velocidade;


    public Carro() {
        this.velocidade = 0; // A velocidade inicial é 0
    }


    public void acelerar() {
        this.velocidade += 10;
    }

    public void diminuir() {
        if (this.velocidade > 0) {
            this.velocidade -= 10;
        } else {
            this.velocidade = 0;
        }
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }


    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getAno() {
        return ano;
    }

    public int getVelocidade() {
        return velocidade;
    }


    public String[] getDadosCarro() {
        return new String[]{modelo, cor, String.valueOf(ano)};
    }
}
