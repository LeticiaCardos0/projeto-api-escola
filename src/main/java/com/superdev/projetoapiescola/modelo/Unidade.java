package com.superdev.projetoapiescola.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidades")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 10, updatable = false)
    private String sigla;
}