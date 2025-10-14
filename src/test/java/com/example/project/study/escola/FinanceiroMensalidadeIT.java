package com.example.project.study.escola;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.dataproviders.AlunoDTODataProvider;
import com.example.project.study.dataproviders.EnderecoDTODataProvider;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoService;
import com.example.project.study.domain.model.instituicao.financeiro.*;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

public class FinanceiroMensalidadeIT extends AbstractIntegrationIT {
    public static UUID uuidEscolaValido =
            UUID.fromString("5fbc6a76-fc94-40d9-a7e0-b5df856b637f");

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
    public void createBoletoAlunoIsento() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null, Boolean.TRUE);

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setStatusPagamento(StatusPagamento.EM_ABERTO);
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());
        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(mensalidadeRepository.count()).isEqualTo(1);
            acertaFofo.assertThat(mensalidadeFounded.getAlunoFK()).isEqualTo(alunoFounded.getUuid());
            acertaFofo.assertThat(mensalidadeFounded.getValorPago()).isEqualTo(BigDecimal.valueOf(800.00));
            acertaFofo.assertThat(mensalidadeFounded.getStatusPagamento()).isEqualTo(StatusPagamento.ISENTO);
            acertaFofo.assertThat(mensalidadeFounded.getDataEmissao()).isEqualTo(LocalDate.now());
            acertaFofo.assertThat(mensalidadeFounded.getDataVencimento()).isEqualTo(LocalDate.now().plusMonths(1L));
            acertaFofo.assertThat(alunoSaved.isBolsista()).isTrue();
        });
    }

    @Test
    public void createBoletoAlunoNotIsIsento() {
        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setStatusPagamento(StatusPagamento.EM_ABERTO);
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());
        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(mensalidadeRepository.count()).isEqualTo(1);
            acertaFofo.assertThat(alunoFounded.getBolsista()).isFalse();
            acertaFofo.assertThat(mensalidadeFounded.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800.00));
        });
    }


    @Test
    public void pagarBoletoAtrasado() {
        long before = mensalidadeRepository.count();
        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());
        long after = mensalidadeRepository.count();

        // Consultar se mensalidade foi criada e alterar a data de vencimento
        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());
        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(after).isEqualTo(before + 1);
            acertaFofo.assertThat(mensalidadeFounded.getStatusPagamento().isAberto()).isTrue();
        });
        mensalidadeFounded.setDataVencimento(LocalDate.now().minusDays(2L));
        mensalidadeRepository.save(mensalidadeFounded);

        // Consultar meu boleto atrasado
        MensalidadeDTO consultaMensalidade = mensalidadeService.consultaMensalidade(MensalidadeDTO.of(mensalidadeFounded), alunoFounded.getUuid());

        // Pagar boleto
        MensalidadeDTO pagarMensalidade = mensalidadeService.pagarBoleto(consultaMensalidade, alunoFounded.getUuid());

        // Consultar se os valores depois de pagar estarão corretos
        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(pagarMensalidade.getValorPagamento().setScale(2, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(800.00).setScale(2, RoundingMode.HALF_UP));
            acertaFofo.assertThat(pagarMensalidade.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            acertaFofo.assertThat(pagarMensalidade.getDataPagamento()).isEqualTo(LocalDate.now());
            MensalidadeJurosMultaDTO mensalidadeJurosMultaDTO = MensalidadeAlunoSerieJurosMulta.VALORES_MENSALIDADES.get(SerieAno.QUARTO_ANO);
            acertaFofo.assertThat(pagarMensalidade.getMulta()).isEqualTo(mensalidadeJurosMultaDTO.getValorMulta());
            acertaFofo.assertThat(pagarMensalidade.getJuros()).isEqualTo(mensalidadeJurosMultaDTO.getValorJuros());
            acertaFofo.assertThat(pagarMensalidade.getValorPago()).isEqualTo(BigDecimal.valueOf(810.66));
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Valor de juros deve ser maior que 0 e menor ou igual a 100")
    public void valorJurosMenorZero() {
        new MensalidadeDTO().setJuros(BigDecimal.valueOf(-1));
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Valor de juros deve ser maior que 0 e menor ou igual a 100")
    public void valorJurosMaiorQueCem() {
        new MensalidadeDTO().setJuros(BigDecimal.valueOf(100.01));
    }


    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Nāo possuímos a 9° série cadastrada.")
    public void findMensalidadeNotSerie() {
        MensalidadeAlunoSerieJurosMulta.findMensalidadeBySerie(SerieAno.NONO_ANO);
    }

    @Test
    public void pagamentoEmDia() {
        AlunoDTO alunoDTO = createGenericAlunoDTONotIsento();

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid());

        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());

        MensalidadeDTO mensalidadeConsultada = mensalidadeService.consultaMensalidade(MensalidadeDTO.of(mensalidadeFounded), alunoFounded.getUuid());

        MensalidadeDTO mensalidadePagamento = mensalidadeService.pagarBoleto(mensalidadeConsultada, alunoFounded.getUuid());

        SoftAssertions.assertSoftly(sejaSeDeusQuiser -> {
            sejaSeDeusQuiser.assertThat(mensalidadePagamento.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
            sejaSeDeusQuiser.assertThat(mensalidadePagamento.getValorPago()).isEqualTo(BigDecimal.valueOf(800.00));
            sejaSeDeusQuiser.assertThat(mensalidadeConsultada.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800.00));
        });
    }
}
