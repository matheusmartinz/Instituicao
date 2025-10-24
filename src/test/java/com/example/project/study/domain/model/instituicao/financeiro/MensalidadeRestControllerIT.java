package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.dataproviders.AlunoDTODataProvider;
import com.example.project.study.dataproviders.EnderecoDTODataProvider;
import com.example.project.study.dataproviders.EscolaDTODataProvider;
import com.example.project.study.dataproviders.PessoaTelefoneDTODataProvider;
import com.example.project.study.domain.model.instituicao.escola.EscolaDTO;
import com.example.project.study.domain.model.instituicao.escola.EscolaService;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoService;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaDTO;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MensalidadeRestControllerIT extends AbstractIntegrationIT {
    private static final String BASE_URL = "/mensalidade";
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private EscolaService escolaService;
    @Autowired
    private SalaService salaService;
    @Autowired
    MensalidadeService mensalidadeService;
    @Autowired
    MensalidadeRepository mensalidadeRepository;

    @Test
    @SneakyThrows
    public void createMensalidade() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("MIN RABÁ", enderecoDTO);
        EscolaDTO escola = escolaService.createEscola(escolaDTO);

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNumeroSala("120");
        salaDTO.setSerieAno(SerieAno.QUARTO_ANO);
        salaService.createSala(salaDTO, escola.getUuid());


        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "André", "88899944455",
                enderecoDTO, "andre.franco@peon.moda", PessoaTelefoneDTODataProvider.ofTelefone(), Boolean.FALSE);

        AlunoDTO aluno = alunoService.createAluno(alunoDTO, escola.getUuid());

        String contentAsString = postRequest(BASE_URL + "/" + aluno.getUuid(), "", status().isOk())
                .andReturn().getResponse().getContentAsString();

        MensalidadeDTO mensalidadeDTOCreated = objectMapper.readValue(contentAsString, new TypeReference<MensalidadeDTO>() {
        });

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(mensalidadeDTOCreated.getUuid()).isNotNull();
            s.assertThat(mensalidadeDTOCreated.getDataEmissao()).isEqualTo(LocalDate.now());
            s.assertThat(mensalidadeDTOCreated.getDataVencimento()).isEqualTo(LocalDate.now().plusMonths(1L));
            s.assertThat(mensalidadeDTOCreated.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, BigDecimal.ROUND_HALF_UP));
        });
    }

    @Test
    @SneakyThrows
    public void getMensalidadeByUuid() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("MIN RABÁ", enderecoDTO);
        EscolaDTO escola = escolaService.createEscola(escolaDTO);

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNumeroSala("120");
        salaDTO.setSerieAno(SerieAno.QUARTO_ANO);
        salaService.createSala(salaDTO, escola.getUuid());


        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "André", "88899944455",
                enderecoDTO, "andre.franco@peon.moda", PessoaTelefoneDTODataProvider.ofTelefone(), Boolean.FALSE);

        AlunoDTO aluno = alunoService.createAluno(alunoDTO, escola.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(aluno.getUuid());
        MensalidadeDTO boletoCreated = mensalidadeService.createBoleto(aluno.getUuid());

        String contentAsString = getRequest(BASE_URL + "/" + boletoCreated.getUuid(), status().isOk()).andReturn().getResponse().getContentAsString();

        MensalidadeDTO toReturnMensalidade = objectMapper.readValue(contentAsString, new TypeReference<MensalidadeDTO>() {
        });

        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(toReturnMensalidade.getUuid()).isEqualTo(boletoCreated.getUuid());
            acertaFofo.assertThat(toReturnMensalidade.getAlunoFK()).isEqualTo(boletoCreated.getAlunoFK());
            acertaFofo.assertThat(toReturnMensalidade.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, BigDecimal.ROUND_HALF_UP));
        });
    }

    @Test
    @SneakyThrows
    public void deleteBoleto() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("MIN RABÁ", enderecoDTO);
        EscolaDTO escola = escolaService.createEscola(escolaDTO);

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNumeroSala("120");
        salaDTO.setSerieAno(SerieAno.QUARTO_ANO);
        salaService.createSala(salaDTO, escola.getUuid());


        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "André", "88899944455",
                enderecoDTO, "andre.franco@peon.moda", PessoaTelefoneDTODataProvider.ofTelefone(), Boolean.FALSE);

        AlunoDTO aluno = alunoService.createAluno(alunoDTO, escola.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(aluno.getUuid());

        long beforeCreated = mensalidadeRepository.count();
        MensalidadeDTO boletoCreated = mensalidadeService.createBoleto(aluno.getUuid());
        long beforeDeleted = mensalidadeRepository.count();

        String toReturnContent = deleteRequest(BASE_URL + "/" + boletoCreated.getUuid(), status().isOk()).andReturn().getResponse().getContentAsString();

        long afterDeleted = mensalidadeRepository.count();


        SoftAssertions.assertSoftly(acertaFofo -> {
            acertaFofo.assertThat(beforeDeleted).isEqualTo(beforeCreated + 1);
            acertaFofo.assertThat(afterDeleted).isEqualTo(beforeCreated);
            acertaFofo.assertThat(toReturnContent).isEqualTo("Mensalidade deletada com sucesso.");
        });
    }

    @Test
    @SneakyThrows
    public void updateMensalidadeBoleto() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("MIN RABÁ", enderecoDTO);
        EscolaDTO escola = escolaService.createEscola(escolaDTO);

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNumeroSala("120");
        salaDTO.setSerieAno(SerieAno.QUARTO_ANO);
        salaService.createSala(salaDTO, escola.getUuid());


        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "André", "88899944455",
                enderecoDTO, "andre.franco@peon.moda", PessoaTelefoneDTODataProvider.ofTelefone(), Boolean.FALSE);

        AlunoDTO aluno = alunoService.createAluno(alunoDTO, escola.getUuid());

        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        mensalidadeDTO.setAlunoFK(aluno.getUuid());

        MensalidadeDTO mensalidadeCreated = mensalidadeService.createBoleto(aluno.getUuid());

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(mensalidadeCreated.getValorPagamento()).isEqualTo(BigDecimal.valueOf(800).setScale(2, BigDecimal.ROUND_HALF_UP));
            s.assertThat(mensalidadeCreated.getAlunoFK()).isEqualTo(aluno.getUuid());
            s.assertThat(mensalidadeCreated.getDataEmissao()).isEqualTo(LocalDate.now());
            s.assertThat(mensalidadeCreated.getDataVencimento()).isEqualTo(LocalDate.now().plusMonths(1L));
        });

        MensalidadeDTO mensalidadeUpdatedDTO = new MensalidadeDTO();
        mensalidadeUpdatedDTO.setMulta(BigDecimal.valueOf(10.00));
        mensalidadeUpdatedDTO.setJuros(BigDecimal.valueOf(5.00));
        mensalidadeUpdatedDTO.setDataVencimento(LocalDate.now().plusMonths(2L));

        String content = objectMapper.writeValueAsString(mensalidadeUpdatedDTO);

        String contentAsString = putRequest(BASE_URL + "/" + mensalidadeCreated.getUuid(), content, status().isOk()).andReturn().getResponse().getContentAsString();

        MensalidadeDTO updatedDTO = objectMapper.readValue(contentAsString, new TypeReference<MensalidadeDTO>() {
        });
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(updatedDTO.getMulta()).isEqualTo(BigDecimal.valueOf(10.00));
            s.assertThat(updatedDTO.getDataVencimento()).isEqualTo(LocalDate.now().plusMonths(2L));
            s.assertThat(updatedDTO.getJuros()).isEqualTo(BigDecimal.valueOf(5.00));
        });

    }

}