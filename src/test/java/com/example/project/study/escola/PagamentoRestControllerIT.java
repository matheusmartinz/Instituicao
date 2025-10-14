package com.example.project.study.escola;

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
import com.example.project.study.domain.model.instituicao.financeiro.*;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PagamentoRestControllerIT extends AbstractIntegrationIT {
    private static final String BASE_URL = "/pagamento";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MensalidadeRepository mensalidadeRepository;
    @Autowired
    private EscolaService escolaService;
    @Autowired
    private MensalidadeService mensalidadeService;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private SalaService salaService;
    @Autowired
    private PagamentoService pagamentoService;

    private static AlunoDTO createGenericAlunoDTONotIsento() {
        return AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null, Boolean.FALSE);
    }

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


    @Test
    @SneakyThrows
    public void pagamentoEmDiaRestController() {
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

//        mensalidadeCreatedDTO.setFormaPagamento(FormaPagamento.PIX);

        String content = objectMapper.writeValueAsString(mensalidadeCreatedDTO);

        String toReturnStringPagamento = postRequest(BASE_URL + "/" + alunoSaved.getUuid(), content, status().isOk()).andReturn().getResponse().getContentAsString();

        MensalidadeDTO toReturnDTO = objectMapper.readValue(toReturnStringPagamento, new TypeReference<MensalidadeDTO>() {
        });

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoFounded.getSerie()).isEqualTo(SerieAno.QUARTO_ANO);

            s.assertThat(toReturnDTO.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            s.assertThat(toReturnDTO.getDataPagamento()).isBefore(mensalidadeCreatedDTO.getDataVencimento());
            s.assertThat(toReturnDTO.getValorPago()).isEqualTo(BigDecimal.valueOf(720).setScale(2, RoundingMode.HALF_UP));
            s.assertThat(toReturnDTO.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, RoundingMode.HALF_UP));
            s.assertThat(toReturnDTO.getMulta()).isEqualTo(BigDecimal.valueOf(1.3));
            s.assertThat(toReturnDTO.getJuros()).isEqualTo(BigDecimal.valueOf(0.13));
//            s.assertThat(toReturnDTO.getFormaPagamento()).isEqualTo(FormaPagamento.PIX);
        });

        Mensalidade mensalidadeComFormaDePagamento = mensalidadeRepository.findByUuid(toReturnDTO.getUuid());

        SoftAssertions.assertSoftly(acertaFofo -> {
//            acertaFofo.assertThat(mensalidadeComFormaDePagamento.getFormaPagamento()).isEqualTo(FormaPagamento.PIX);
        });
    }

    @Test
    @SneakyThrows
    public void PagamentoService(){
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(mensalidadeCreatedDTO.getStatusPagamento()).isEqualTo(StatusPagamento.EM_ABERTO);
            acertaFofo.assertThat(mensalidadeCreatedDTO.getDataPagamento()).isNull();
            acertaFofo.assertThat(mensalidadeCreatedDTO.getValorPago()).isEqualTo(BigDecimal.ZERO);
            acertaFofo.assertThat(mensalidadeCreatedDTO.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, RoundingMode.HALF_UP));
            acertaFofo.assertThat(mensalidadeCreatedDTO.getMulta()).isEqualTo(BigDecimal.valueOf(1.3));
            acertaFofo.assertThat(mensalidadeCreatedDTO.getJuros()).isEqualTo(BigDecimal.valueOf(0.13));
        });
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setFormaPagamento(FormaPagamento.PIX);
        pagamentoDTO.setDataPagamento(LocalDate.now());
        pagamentoDTO.setStatusPagamento(StatusPagamento.PAGO);
        pagamentoDTO.setMensalidadeFK(mensalidadeCreatedDTO.getUuid());

        PagamentoDTO toReturnPagamentoDTO = pagamentoService.pagarMensalidade(mensalidadeCreatedDTO, alunoSaved.getUuid(),pagamentoDTO);

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(toReturnPagamentoDTO.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            acertaFofo.assertThat(toReturnPagamentoDTO.getDataPagamento()).isEqualTo(LocalDate.now());
            acertaFofo.assertThat(toReturnPagamentoDTO.getFormaPagamento()).isEqualTo(FormaPagamento.PIX);
            acertaFofo.assertThat(toReturnPagamentoDTO.getValorPago()).isEqualTo(BigDecimal.valueOf(720).setScale(2, RoundingMode.HALF_UP));
        });
    }

}
