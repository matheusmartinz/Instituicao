package com.example.project.study.escola;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlunoValidationIT extends AbstractIntegrationIT {

    @Autowired
    private AlunoValidation alunoValidation;

    @Test
    public void validateAlunoSerieIsNull() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setSerieAno(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            alunoValidation.validateAluno(alunoDTO);
        });

        assertEquals("É necessário informar a série do aluno.", exception.getMessage());
    }
}
