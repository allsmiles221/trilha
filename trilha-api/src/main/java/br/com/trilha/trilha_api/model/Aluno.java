package br.com.trilha.trilha_api.model;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    
    private String curso;
    private String email;

    public Aluno(int id,String nome, LocalDate dataNascimento,String curso,String email){
        super(id,nome, dataNascimento);
        this.email=email;
        this.curso=curso;
    }

    public Aluno(String nome, LocalDate dataNascimento,String curso,String email){
        super(nome, dataNascimento);
        this.email=email;
        this.curso=curso;
    }

    public String getEmail() {
        return email;
    }
    public String getCurso() {
        return curso;
    }

   
}
