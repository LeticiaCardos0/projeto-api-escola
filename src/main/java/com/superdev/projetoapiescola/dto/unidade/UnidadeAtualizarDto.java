package com.superdev.projetoapiescola.dto.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Sem campo "sigla": se vier no JSON, é ignorado (RG0101)
public record UnidadeAtualizarDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
        String nome
) {}