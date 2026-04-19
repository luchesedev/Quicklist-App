package com.example.bdlistview.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Cliente implements Serializable {
    //atributos
    private Integer codigo;
    private String nome;
    private String email;

    //Construtor

    public Cliente() {

    }

    public Cliente(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        String mensagem = "\n|Código:|" + codigo +
                "\n|Nome:| " + nome +
                "\n|Email:| " + email+" \n";
        return mensagem;
    }
}


