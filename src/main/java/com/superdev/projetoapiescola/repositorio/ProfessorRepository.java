package com.superdev.projetoapiescola.repositorio;

import com.superdev.projetoapiescola.modelo.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
}
