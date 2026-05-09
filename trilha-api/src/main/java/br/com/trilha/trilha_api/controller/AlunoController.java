package br.com.trilha.trilha_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trilha.trilha_api.dto.aluno.AlunoCreateRequest;
import br.com.trilha.trilha_api.dto.aluno.AlunoResponse;
import br.com.trilha.trilha_api.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoResponse> listarTodos() {
        return alunoService.listarTodos();
    }

    @PostMapping
    public AlunoResponse criar(@RequestBody AlunoCreateRequest request) {
        return alunoService.cadastrar(request);
    }
}
