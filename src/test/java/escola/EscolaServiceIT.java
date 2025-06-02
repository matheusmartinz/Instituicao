package escola;

import DataProviders.EnderecoDTODataProvider;
import DataProviders.EscolaDTODataProvider;
import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.*;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoRepository;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class EscolaServiceIT extends AbstractIntegrationTest {

    @Autowired
    EscolaService escolaService;

    @Autowired
    EscolaRepository escolaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Test
    public void createEscola() {
        long beforeCount = escolaRepository.count();
        EscolaDTO dtoToSave = new EscolaDTO();
        dtoToSave.nome = "XVIDEOS";
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.cidade = "Cuiabá";
        enderecoDTO.estado = "PE";
        enderecoDTO.cep = "89355-997";
        dtoToSave.endereco = enderecoDTO;
        EscolaDTO escolaDTO = escolaService.createEscola(dtoToSave);
        long afterCount = escolaRepository.count();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(escolaDTO.nome).isNotNull();
            s.assertThat(escolaDTO.endereco.cidade).isEqualTo("Cuiabá");
            s.assertThat(escolaDTO.endereco.estado).isEqualTo("PE");
            s.assertThat(escolaDTO.endereco.cep).isEqualTo("89355997");
            s.assertThat(escolaDTO.uuid).isNotNull();
            s.assertThat(beforeCount + 1).isEqualTo(afterCount);
        });
    }

    @Test
    public void findAll() {
        List<EscolaDataGridDTO> all = escolaService.findAll();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(all).hasSize(4);
        });
    }

    @Test
    public void updateEscola() {
        // CRIANDO MEU DTO
        EscolaDTO createEscola = new EscolaDTO();
        createEscola.nome = "Dona Maria XX";
        EnderecoDTO createEnderecoDTO = new EnderecoDTO();
        createEnderecoDTO.cidade = "Manaus";
        createEnderecoDTO.estado = "AM";
        createEnderecoDTO.cep = "88900-766";
        createEscola.endereco = createEnderecoDTO;
        // SALVANDO MINHA ENTIDADE
        EscolaDTO escolaSaved = escolaService.createEscola(createEscola);
        // ALTERANDO MEU DTO
        escolaSaved.nome = "Zezinho Rezende XIX";
        escolaSaved.endereco.cidade = "Santa Catarina";
        escolaSaved.endereco.estado = "SC";
        escolaSaved.endereco.cep = "89010-677";

        // ATUALIZANDO MINHA ENTIDADE
        EscolaDTO updatedDTO = escolaService.updateByUuid(escolaSaved);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(updatedDTO.nome).isEqualTo("Zezinho Rezende XIX");
            s.assertThat(updatedDTO.endereco.cidade).isEqualTo("Santa Catarina");
            s.assertThat(updatedDTO.endereco.estado).isEqualTo("SC");
            s.assertThat(updatedDTO.endereco.cep).isEqualTo("89010677");
            s.assertThat(updatedDTO.uuid).isNotNull();
        });
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void nomeNaoInformadoWithSpacingName() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createAlunoDTO("  ", enderecoDTO);
        escolaService.createEscola(escolaDTO);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void nomeNaoInformadoWithNomeNull() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createAlunoDTO(null, enderecoDTO);
        escolaService.createEscola(escolaDTO);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o endereço.")
    public void nomeNaoInformadoWithAddressNull() {
        EscolaDTO escolaDTO = EscolaDTODataProvider.createAlunoDTO("Joaquim Pamonha", null);
        escolaService.createEscola(escolaDTO);
    }

    @Test
    public void updateByUuidMesmoCEP() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "Tia benta XIX";
        escolaDTO.endereco = new EnderecoDTO();
        escolaDTO.endereco.cidade = "Echaporã";
        escolaDTO.endereco.estado = "SP";
        escolaDTO.endereco.cep = "89010-344";

        EscolaDTO createEscola = escolaService.createEscola(escolaDTO);

        createEscola.nome = "Tio Bento II";
        createEscola.endereco.cidade = "Echaporã";
        createEscola.endereco.estado = "SP";
        createEscola.endereco.cep = "89010-344";

        EscolaDTO atualizarEscola = escolaService.updateByUuid(createEscola);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(atualizarEscola.nome).isEqualTo("Tio Bento II");
            s.assertThat(atualizarEscola.endereco.cidade).isEqualTo("Echaporã");
            s.assertThat(atualizarEscola.endereco.estado).isEqualTo("SP");
            s.assertThat(atualizarEscola.endereco.cep).isEqualTo("89010344");
        });
    }

    @Test
    public void updateByUuidNewCEP() {
        long before = enderecoRepository.count();
        EnderecoDTO maringa = new EnderecoDTO(enderecoRepository.findByCidadeAndCepAndEstado("Maringá", "87030655",UF.PR));

        EscolaDTO escolaDTO = EscolaDTODataProvider.createAlunoDTO("Bento XX", maringa);
        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        createdEscola.nome = "Bentinho 20";
        createdEscola.endereco = EnderecoDTODataProvider.generic("Tocantins","88999-666","PE");
        EscolaDTO updateEscola = escolaService.updateByUuid(createdEscola);


        long after = enderecoRepository.count();

        SoftAssertions.assertSoftly(s ->{
            s.assertThat(updateEscola.nome).isEqualTo("Bentinho 20");
            s.assertThat(updateEscola.endereco.cidade).isNotEqualTo(maringa.cidade);
            s.assertThat(updateEscola.endereco.cep).isNotEqualTo(maringa.cep);
            s.assertThat(updateEscola.endereco.estado).isNotEqualTo(maringa.estado);
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void favorInformarONomeExceptionNomeNull() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaService.createEscola(escolaDTO);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void favorInformarONomeExceptionNomeVazioComEspaco() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "   ";
        escolaDTO.endereco = new EnderecoDTO();
        escolaDTO.endereco.cidade = "Echaporã";
        escolaDTO.endereco.estado = "SP";
        escolaDTO.endereco.cep = "89010-344";
        escolaService.createEscola(escolaDTO);
    }

    @Test
    public void createEscolaNotCreatesEndereco() {
        long before = enderecoRepository.count();
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "Beta IV";
        escolaDTO.endereco = new EnderecoDTO();
        escolaDTO.endereco.cidade = "Echaporã";
        escolaDTO.endereco.estado = "SP";
        escolaDTO.endereco.cep = "89010-344";
        EscolaDTO escola = escolaService.createEscola(escolaDTO);
        long after = enderecoRepository.count();

        Endereco endereco = enderecoRepository.findByCidadeAndCepAndEstado(
                escolaDTO.endereco.cidade,
                escolaDTO.endereco.cep.replaceAll("-", ""),
                UF.valueOf(escolaDTO.endereco.estado)
        );

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(escola.endereco.cidade).isEqualTo(endereco.getCidade());
            s.assertThat(escola.endereco.cep).isEqualTo(endereco.getCep());
            s.assertThat(UF.valueOf(escola.endereco.estado)).isEqualTo(endereco.getEstado());
            s.assertThat(after).isEqualTo(before);
        });
    }

    @Test
    public void createEscolaCreatesEndereco(){
        long before = enderecoRepository.count();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createAlunoDTO("Francisco Segundo",
                EnderecoDTODataProvider.generic("Sarandi","88099-455","PR"));
        EscolaDTO criarEscola = escolaService.createEscola(escolaDTO);
        long after = enderecoRepository.count();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(criarEscola.nome).isEqualTo("Francisco Segundo");
            s.assertThat(criarEscola.endereco.cidade).isEqualTo("Sarandi");
            s.assertThat(criarEscola.endereco.cep).isEqualTo("88099455");
            s.assertThat(criarEscola.endereco.estado).isEqualTo("PR");
            s.assertThat(after).isEqualTo(before + 1);
        });
    }


    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar a cidade.")
    public void createEscolaCidadeBlank() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "Tio Ben 10";
        escolaDTO.endereco = new EnderecoDTO();
        escolaDTO.endereco.cidade = "   ";
        escolaDTO.endereco.cep = "88010-666";
        escolaDTO.endereco.estado = "SP";

        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(createdEscola.nome).isBlank();
            s.assertThat(createdEscola.endereco.cidade).isEqualTo("Presidente Prudente");
            s.assertThat(createdEscola.endereco.cep).isEqualTo("88010-666");
            s.assertThat(createdEscola.endereco.estado).isEqualTo("SP");
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar a cidade.")
    public void createEscolaCidadeNull() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "Tio Ben 10";
        escolaDTO.endereco = EnderecoDTODataProvider.generic(null, "88010-666", "SP");

        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(createdEscola.nome).isNull();
            s.assertThat(createdEscola.endereco.cidade).isEqualTo("Presidente Prudente");
            s.assertThat(createdEscola.endereco.cep).isEqualTo("88010666");
            s.assertThat(createdEscola.endereco.estado).isEqualTo("SP");
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar o cep.")
    public void createEscolaCepBlank() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.nome = "Ben 10";
        escolaDTO.endereco = EnderecoDTODataProvider.generic("Presidente Prudente", "  ", "SP");
        EscolaDTO criarEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(criarEscola.nome).isEqualTo("Ben 10");
            s.assertThat(criarEscola.endereco.cidade).isEqualTo("Presidente Prudente");
            s.assertThat(criarEscola.endereco.cep).isBlank();
            s.assertThat(criarEscola.endereco.estado).isEqualTo("SP");
        });
    }

    @Test
    public void findAllBySerie() {
        List<Escola> allBySalasBySerieAno = escolaRepository.findAllBySalasBySerieAno(SerieAno.QUARTO_ANO.name());
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(allBySalasBySerieAno).hasSize(1);
        });
    }

    @Test
    public void findAllBySerieNull() {
        List<Escola> allBySalasBySerieAno = escolaRepository.findAllBySalasBySerieAno(SerieAno.PRIMEIRO_ANO.name());
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(allBySalasBySerieAno).hasSize(0);
        });
    }
}
