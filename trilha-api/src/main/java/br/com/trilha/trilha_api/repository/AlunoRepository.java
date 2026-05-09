package br.com.trilha.trilha_api.repository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.trilha.trilha_api.model.Aluno;

@Repository
public class AlunoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Aluno> alunoRowMapper = (rs, rowNum) -> {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();
        String curso = rs.getString("curso");
        String email = rs.getString("email");

        return new Aluno(id, nome, dataNascimento, curso, email);
    };

    public AlunoRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Aluno> listarTodos(){
        String sql="""
                SELECT id, nome, dataNascimento, curso, email
        FROM aluno
                """;
        return jdbcTemplate.query(sql, alunoRowMapper);
    }

    public Aluno salvar(Aluno aluno) {
        String sql = """
                INSERT INTO aluno (nome, dataNascimento, curso, email)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, aluno.getNome());
                    ps.setDate(2, Date.valueOf(aluno.getDataNascimento()));
                    ps.setString(3, aluno.getCurso());
                    ps.setString(4, aluno.getEmail());
                    return ps;
                },
                keyHolder);

        int idGerado = keyHolder.getKey().intValue();

        return new Aluno(
                idGerado,
                aluno.getNome(),
                aluno.getDataNascimento(),
                aluno.getCurso(),
                aluno.getEmail());
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        String sql = """
                SELECT id, nome, dataNascimento, curso, email
                FROM aluno
                WHERE email = ?
                """;

        return jdbcTemplate.query(sql, alunoRowMapper, email).stream().findFirst();
    }

    public Optional<Aluno> buscarPorId(int id) {
        String sql = """
                SELECT id, nome, dataNascimento, curso, email
                FROM aluno
                WHERE id = ?
                """;

        return jdbcTemplate.query(sql, alunoRowMapper, id).stream().findFirst();
    }

    public Optional<Aluno> buscarPorNome(String nome) {
        String sql = """
                SELECT id, nome, dataNascimento, curso, email
                FROM aluno
                WHERE nome= ?
                """;

        return jdbcTemplate.query(sql, alunoRowMapper, nome).stream().findFirst();
    }

}
