package com.example.project2.study.domain.model.Empresa;

import com.example.project2.study.domain.Repositories.EmpresaDesenvolvedoraRepository;
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
