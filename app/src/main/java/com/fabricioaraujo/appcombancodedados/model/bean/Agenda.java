package com.fabricioaraujo.appcombancodedados.model.bean;

import java.io.Serializable;

public class Agenda implements Serializable {

    private int id;
    private int id_cliente;
    private int id_tatuador;
    private String horario;
    private String valor;
    private Cliente cliente;
    private Tatuador tatuador;

    public Agenda(int id) {
        this.id = id;
    }

    public Agenda(String horario) {
        this.horario = horario;
    }

    public Agenda(int id, int id_cliente, int id_tatuador, String horario, String valor) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_tatuador = id_tatuador;
        this.horario = horario;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getIdTatuador() {
        return id_tatuador;
    }

    public void setIdTatuador(int id_tatuador) {
        this.id_tatuador = id_tatuador;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    @Override
    public String toString() {
        return "\nId = " + id + "\n" +
               "Cliente = " + cliente.getNome() + "\n" +
               "Tatuador = " + tatuador.getNome() + "\n" +
               "Horário = " + horario + "\n" +
               "Valor = " + valor + '\n';
    }

}
