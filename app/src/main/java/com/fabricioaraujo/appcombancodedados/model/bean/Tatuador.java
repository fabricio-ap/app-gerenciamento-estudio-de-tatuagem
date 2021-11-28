package com.fabricioaraujo.appcombancodedados.model.bean;

import java.io.Serializable;

public class Tatuador implements Serializable {

    private int id;
    private String nome;
    private String idade;
    private String genero;
    private String especialidade;
    private String horario;

    public Tatuador(int id) {
        this.id = id;
    }

    public Tatuador(String nome) {
        this.nome = nome;
    }

    public Tatuador(int id, String nome, String idade, String genero, String especialidade, String horario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.especialidade = especialidade;
        this.horario = horario;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "\nId = " + id + "\n" +
                "Nome = " + nome + "\n" +
                "Idade = " + idade + "\n" +
                "Genero = " + genero + "\n" +
                "Especialidade = " + especialidade + "\n" +
                "Horário = " + horario + "\n";
    }
}
