package com.example.project2.study.domain.model.Biblioteca.Repository;

import com.example.project2.study.domain.model.Biblioteca.Models.EnderecoBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndereBiblioRepository extends JpaRepository<EnderecoBiblioteca, Long> {
}
