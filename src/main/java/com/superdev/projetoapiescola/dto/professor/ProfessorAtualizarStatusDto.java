package com.superdev.projetoapiescola.dto.professor;

import com.superdev.projetoapiescola.enums.ProfessorStatus;
import jakarta.validation.constraints.NotNull;

public record ProfessorAtualizarStatusDto(
        @NotNull(message = "Status é obrigatório")   // enum usa @NotNull, não @NotBlank
        ProfessorStatus professorStatus
){}
