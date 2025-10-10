package com.example.project.study.domain.model.biblioteca.repository;

import com.example.project.study.domain.model.biblioteca.models.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
