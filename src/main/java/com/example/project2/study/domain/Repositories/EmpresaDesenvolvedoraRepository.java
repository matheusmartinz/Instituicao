package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Empresa.EmpresaDesenvolvedora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpresaDesenvolvedoraRepository extends JpaRepository<EmpresaDesenvolvedora, Long> {
    EmpresaDesenvolvedora findByUuid(UUID uuidEmpresaDesenvolvedora);
}
