package escola;

import DataProviders.EnderecoDTODataProvider;
import DataProviders.EscolaDTODataProvider;
import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.*;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

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
        dtoToSave.setNome("Lula");
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCidade("Cuiabá");
        enderecoDTO.setEstado("PE");
        enderecoDTO.setCep("89355-997");
        dtoToSave.setEndereco(enderecoDTO);
        EscolaDTO escolaDTO = escolaService.createEscola(dtoToSave);
        long afterCount = escolaRepository.count();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(escolaDTO.getNome()).isNotNull();
            s.assertThat(escolaDTO.getEndereco().getCidade()).isEqualTo("Cuiabá");
            s.assertThat(escolaDTO.getEndereco().getEstado()).isEqualTo("PE");
            s.assertThat(escolaDTO.getEndereco().getCep()).isEqualTo("89355997");
            s.assertThat(escolaDTO.getUuid()).isNotNull();
            s.assertThat(beforeCount + 1).isEqualTo(afterCount);
        });
    }

    @Test
    public void findAll() {
        List<EscolaDataGridDTO> all = escolaService.findAll();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(all).hasSize(1);
        });
    }

    @Test
    public void updateEscola() {
        // CRIANDO MEU DTO
        EscolaDTO createEscola = new EscolaDTO();
        createEscola.setNome("Dona Maria XX");
        createEscola.setUuid(UUID.randomUUID());
        EnderecoDTO createEnderecoDTO = new EnderecoDTO();
        createEnderecoDTO.setCidade("Manaus");
        createEnderecoDTO.setEstado("AM");
        createEnderecoDTO.setCep("88900-766");
        createEscola.setEndereco(createEnderecoDTO);
        // SALVANDO MINHA ENTIDADE
        EscolaDTO escolaSaved = escolaService.createEscola(createEscola);
        // ALTERANDO MEU DTO
        escolaSaved.setNome("Zezinho Rezende XIX");
        escolaSaved.getEndereco().setCidade("Santa Catarina");
        escolaSaved.getEndereco().setEstado("SC");
        escolaSaved.getEndereco().setCep("89010-677");

        // ATUALIZANDO MINHA ENTIDADE
        EscolaDTO updatedDTO = escolaService.updateByUuid(escolaSaved);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(updatedDTO.getNome()).isEqualTo("zezinho rezende xix");
            s.assertThat(updatedDTO.getEndereco().getCidade()).isEqualTo("Santa Catarina");
            s.assertThat(updatedDTO.getEndereco().getEstado()).isEqualTo("SC");
            s.assertThat(updatedDTO.getEndereco().getCep()).isEqualTo("89010677");
            s.assertThat(updatedDTO.getUuid()).isNotNull();
        });
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void nomeNaoInformadoWithSpacingName() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("  ", enderecoDTO);
        escolaService.createEscola(escolaDTO);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void nomeNaoInformadoWithNomeNull() {
        EnderecoDTO enderecoDTO = EnderecoDTODataProvider.ofMaringa();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO(null, enderecoDTO);
        escolaService.createEscola(escolaDTO);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Favor informar o endereço.")
    public void nomeNaoInformadoWithAddressNull() {
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("Joaquim Pamonha", null);
        escolaService.createEscola(escolaDTO);
    }

    @Test
    public void updateByUuidMesmoCEP() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.setNome("Tia benta XIX");

        escolaDTO.setEndereco(new EnderecoDTO());
        escolaDTO.getEndereco().setCidade("Echaporã");
        escolaDTO.getEndereco().setEstado("SP");
        escolaDTO.getEndereco().setCep("89010-344");

        EscolaDTO createEscola = escolaService.createEscola(escolaDTO);

        createEscola.setNome("Tio Bento II");
        createEscola.getEndereco().setCidade("Echaporã");
        createEscola.getEndereco().setEstado("SP");
        createEscola.getEndereco().setCep("89010-344");

        EscolaDTO atualizarEscola = escolaService.updateByUuid(createEscola);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(atualizarEscola.getNome()).isEqualTo("tio bento ii");
            s.assertThat(atualizarEscola.getEndereco().getCidade()).isEqualTo("Echaporã");
            s.assertThat(atualizarEscola.getEndereco().getEstado()).isEqualTo("SP");
            s.assertThat(atualizarEscola.getEndereco().getCep()).isEqualTo("89010344");
        });
    }

    @Test
    public void updateByUuidNewCEP() {
        long before = enderecoRepository.count();
        EnderecoDTO maringa = EnderecoDTO.of(enderecoRepository.findByCidadeAndCepAndEstado("Maringá", "87010355", UF.PR));

        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("Joao Segundo", maringa);
        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        createdEscola.setNome("Bentinho 20");
        createdEscola.setEndereco(EnderecoDTO.of("Maringá", "88999-666", "PR"));
        EscolaDTO updateEscola = escolaService.updateByUuid(createdEscola);
        long after = enderecoRepository.count();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(updateEscola.getNome()).isEqualTo("bentinho 20");
            s.assertThat(updateEscola.getEndereco().getCidade()).isEqualTo(maringa.getCidade());
            s.assertThat(updateEscola.getEndereco().getCep()).isNotEqualTo(maringa.getCep());
            s.assertThat(updateEscola.getEndereco().getEstado()).isEqualTo(maringa.getEstado());
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
        escolaDTO.setNome("   ");

        escolaDTO.setEndereco(new EnderecoDTO());
        escolaDTO.getEndereco().setCidade("Echaporã");
        escolaDTO.getEndereco().setEstado("SP");
        escolaDTO.getEndereco().setCep("89010-344");
        escolaService.createEscola(escolaDTO);
    }

    @Test
    public void createEscolaNotCreatesEndereco() {
        long before = enderecoRepository.count();
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.setNome("Beta IV");

        escolaDTO.setEndereco(new EnderecoDTO());
        escolaDTO.getEndereco().setCidade("Maringá");
        escolaDTO.getEndereco().setEstado("PR");
        escolaDTO.getEndereco().setCep("87010-355");
        EscolaDTO escola = escolaService.createEscola(escolaDTO);
        long after = enderecoRepository.count();

        Endereco endereco = enderecoRepository.findByCidadeAndCepAndEstado(
                escolaDTO.getEndereco().getCidade(),
                escolaDTO.getEndereco().getCep().replaceAll("-", ""),
                UF.valueOf(escolaDTO.getEndereco().getEstado())
        );

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(escola.getEndereco().getCidade()).isEqualTo(endereco.getCidade());
            s.assertThat(escola.getEndereco().getCep()).isEqualTo(endereco.getCep());
            s.assertThat(UF.valueOf(escola.getEndereco().getEstado())).isEqualTo(endereco.getEstado());
            s.assertThat(after).isEqualTo(before);
        });
    }

    @Test
    public void createEscolaCreatesEndereco() {
        long before = enderecoRepository.count();
        EscolaDTO escolaDTO = EscolaDTODataProvider.createEscolaDTO("Francisco Segundo",
                EnderecoDTO.of("Sarandi", "88099-455", "PR"));
        EscolaDTO criarEscola = escolaService.createEscola(escolaDTO);
        long after = enderecoRepository.count();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(criarEscola.getNome()).isEqualTo("francisco segundo");
            s.assertThat(criarEscola.getEndereco().getCidade()).isEqualTo("Sarandi");
            s.assertThat(criarEscola.getEndereco().getCep()).isEqualTo("88099455");
            s.assertThat(criarEscola.getEndereco().getEstado()).isEqualTo("PR");
            s.assertThat(after).isEqualTo(before + 1);
        });
    }


    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar a cidade.")
    public void createEscolaCidadeBlank() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.setNome("Tio Ben 10");
        escolaDTO.setEndereco(new EnderecoDTO());

        escolaDTO.getEndereco().setCidade("   ");
        escolaDTO.getEndereco().setCep("88010-666");
        escolaDTO.getEndereco().setEstado("SP");

        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(createdEscola.getNome()).isBlank();
            s.assertThat(createdEscola.getEndereco().getCidade()).isEqualTo("Presidente Prudente");
            s.assertThat(createdEscola.getEndereco().getCep()).isEqualTo("88010-666");
            s.assertThat(createdEscola.getEndereco().getEstado()).isEqualTo("SP");
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar a cidade.")
    public void createEscolaCidadeNull() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.setNome("Tio Ben 10");
        escolaDTO.setEndereco(EnderecoDTO.of(null, "88010-666", "SP"));

        EscolaDTO createdEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(createdEscola.getNome()).isNull();
            s.assertThat(createdEscola.getEndereco().getCidade()).isEqualTo("Presidente Prudente");
            s.assertThat(createdEscola.getEndereco().getCep()).isEqualTo("88010666");
            s.assertThat(createdEscola.getEndereco().getEstado()).isEqualTo("SP");
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Favor informar o cep.")
    public void createEscolaCepBlank() {
        EscolaDTO escolaDTO = new EscolaDTO();
        escolaDTO.setNome("Ben 10");
        escolaDTO.setEndereco(EnderecoDTO.of("Presidente Prudente", "  ", "SP"));
        EscolaDTO criarEscola = escolaService.createEscola(escolaDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(criarEscola.getNome()).isEqualTo("Ben 10");
            s.assertThat(criarEscola.getEndereco().getCidade()).isEqualTo("Presidente Prudente");
            s.assertThat(criarEscola.getEndereco().getCep()).isBlank();
            s.assertThat(criarEscola.getEndereco().getEstado()).isEqualTo("SP");
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
