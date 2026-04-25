package br.com.trilha.trilha_api.repository;
import java.util.List;
import java.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.trilha.trilha_api.model.Aluno;

@Repository
public class AlunoRepository {
    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Aluno> listarTodos(){
        String sql="""
                SELECT id, nome, dataNascimento, curso, email
        FROM aluno
                """;
                return jdbcTemplate.query(sql, (rs,rowNum) -> {
                    Integer id =rs.getInt("id");
                    String nome= rs.getString("nome");
                    LocalDate dataNascimento= rs.getDate("dataNascimento").toLocalDate();
                    String curso= rs.getString("curso");
                    String email= rs.getString("email");

                    return new Aluno(id,nome,dataNascimento,curso, email);
                });
        
    }

}
