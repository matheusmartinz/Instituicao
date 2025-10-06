package escola;

import DataProviders.AlunoDTODataProvider;
import DataProviders.EnderecoDTODataProvider;
import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.CargaHorariaPorDisciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.ProfessorService;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

public class ProfessorServiceIT extends AbstractIntegrationTest {

    public static UUID uuidEscolaValido =
            UUID.fromString("e655c7e1-742a-42f4-9eba-b69e344c728c");

    @Autowired
    AlunoService alunoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProfessorService professorService;

    @Test
    public void addDisciplinaAluno() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Silva",
                "43325789300", EnderecoDTODataProvider.ofMaringa(), "josé@gmail.com", null
        );
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoDTO).isNotNull();
            s.assertThat(alunoDTO.getDisciplinas()).hasSize(0);
        });
        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        Pessoa alunoRecuperado = pessoaRepository.findByUuid(alunoSaved.getUuid());
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoRecuperado.getDisciplinas()).hasSize(3);
            s.assertThat(alunoRecuperado.getCargaHoraria()).isEqualTo(
                    CargaHorariaPorDisciplina.getCargaHoraria(List.of(Disciplina.MATEMATICA, Disciplina.INGLES, Disciplina.PORTUGUES), alunoRecuperado.getSerie().getValor())
            );
        });

        professorService.addDisciplinaAluno(alunoSaved, Disciplina.GEOGRAFIA);

        Pessoa alunoAtualizado = pessoaRepository.findByUuid(alunoSaved.getUuid());

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoAtualizado).isNotNull();
            s.assertThat(alunoAtualizado.getDisciplinas()).hasSize(4);
            s.assertThat(alunoAtualizado.getCargaHoraria()).isEqualTo(
                    CargaHorariaPorDisciplina.getCargaHoraria(alunoAtualizado.getDisciplinas(), alunoAtualizado.getSerie().getValor())
            );
        });
    }

}
