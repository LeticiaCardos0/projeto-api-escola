package com.superdev.projetoapiescola.service;

import com.superdev.projetoapiescola.dto.professor.ProfessorAtualizarDto;
import com.superdev.projetoapiescola.dto.professor.ProfessorCriarDto;
import com.superdev.projetoapiescola.enums.ProfessorStatus;
import com.superdev.projetoapiescola.modelo.Professor;
import com.superdev.projetoapiescola.repositorio.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Page<Professor> listar(String nome, ProfessorStatus status, Pageable pageable) {
        String filtroNome = (nome == null || nome.isBlank()) ? null : nome.trim().toLowerCase();

        List<Professor> filtrados = repository.findAll(pageable.getSort())
                .stream()
                .filter(p -> filtroNome == null || p.getNome().toLowerCase().contains(filtroNome))
                .filter(p -> status == null || p.getProfessorStatus() == status)
                .toList();

        int inicio = (int) Math.min(pageable.getOffset(), filtrados.size());
        int fim = Math.min(inicio + pageable.getPageSize(), filtrados.size());

        return new PageImpl<>(filtrados.subList(inicio, fim), pageable, filtrados.size());
    }

    @Transactional
    public Professor criar(ProfessorCriarDto dado) {
        var professor = Professor.builder()
                .nome(dado.nome().trim())
                .professorStatus(ProfessorStatus.ATIVO)
                .build();
        return repository.save(professor);
    }

    // RF0201 + RG0203: só o nome
    @Transactional
    public Professor atualizar(int id, ProfessorAtualizarDto dado) {
        var professor = obterPorId(id);
        professor.setNome(dado.nome().trim());
        return repository.save(professor);
    }

    // RF0202
    @Transactional
    public Professor inativar(int id) {
        var professor = obterPorId(id);

        if (professor.getProfessorStatus() == ProfessorStatus.INATIVO) {
            return professor;
        }

        professor.setProfessorStatus(ProfessorStatus.INATIVO);
        return repository.save(professor);
    }

    @Transactional
    public Professor reativar(int id) {
        var professor = obterPorId(id);

        if (professor.getProfessorStatus() == ProfessorStatus.ATIVO) {
            return professor;
        }

        professor.setProfessorStatus(ProfessorStatus.ATIVO);
        return repository.save(professor);
    }

    public Professor obterPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }
}