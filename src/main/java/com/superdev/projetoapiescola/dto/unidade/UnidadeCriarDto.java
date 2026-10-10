package com.superdev.projetoapiescola.dto.unidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// RG0102
public record UnidadeCriarDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
        String nome,

        @NotBlank(message = "Sigla é obrigatória")
        @Schema(example = "Sigla deve ter de 2 a 10 caracteres alfanuméricos")
        String sigla
) {}