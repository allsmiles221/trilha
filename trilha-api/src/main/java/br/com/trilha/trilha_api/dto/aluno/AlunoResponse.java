package br.com.trilha.trilha_api.dto.aluno;

import java.time.LocalDate;

import br.com.trilha.trilha_api.model.Aluno;

public class AlunoResponse {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String curso;
    private String email;

    public AlunoResponse() {
    }

    public AlunoResponse(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.dataNascimento = aluno.getDataNascimento();
        this.curso = aluno.getCurso();
        this.email = aluno.getEmail();
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
