package com.example.project2.study.domain.model.Biblioteca.Repository;

import com.example.project2.study.domain.model.Biblioteca.Models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
