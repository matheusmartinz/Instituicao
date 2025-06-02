package com.example.project2.study.domain.model.Biblioteca.Repository;

import com.example.project2.study.domain.model.Biblioteca.Models.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
