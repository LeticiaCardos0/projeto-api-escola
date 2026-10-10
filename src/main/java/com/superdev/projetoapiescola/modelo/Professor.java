package com.superdev.projetoapiescola.modelo;

import com.superdev.projetoapiescola.enums.ProfessorStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professores")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "professor_status", nullable = false, length = 20)
    private ProfessorStatus professorStatus;
}

