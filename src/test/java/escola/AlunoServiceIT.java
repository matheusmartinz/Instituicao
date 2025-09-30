package escola;

import DataProviders.AlunoDTODataProvider;
import DataProviders.EnderecoDTODataProvider;
import DataProviders.PessoaTelefoneDTODataProvider;
import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.*;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoService;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class AlunoServiceIT extends AbstractIntegrationTest {
    public static String emailMatheus =
            "matheus@gmail";
    public static UUID uuidEscolaValido =
            UUID.fromString("e655c7e1-742a-42f4-9eba-b69e344c728c");
    public static UUID uuidSala = UUID.fromString("ed8172e3-0bc4-4f5d-8f34-27de83c22f4c");
    public static UUID uuidSalaValida =
            UUID.fromString("ed8172e3-0bc4-4f5d-8f34-27de83c22f4c");


    @Autowired
    AlunoService alunoService;
    @Autowired
    EscolaService escolaService;
    @Autowired
    SalaService salaService;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    EscolaRepository escolaRepository;
    @Autowired
    SalaRepository salaRepository;

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar a série do aluno.")
    @SneakyThrows
    public void necessarioInformarSerieAlunoException() {
        int before = escolaRepository.findByUuid(uuidEscolaValido).getPessoas().size();
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(null, "José Almeida", null,
                null, emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
        int after = escolaRepository.findByUuid(uuidEscolaValido).getPessoas().size();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test
    public void validarCriacaoSalaNaEscola(){
        int before = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.numeroSala = "25";
        salaDTO.serieAno = SerieAno.TERCEIRO_ANO;
        salaDTO.capacidadeAlunos = 10;

        salaService.createSala(salaDTO,uuidEscolaValido);
        int after = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();

        SoftAssertions.assertSoftly(s ->{
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test
    public void validarCriacaoSala(){
        long before = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.numeroSala = "25";
        salaDTO.serieAno = SerieAno.TERCEIRO_ANO;
        salaDTO.capacidadeAlunos = 10;

        SalaDTO criacaoSala = salaService.createSala(salaDTO,uuidEscolaValido);
        long after = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();

        SoftAssertions.assertSoftly(s ->{
            s.assertThat(criacaoSala).isNotNull();
            s.assertThat(criacaoSala.numeroSala).isEqualTo("25");
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o nome do aluno.")
    @SneakyThrows
    public void necessarioInformarNomeComEspacamento() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "    ", "11085139999",
                null, emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o nome do aluno.")
    @SneakyThrows
    public void necessarioInformarNomeNull() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", null, "11085139999",
                null, emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }


    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o CPF do aluno.")
    @SneakyThrows
    public void necessarioInformarCpfAluno() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida", null, null,
                emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Formato do CPF informado é inválido.")
    @SneakyThrows
    public void formatoDoCpfInválido() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "110.851.399-9", null, emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o CPF do aluno.")
    @SneakyThrows
    public void necessarioInformarCpfAlunoSemCpf() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida", null, null,
                emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o Endereço do aluno.")
    @SneakyThrows
    public void necessarioInformarEnderecoAlunoComCpf() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "ANDRÉ FRANCO",
                "110.851.399-99", null, emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o Email do aluno.")
    @SneakyThrows
    public void necessarioInformarEmailAlunoSemEmail() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("2°", "ANDRÉ FRANCO",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), null, null);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Escola não encontrada.")
    @SneakyThrows
    public void escolaNaoEncontrada() {
        UUID uuidOutraEntidade = uuidSala;
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null);
        alunoService.createAluno(alunoDTO, uuidOutraEntidade);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Não possuimos salas para o primeiro ano.")
    @SneakyThrows
    public void salaNaoEncontrada() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "11085139999", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null);
        Escola escola = escolaService.load(uuidEscolaValido);
        escola.getSalas().clear();
        escolaService.update(escola);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test
    @SneakyThrows
    public void alunoCriadoComSucesso() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "José Carlos",
                "220.567.432-11", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null);

        Escola escola = escolaService.load(uuidEscolaValido);
        Sala sala = salaRepository.findByUuid(uuidSala);
        addSalaNaEscola(escola, sala);
        AlunoDTO alunoDTOFinal = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoDTOFinal.matricula).isNotNull();
            s.assertThat(alunoDTOFinal.tarefas).isEmpty();
            s.assertThat(alunoDTOFinal.disciplinas).hasSize(3);
            s.assertThat(SerieAno.from(alunoDTOFinal.serieAno).isAno(SerieAno.QUARTO_ANO)).isTrue();
            s.assertThat(alunoDTOFinal.nome).isEqualTo("José Carlos");
            s.assertThat(alunoDTOFinal.cpf).isEqualTo("220.567.432-11");
            s.assertThat(alunoDTOFinal.email).isNotNull();
            s.assertThat(alunoDTOFinal.telefone).isNull();
            s.assertThat(alunoDTOFinal.uuid).isNotNull();
            EnderecoDTO enderecoDTO = alunoDTOFinal.endereco;
            s.assertThat(enderecoDTO.uuid).isNotNull();
            s.assertThat(enderecoDTO.cidade).isEqualTo("Maringá");
            s.assertThat(enderecoDTO.cep).isEqualTo("87060550");
            s.assertThat(enderecoDTO.estado).isEqualTo("PR");
        });
    }

    @Test
    @SneakyThrows
    public void contaQuantidadeAluno() {
        Sala sala = salaRepository.findByUuid(UUID.fromString("228b0a25-c767-4e7a-878e-d539105bbb79"));
        Integer capacidadeAlunosAntes = sala.getCapacidadeAlunos();

        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "José Almeida",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null);

        Escola escola = escolaService.load(uuidEscolaValido);
        addSalaNaEscola(escola, sala);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);

        Sala salaDepoisDeSalva = salaRepository.findByUuid(uuidSala);
        Integer capacidadeAlunosDepois = salaDepoisDeSalva.getCapacidadeAlunos();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(capacidadeAlunosDepois).isEqualTo(capacidadeAlunosAntes - 1);
        });
    }

    @Test
    public void alunoComTelefone() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "André", "88899944455",
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, PessoaTelefoneDTODataProvider.ofTelefone());
        Escola escola = escolaService.load(uuidEscolaValido);
        Sala sala = salaRepository.findByUuid(uuidSala);
        addSalaNaEscola(escola, sala);
        AlunoDTO alunoDTOFinal = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        SoftAssertions.assertSoftly(s -> {
            PessoaTelefoneDTO telefone = alunoDTOFinal.telefone;
            s.assertThat(telefone.ddd).isEqualTo("44");
            s.assertThat(telefone.fone).isEqualTo("992228899");
            s.assertThat(telefone.pessoaUUID).isEqualTo(alunoDTOFinal.uuid);
            s.assertThat(alunoDTOFinal.email).isEqualTo(emailMatheus);
        });
    }
    private void addSalaNaEscola(Escola escola, Sala sala) {
        if (!escola.getSalas().contains(sala)) {
            escola.addSala(sala);
            escolaService.update(escola);
        }
    }


}
