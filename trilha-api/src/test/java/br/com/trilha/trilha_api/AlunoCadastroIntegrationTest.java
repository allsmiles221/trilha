package br.com.trilha.trilha_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import br.com.trilha.trilha_api.dto.aluno.AlunoCreateRequest;
import br.com.trilha.trilha_api.dto.aluno.AlunoResponse;
import br.com.trilha.trilha_api.model.Aluno;
import br.com.trilha.trilha_api.repository.AlunoRepository;
import br.com.trilha.trilha_api.service.AlunoService;

@SpringBootTest
@ActiveProfiles("test")
class AlunoCadastroIntegrationTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limparBanco() {
        jdbcTemplate.update("DELETE FROM aluno");
        jdbcTemplate.execute("ALTER TABLE aluno ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    void deveSalvarAlunoNoRepositoryComIdGerado() {
        Aluno aluno = new Aluno(
                "Ana Silva",
                LocalDate.of(2001, 5, 10),
                "Java",
                "ana.silva@email.com");

        Aluno alunoSalvo = alunoRepository.salvar(aluno);

        assertNotNull(alunoSalvo);
        assertTrue(alunoSalvo.getId() > 0);
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        AlunoCreateRequest request = criarRequest(
                "Bruno Santos",
                LocalDate.of(2000, 3, 20),
                "Spring Boot",
                "bruno.santos@email.com");

        AlunoResponse response = alunoService.cadastrar(request);

        assertTrue(response.getId() > 0);
        assertEquals(request.getNome(), response.getNome());
        assertEquals(request.getDataNascimento(), response.getDataNascimento());
        assertEquals(request.getCurso(), response.getCurso());
        assertEquals(request.getEmail(), response.getEmail());
    }

    @Test
    void deveCadastrarAlunoNoServiceComDadosIguaisAoRequest() {
        AlunoCreateRequest request = criarRequest(
                "Carla Souza",
                LocalDate.of(1999, 8, 15),
                "Banco de Dados",
                "carla.souza@email.com");

        AlunoResponse response = alunoService.cadastrar(request);

        assertTrue(response.getId() > 0);
        assertEquals("Carla Souza", response.getNome());
        assertEquals(LocalDate.of(1999, 8, 15), response.getDataNascimento());
        assertEquals("Banco de Dados", response.getCurso());
        assertEquals("carla.souza@email.com", response.getEmail());
    }

    @Test
    void deveLancarExcecaoAoCadastrarAlunoComNomeDuplicado() {
        AlunoCreateRequest primeiroAluno = criarRequest(
                "Daniel Lima",
                LocalDate.of(2002, 1, 5),
                "Java",
                "daniel.lima@email.com");
        AlunoCreateRequest alunoDuplicado = criarRequest(
                "Daniel Lima",
                LocalDate.of(2003, 2, 6),
                "Spring Boot",
                "outro.daniel@email.com");

        alunoService.cadastrar(primeiroAluno);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> alunoService.cadastrar(alunoDuplicado));

        assertEquals("Ja existe aluno cadastrado com esse nome.", exception.getMessage());
    }

    private AlunoCreateRequest criarRequest(String nome, LocalDate dataNascimento, String curso, String email) {
        AlunoCreateRequest request = new AlunoCreateRequest();
        request.setNome(nome);
        request.setDataNascimento(dataNascimento);
        request.setCurso(curso);
        request.setEmail(email);
        return request;
    }
}
