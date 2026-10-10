package com.superdev.projetoapiescola.controller;

import com.superdev.projetoapiescola.dto.unidade.UnidadeAtualizarDto;
import com.superdev.projetoapiescola.dto.unidade.UnidadeCriarDto;
import com.superdev.projetoapiescola.modelo.Professor;
import com.superdev.projetoapiescola.modelo.Unidade;
import com.superdev.projetoapiescola.service.UnidadeService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {
    private final UnidadeService service;

    public UnidadeController(UnidadeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Unidade> listar(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) String sigla,
                                @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return service.listar(nome, sigla, pageable);
    }

    @GetMapping("/{id}")
    public Unidade obterPorId(@PathVariable int id) {
        return service.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Unidade> criar(@RequestBody @Valid UnidadeCriarDto dado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dado));
    }

    @PutMapping("/{id}")
    public Unidade atualizar(@PathVariable int id, @RequestBody @Valid UnidadeAtualizarDto dado) {
        return service.atualizar(id, dado);
    }

}