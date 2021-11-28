package com.fabricioaraujo.appcombancodedados.model.bean;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String idade;
    private String genero;
    private String detalhes_tattoo;
    private String obs;

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(int id, String nome, String idade, String genero, String obs, String detalhes_tattoo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.obs = obs;
        this.detalhes_tattoo = detalhes_tattoo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDetalhesTattoo() {
        return detalhes_tattoo;
    }

    public void setDetalhesTattoo(String detalhes_tattoo) {
        this.detalhes_tattoo = detalhes_tattoo;
    }

    @Override
    public String toString() {
        return "\nId = " + id + "\n" +
               "Nome = " + nome + "\n" +
               "Idade = " + idade + "\n" +
               "Genero = " + genero + "\n" +
               "Observações = " + obs + "\n" +
               "Detalhes da tatuagem = " + detalhes_tattoo + "\n";
    }

}
