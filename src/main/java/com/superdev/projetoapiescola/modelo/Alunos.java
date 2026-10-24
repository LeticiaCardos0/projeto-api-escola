package com.superdev.projetoapiescola.modelo;

import com.superdev.projetoapiescola.enums.ProfessorStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Alunos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true,  length = 15)
    private String cpf;

    @Column(nullable = false,  length = 150)
    private String nome;

    @Column(nullable = false,  length = 150)
    private Data data_nascimento;

}
