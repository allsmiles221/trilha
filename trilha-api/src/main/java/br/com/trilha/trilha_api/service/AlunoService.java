package br.com.trilha.trilha_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.trilha.trilha_api.dto.aluno.AlunoCreateRequest;
import br.com.trilha.trilha_api.dto.aluno.AlunoResponse;
import br.com.trilha.trilha_api.model.Aluno;
import br.com.trilha.trilha_api.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponse> listarTodos() {
        return alunoRepository.listarTodos()
                .stream()
                .map(AlunoResponse::new)
                .toList();
    }

    public Optional<AlunoResponse> buscarPorId(int id) {
        return alunoRepository.buscarPorId(id)
                .map(AlunoResponse::new);
    }

    public AlunoResponse cadastrar(AlunoCreateRequest request) {
        Optional<Aluno> alunoComMesmoNome = alunoRepository.buscarPorNome(request.getNome());

        if (alunoComMesmoNome.isPresent()) {
            throw new RuntimeException("Ja existe aluno cadastrado com esse nome.");
        }

        Aluno aluno = new Aluno(
                request.getNome(),
                request.getDataNascimento(),
                request.getCurso(),
                request.getEmail());

        Aluno alunoSalvo = alunoRepository.salvar(aluno);

        return new AlunoResponse(alunoSalvo);
    }
}
