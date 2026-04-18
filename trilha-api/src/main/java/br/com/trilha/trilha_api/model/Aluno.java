package br.com.trilha.trilha_api.model;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    
    private String curso;
    private String email;

    public Aluno(String nome, LocalDate dataNascimento,String matricula,String email){
        super(nome, dataNascimento);
        this.email=email;
        this.curso=matricula;
    }

    public String getEmail() {
        return email;
    }
    public String getCurso() {
        return curso;
    }

   
}
