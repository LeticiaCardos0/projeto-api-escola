package com.superdev.projetoapiescola.service;

import com.superdev.projetoapiescola.dto.unidade.UnidadeAtualizarDto;
import com.superdev.projetoapiescola.dto.unidade.UnidadeCriarDto;
import com.superdev.projetoapiescola.modelo.Unidade;
import com.superdev.projetoapiescola.repositorio.UnidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class UnidadeService {
    private final UnidadeRepository repository;

    public UnidadeService(UnidadeRepository repository) {
        this.repository = repository;
    }

    // RF0102 + RF0104
    public Page<Unidade> listar(String nome, String sigla, Pageable pageable) {
        String filtroNome = normalizarFiltro(nome);
        String filtroSigla = normalizarFiltro(sigla);

        List<Unidade> filtradas = repository.findAll(pageable.getSort())
                .stream()
                .filter(u -> filtroNome == null || u.getNome().toLowerCase().contains(filtroNome))
                .filter(u -> filtroSigla == null || u.getSigla().toLowerCase().contains(filtroSigla))
                .toList();

        int inicio = (int) Math.min(pageable.getOffset(), filtradas.size());
        int fim = Math.min(inicio + pageable.getPageSize(), filtradas.size());

        return new PageImpl<>(filtradas.subList(inicio, fim), pageable, filtradas.size());
    }

    @Transactional
    public Unidade criar(UnidadeCriarDto dado) {
        String nome = dado.nome().trim();
        String sigla = dado.sigla().trim().toUpperCase();

        List<Unidade> todas = repository.findAll();

        boolean siglaEmUso = todas.stream()
                .anyMatch(u -> u.getSigla().equalsIgnoreCase(sigla));
        if (siglaEmUso) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma unidade com a sigla " + sigla);
        }

        boolean nomeEmUso = todas.stream()
                .anyMatch(u -> u.getNome().equalsIgnoreCase(nome));
        if (nomeEmUso) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma unidade com o nome " + nome);
        }

        var unidade = Unidade.builder()
                .nome(nome)
                .sigla(sigla)
                .build();
        return repository.save(unidade);
    }

    @Transactional
    public Unidade atualizar(int id, UnidadeAtualizarDto dado) {
        var unidade = obterPorId(id);
        String nome = dado.nome().trim();

        boolean nomeEmUso = repository.findAll().stream()
                .anyMatch(u -> u.getNome().equalsIgnoreCase(nome)
                        && !Objects.equals(u.getId(), id));
        if (nomeEmUso) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma unidade com o nome " + nome);
        }

        unidade.setNome(nome);
        return repository.save(unidade);
    }

    public Unidade obterPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada"));
    }

    private String normalizarFiltro(String valor) {
        return (valor == null || valor.isBlank()) ? null : valor.trim().toLowerCase();
    }
}