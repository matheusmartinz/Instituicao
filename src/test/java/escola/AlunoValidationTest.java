package escola;

import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlunoValidationTest extends AbstractIntegrationTest {

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
