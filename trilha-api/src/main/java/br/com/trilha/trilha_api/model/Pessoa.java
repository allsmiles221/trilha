package br.com.trilha.trilha_api.model;

import java.time.LocalDate;

public class Pessoa {
    private String nome  ;
    private LocalDate dataNascimento;

    public Pessoa (String nome, LocalDate dataNascimento) {
    this.nome=nome;
    this.dataNascimento=dataNascimento;

    }

    public LocalDate getDatanascimento() {
        return dataNascimento;
    }
    public String getNome() {
        return nome;
    }

    public void setDatanascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
