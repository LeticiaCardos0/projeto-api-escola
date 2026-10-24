package com.superdev.projetoapiescola.dto.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record AlunoAtualizar(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "Idade é obrigatório")
        @Size(min = 1, message = "A data de nascimento é obrigatória e não pode ser futura nem anterior a 120 anos da data atual e deve ter no mínimo 16 anos completos na data do cadastro.")
        Data data_nascimento

) {
}
