package com.example.project.study.domain.model.biblioteca;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public FuncionarioDTO createFunc(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario(funcionarioDTO);
        Funcionario saveFuncionario = funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(saveFuncionario);
    }

    public List<Funcionario> saveFuncionarios(List<Funcionario> funcionarios) {
        return funcionarioRepository.saveAll(funcionarios);
    }
}