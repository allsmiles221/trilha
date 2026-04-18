package br.com.trilha.trilha_api.model;

import java.time.LocalDate;

public abstract class Pessoa {
    private int id;
    private String nome  ;
    private LocalDate dataNascimento;

    public Pessoa (String nome, LocalDate dataNascimento) {
    this.nome=nome;
    this.dataNascimento=dataNascimento;

    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getNome() {
        return nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;//ele não pode modificar id, é necessario que seja id unico para cada usuario, esse id vai ser pego no banco de dados
    }

}
