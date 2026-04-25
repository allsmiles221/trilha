package br.com.trilha.trilha_api.dto.aluno;

import java.time.LocalDate;

public class AlunoCreateRequest {
    private String nome;
    private LocalDate dataNascimento;
    private String curso;
    private String email;

    public AlunoCreateRequest(){
       
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public String getCurso() {
        return curso;
    }
    public String getEmail() {
        return email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
}
