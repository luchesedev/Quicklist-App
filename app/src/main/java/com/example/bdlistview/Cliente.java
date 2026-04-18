package com.example.bdlistview;

import androidx.annotation.NonNull;

public class Cliente {
    //atributos
    private int codigo;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
        String mensagem = "Código: " + codigo +
                "\n Nome: " + nome +
                "\n Email: " + email;
        return mensagem;
    }
}


