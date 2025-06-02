package escola;

import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlunoValidationTest {

    @Test
    public void validateAluno() {
        AlunoValidation alunoValidation = new AlunoValidation();
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.serieAno = SerieAno.PRIMEIRO_ANO.getValor();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            alunoValidation.validateAluno(alunoDTO);
        });

        assertEquals("É Necessário informar a série do aluno", exception.getMessage());
    }
}
