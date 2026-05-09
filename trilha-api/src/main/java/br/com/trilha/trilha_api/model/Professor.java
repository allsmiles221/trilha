package br.com.trilha.trilha_api.model;
import java.time.LocalDate;



public class Professor extends Pessoa {
    private String descricao;
    private String curso;
    private boolean status;

    public Professor (int id,String nome,LocalDate dataNacimento,String descricao, String curso, boolean status){
        super( id,nome,dataNacimento);
        this.curso=curso;
        this.descricao=descricao;
        this.status=status;
    }

    public Professor (String nome,LocalDate dataNacimento,String descricao, String curso, boolean status){
        super( nome,dataNacimento);
        this.curso=curso;
        this.descricao=descricao;
        this.status=status;
    }
   
    public String getCurso() {
        return curso;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public boolean getstatus(){
        return status;
    }


    public void setCurso(String curso) {
        this.curso = curso;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


    
}
