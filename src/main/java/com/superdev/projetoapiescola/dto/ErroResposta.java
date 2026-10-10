package com.superdev.projetoapiescola.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ErroResposta (
        @Schema(example = "nao_encantrado")
        String codigo,

        @Schema(example = "Catogoria nao encontrada")
        String mensagem,

        List<?> detalhes
){}