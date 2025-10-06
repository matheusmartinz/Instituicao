package escola;

import DataProviders.AlunoDTODataProvider;
import DataProviders.EnderecoDTODataProvider;
import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Financeiro.MensalidadeDTO;
import com.example.project2.study.domain.model.Instituicao.Financeiro.MensalidadeRepository;
import com.example.project2.study.domain.model.Instituicao.Financeiro.MensalidadeService;
import com.example.project2.study.domain.model.Instituicao.Financeiro.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class FinanceiroMensalidadeIT extends AbstractIntegrationTest {
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    public static UUID uuidEscolaValido =
            UUID.fromString("e655c7e1-742a-42f4-9eba-b69e344c728c");

    @Autowired
    private MensalidadeService mensalidadeService;

    @Test
    public void mensalidade() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null);

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setStatusPagamento(StatusPagamento.EM_ABERTO);
        mensalidadeDTO.setAluno(alunoSaved);
        mensalidadeDTO.setValorFinal(BigDecimal.valueOf(880.90));
        mensalidadeDTO.setDataPagamento(LocalDate.now());
        mensalidadeDTO.setDataVencimento(LocalDate.of(2025,12,10));

        mensalidadeService.createMensalidade(alunoSaved.getUuid());
    }
}
