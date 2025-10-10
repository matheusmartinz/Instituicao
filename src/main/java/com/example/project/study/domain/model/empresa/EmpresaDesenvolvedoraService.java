package com.example.project.study.domain.model.empresa;

import com.example.project.study.domain.repositories.EmpresaDesenvolvedoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmpresaDesenvolvedoraService {

    @Autowired
    EmpresaDesenvolvedoraRepository empresaDesenvolvedoraRepository;

    public EmpresaDesenvolvedoraDTO createEmpresaDesenvolvedora(EmpresaDesenvolvedoraDTO empresaDesenvolvedoraDTO) {
        EmpresaDesenvolvedora empresaDesenvolvedora = new EmpresaDesenvolvedora(empresaDesenvolvedoraDTO);
        EmpresaDesenvolvedora savedDesenvolvedora = save(empresaDesenvolvedora);
        return new EmpresaDesenvolvedoraDTO(savedDesenvolvedora);
    }

    private EmpresaDesenvolvedora save(EmpresaDesenvolvedora empresaDesenvolvedora) {
        empresaDesenvolvedora.setUuid(UUID.randomUUID());
        return empresaDesenvolvedoraRepository.save(empresaDesenvolvedora);
    }
}
