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
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class FinanceiroServiceIT extends AbstractIntegrationIT {
    @Autowired
    FinanceiroService financeiroService;
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

    @Test
    public void createFinanceiro() {
        EscolaDTO createdEscola = getEscolaDTO();

        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, createdEscola.getUuid());
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        //MENSALIDADE CRIADA

        FinanceiroDTO financeiroDTO = FinanceiroDTO.of(mensalidadeCreatedDTO);
        financeiroDTO.getPagamentoDTO().setFormaPagamento(FormaPagamento.PIX);
        financeiroDTO.getPagamentoDTO().setMensalidadeFK(mensalidadeCreatedDTO.getUuid());

         FinanceiroDTO toReturnPagamentoDTO =  financeiroService.pagarMensalidade(financeiroDTO);

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(toReturnPagamentoDTO.getPagamentoDTO().getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            acertaFofo.assertThat(toReturnPagamentoDTO.getMensalidadeDTO().getDataPagamento()).isEqualTo(LocalDate.now());
            acertaFofo.assertThat(toReturnPagamentoDTO.getPagamentoDTO().getFormaPagamento()).isEqualTo(FormaPagamento.PIX);
            acertaFofo.assertThat(toReturnPagamentoDTO.getPagamentoDTO().getValorPago()).isEqualTo(BigDecimal.valueOf(720).setScale(2, RoundingMode.HALF_UP));
        });
    }
}
