package com.example.project.study.domain.repositories;

import com.example.project.study.domain.model.empresa.EmpresaDesenvolvedora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpresaDesenvolvedoraRepository extends JpaRepository<EmpresaDesenvolvedora, Long> {
    EmpresaDesenvolvedora findByUuid(UUID uuidEmpresaDesenvolvedora);
}
