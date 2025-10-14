package com.example.project.study.escola;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.dataproviders.AlunoDTODataProvider;
import com.example.project.study.dataproviders.EnderecoDTODataProvider;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoService;
import com.example.project.study.domain.model.instituicao.financeiro.MensalidadeDTO;
import com.example.project.study.domain.model.instituicao.financeiro.MensalidadeRepository;
import com.example.project.study.domain.model.instituicao.financeiro.MensalidadeService;
import com.example.project.study.domain.model.instituicao.financeiro.StatusPagamento;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PagamentoRestControllerIT extends AbstractIntegrationIT {
    private static final String BASE_URL = "/pagamento";

    private static final UUID uuidEscolaValido = UUID.fromString("2a5089bd-2e62-4b8d-b3eb-f35bc2f328dd");

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MensalidadeRepository mensalidadeRepository;
    @Autowired
    private MensalidadeService mensalidadeService;

    private static AlunoDTO createGenericAlunoDTONotIsento() {
        return AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null, Boolean.FALSE);
    }


    @Test
    @SneakyThrows
    public void pagamentoAntecipadoRestController() {
        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        String content = objectMapper.writeValueAsString(mensalidadeCreatedDTO);

        String toReturnStringPagamento = postRequest(BASE_URL + "/" + alunoSaved.getUuid(), content, status().isOk()).andReturn().getResponse().getContentAsString();

        MensalidadeDTO toReturnDTO = objectMapper.readValue(toReturnStringPagamento, MensalidadeDTO.class);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(mensalidadeDTO.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
        });
    }
}
