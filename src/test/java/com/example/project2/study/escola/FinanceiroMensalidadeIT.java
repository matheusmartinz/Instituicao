package com.example.project2.study.escola;

import com.example.project2.study.AbstractIntegrationIT;
import com.example.project2.study.DataProviders.AlunoDTODataProvider;
import com.example.project2.study.DataProviders.EnderecoDTODataProvider;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Financeiro.*;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FinanceiroMensalidadeIT extends AbstractIntegrationIT {
    public static UUID uuidEscolaValido =
            UUID.fromString("a020780a-6ea2-42e7-9e02-6d283b2c1dd9");

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private MensalidadeService mensalidadeService;

    @Test
    public void createBoleto() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), "matheus@gmail.com", null, Boolean.TRUE);

        AlunoDTO alunoSaved = alunoService.createAluno(alunoDTO, uuidEscolaValido);
        Pessoa alunoFounded = pessoaRepository.findByUuid(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setStatusPagamento(StatusPagamento.EM_ABERTO);
        mensalidadeDTO.setAlunoFK(alunoSaved.getUuid());

        MensalidadeDTO mensalidadeCreatedDTO = mensalidadeService.createBoleto(alunoFounded.getUuid(), mensalidadeDTO);
        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());

        Mensalidade mensalidadeFoundedTwo = mensalidadeRepository.findByUuid(mensalidadeCreatedDTO.getUuid());

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(mensalidadeFounded).isEqualTo(mensalidadeFoundedTwo);
            acertaFofo.assertThat(mensalidadeRepository.count()).isEqualTo(1);
            acertaFofo.assertThat(mensalidadeFounded.getAlunoFK()).isEqualTo(alunoFounded.getUuid());
            acertaFofo.assertThat(mensalidadeFounded.getValorPago()).isEqualTo(new BigDecimal("800.00"));
            acertaFofo.assertThat(mensalidadeFounded.getStatusPagamento()).isEqualTo(StatusPagamento.ISENTO);
            acertaFofo.assertThat(mensalidadeFounded.getDataEmissao()).isEqualTo(LocalDate.now());
            acertaFofo.assertThat(mensalidadeFounded.getDataVencimento()).isEqualTo(LocalDate.now().plusMonths(1L));
            acertaFofo.assertThat(alunoSaved.isBolsista()).isTrue();
        });
    }
}
