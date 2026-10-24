package com.superdev.projetoapiescola.repositorio;

import com.superdev.projetoapiescola.modelo.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Alunos, Integer> {
}
