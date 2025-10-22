package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.dataproviders.AlunoDTODataProvider;
import com.example.project.study.dataproviders.EnderecoDTODataProvider;
import com.example.project.study.dataproviders.EscolaDTODataProvider;
import com.example.project.study.domain.model.instituicao.escola.EscolaDTO;
import com.example.project.study.domain.model.instituicao.escola.EscolaService;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoService;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaDTO;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaService;
import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PagamentoRestControllerIT extends AbstractIntegrationIT {
    private static final String BASE_URL = "/pagamento";
    @Autowired
    private EscolaService escolaService;
    @Autowired
    private SalaService salaService;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MensalidadeService mensalidadeService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private FinanceiroService financeiroService;

    private EscolaDTO getEscolaDTO() {
        EnderecoDTO maringa = EnderecoDTODataProvider.ofMaringa();

        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("Joao Segundo", maringa);

        EscolaDTO escolaDTOCreated = escolaService.createEscola(escolaDTO);

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNumeroSala("120");
        salaDTO.setSerieAno(SerieAno.QUARTO_ANO);
        salaService.createSala(salaDTO, escolaDTOCreated.getUuid());

        return escolaDTOCreated;
    }

    private static AlunoDTO createGenericAlunoDTONotIsento() {
        return AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null, Boolean.FALSE);
    }

    private static FinanceiroDTO getGenericFinanceiroDTO(MensalidadeDTO mensalidadeCreatedDTO) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setFormaPagamento(FormaPagamento.PIX);
        pagamentoDTO.setMensalidadeFK(mensalidadeCreatedDTO.getUuid());

        FinanceiroDTO financeiroDTO = new FinanceiroDTO();
        financeiroDTO.setPagamentoDTO(pagamentoDTO);
        financeiroDTO.setMensalidadeDTO(mensalidadeCreatedDTO);
        return financeiroDTO;
    }

    @Test
    @SneakyThrows
    public void restControllerPostPagamento() {
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        FinanceiroDTO financeiroDTO = getGenericFinanceiroDTO(mensalidadeCreatedDTO);

        String content = objectMapper.writeValueAsString(financeiroDTO);

        String contentAsString = postRequest("/pagamento", content, status().isOk()).andReturn().getResponse().getContentAsString();

        FinanceiroDTO toReturnResponseFinanceiroDTO = objectMapper.readValue(contentAsString, new TypeReference<FinanceiroDTO>() {
        });

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(toReturnResponseFinanceiroDTO.getPagamentoDTO().getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            acertaFofo.assertThat(toReturnResponseFinanceiroDTO.getMensalidadeDTO().getDataPagamento()).isEqualTo(LocalDate.now());
            acertaFofo.assertThat(toReturnResponseFinanceiroDTO.getPagamentoDTO().getFormaPagamento()).isEqualTo(FormaPagamento.PIX);
            acertaFofo.assertThat(toReturnResponseFinanceiroDTO.getPagamentoDTO().getValorPago()).isEqualTo(BigDecimal.valueOf(720).setScale(2, RoundingMode.HALF_UP));
            acertaFofo.assertThat(pagamentoRepository.count()).isEqualTo(1);
            acertaFofo.assertThat(mensalidadeCreatedDTO.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, RoundingMode.HALF_UP));
        });
    }

    @Test
    @SneakyThrows
    public void restControllerDeletePagamento() {
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        FinanceiroDTO financeiroDTO = getGenericFinanceiroDTO(mensalidadeCreatedDTO);

        FinanceiroDTO pagamentoCreated = financeiroService.pagarMensalidade(financeiroDTO);

        long beforeDeleted = pagamentoRepository.count();
        deleteRequest(BASE_URL + "/" + pagamentoCreated.getPagamentoDTO().getUuid(), status().isOk())
                .andReturn().getResponse().getContentAsString();
        long afterDeleted = pagamentoRepository.count();

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(beforeDeleted).isEqualTo(1);
            acertaFofo.assertThat(afterDeleted).isEqualTo(0);
        });
    }

    @Test(expectedExceptions = EntidadeNaoEncontradaException.class,
            expectedExceptionsMessageRegExp = "Pagamento não encontrado.")
    @SneakyThrows
    public void restControllerDeletePagamentoUUIDNull() {
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        FinanceiroDTO financeiroDTO = getGenericFinanceiroDTO(mensalidadeCreatedDTO);

        financeiroService.pagarMensalidade(financeiroDTO);

        UUID uuidFake = UUID.randomUUID();

        deleteRequest(BASE_URL + "/" + uuidFake, status().isOk()).andExpect(result -> {
            Assertions.assertThat
        });
    }
}
