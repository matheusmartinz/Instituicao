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

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MensalidadeRestControllerIT extends AbstractIntegrationIT {
    private static final String BASE_URL = "/mensalidade";
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private EscolaService escolaService;
    @Autowired
    private SalaService salaService;

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

        String contentAsString = postRequest(BASE_URL + "/" + aluno.getUuid(), null, status().isOk())
                .andReturn().getRequest().getContentAsString();

        MensalidadeDTO mensalidadeDTOCreated = objectMapper.readValue(contentAsString, new TypeReference<MensalidadeDTO>() {
        });

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(mensalidadeDTOCreated.getUuid()).isNotNull();
        });
    }

}