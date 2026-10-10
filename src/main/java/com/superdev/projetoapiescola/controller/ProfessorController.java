package com.superdev.projetoapiescola.controller;

import com.superdev.projetoapiescola.dto.professor.ProfessorAtualizarDto;
import com.superdev.projetoapiescola.dto.professor.ProfessorCriarDto;
import com.superdev.projetoapiescola.enums.ProfessorStatus;
import com.superdev.projetoapiescola.modelo.Professor;
import com.superdev.projetoapiescola.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professores")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Professor> listar(@RequestParam(required = false) String nome,
                                  @RequestParam(required = false) ProfessorStatus status,
                                  @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return service.listar(nome, status, pageable);
    }

    @GetMapping("/{id}")
    public Professor obterPorId(@PathVariable int id) {
        return service.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody @Valid ProfessorCriarDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable int id, @RequestBody @Valid ProfessorAtualizarDto dado) {
        return service.atualizar(id, dado);
    }

    @PostMapping("/{id}/inativar")
    @Operation(summary = "Inativar professor")
    public Professor inativar(@PathVariable int id) {
        return service.inativar(id);
    }

    @PostMapping("/{id}/reativar")
    @Operation(summary = "Reativar professor")
    public Professor reativar(@PathVariable int id) {
        return service.reativar(id);
    }
}