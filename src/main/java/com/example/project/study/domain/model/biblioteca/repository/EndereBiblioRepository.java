package com.example.project.study.domain.model.biblioteca.repository;

import com.example.project.study.domain.model.biblioteca.models.EnderecoBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndereBiblioRepository extends JpaRepository<EnderecoBiblioteca, Long> {
}
