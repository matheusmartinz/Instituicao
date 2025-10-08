package com.example.project2.study.escola;

import com.example.project2.study.AbstractIntegrationIT;
import com.example.project2.study.DataProviders.AlunoDTODataProvider;
import com.example.project2.study.DataProviders.EnderecoDTODataProvider;
import com.example.project2.study.DataProviders.PessoaTelefoneDTODataProvider;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaService;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;


public class AlunoServiceIT extends AbstractIntegrationIT {
    public static String emailMatheus =
            "matheus@gmail";
    public static UUID uuidEscolaValido =
            UUID.fromString("e655c7e1-742a-42f4-9eba-b69e344c728c");
    public static UUID uuidSala = UUID.fromString("8d2d724b-ef6a-49dc-b312-ac7ccb3f38b9");
    public static UUID uuidSalaValida =
            UUID.fromString("8d2d724b-ef6a-49dc-b312-ac7ccb3f38b9");

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
                null, emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
        int after = escolaRepository.findByUuid(uuidEscolaValido).getPessoas().size();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test
    public void validarCriacaoSalaNaEscola() {
        int before = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.numeroSala = "25";
        salaDTO.serieAno = SerieAno.TERCEIRO_ANO;
        salaDTO.capacidadeAlunos = 10;

        salaService.createSala(salaDTO, uuidEscolaValido);
        int after = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test
    public void validarCriacaoSala() {
        long before = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.numeroSala = "25";
        salaDTO.serieAno = SerieAno.TERCEIRO_ANO;
        salaDTO.capacidadeAlunos = 10;

        SalaDTO criacaoSala = salaService.createSala(salaDTO, uuidEscolaValido);
        long after = escolaRepository.findByUuid(uuidEscolaValido).getSalas().size();

        SoftAssertions.assertSoftly(s -> {
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
                null, emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o nome do aluno.")
    @SneakyThrows
    public void necessarioInformarNomeNull() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", null, "11085139999",
                null, emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }


    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o CPF do aluno.")
    @SneakyThrows
    public void necessarioInformarCpfAluno() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida", null, null,
                emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Formato do CPF informado é inválido.")
    @SneakyThrows
    public void formatoDoCpfInvalido() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "110.851.399-9", null, emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o CPF do aluno.")
    @SneakyThrows
    public void necessarioInformarCpfAlunoSemCpf() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida", null, null,
                emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o Endereço do aluno.")
    @SneakyThrows
    public void necessarioInformarEnderecoAlunoComCpf() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "ANDRÉ FRANCO",
                "110.851.399-99", null, emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "É necessário informar o Email do aluno.")
    @SneakyThrows
    public void necessarioInformarEmailAlunoSemEmail() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("2°", "ANDRÉ FRANCO",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), null, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Escola não encontrada.")
    @SneakyThrows
    public void escolaNaoEncontrada() {
        UUID uuidOutraEntidade = uuidSala;
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);
        alunoService.createAluno(alunoDTO, uuidOutraEntidade);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Não possuimos salas para o primeiro ano.")
    @SneakyThrows
    public void salaNaoEncontrada() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("1°", "José Almeida",
                "11085139999", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);
        Escola escola = escolaService.load(uuidEscolaValido);
        escola.getSalas().clear();
        escolaService.update(escola);
        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }


    @Test
    @SneakyThrows
    public void alunoCriadoComSucesso() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "José Carlos",
                "220.567.432-11", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

        AlunoDTO alunoDTOFinal = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoDTOFinal.getMatricula()).isNotNull();
            s.assertThat(alunoDTOFinal.getTarefas()).isEmpty();
            s.assertThat(alunoDTOFinal.getDisciplinas()).hasSize(3);
            s.assertThat(SerieAno.from(alunoDTOFinal.getSerieAno()).isAno(SerieAno.QUARTO_ANO)).isTrue();
            s.assertThat(alunoDTOFinal.getNome()).isEqualTo("José Carlos");
            s.assertThat(alunoDTOFinal.getCpf()).isEqualTo("220.567.432-11");
            s.assertThat(alunoDTOFinal.getEmail()).isNotNull();
            s.assertThat(alunoDTOFinal.getTelefone()).isNull();
            s.assertThat(alunoDTOFinal.getUuid()).isNotNull();

            EnderecoDTO enderecoDTO = alunoDTOFinal.getEndereco();

            s.assertThat(enderecoDTO.getUuid()).isNotNull();
            s.assertThat(enderecoDTO.getCidade()).isEqualTo("Maringá");
            s.assertThat(enderecoDTO.getCep()).isEqualTo("87060550");
            s.assertThat(enderecoDTO.getEstado()).isEqualTo("PR");
        });
    }

    @Test
    @SneakyThrows
    public void contaQuantidadeAluno() {
        Sala sala = salaRepository.findByUuid(uuidSalaValida);
        Integer capacidadeAlunosAntes = sala.getCapacidadeAlunos();

        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO("4°", "José Almeida",
                "110.851.399-99", EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

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
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, PessoaTelefoneDTODataProvider.ofTelefone(), Boolean.FALSE);

        SalaDTO dtoSalaTOSave = new SalaDTO();
        dtoSalaTOSave.numeroSala = "10";
        dtoSalaTOSave.serieAno = SerieAno.TERCEIRO_ANO;
        dtoSalaTOSave.capacidadeAlunos = 10;

        salaService.createSala(dtoSalaTOSave, uuidEscolaValido);

        AlunoDTO alunoDTOFinal = alunoService.createAluno(alunoDTO, uuidEscolaValido);

        SoftAssertions.assertSoftly(s -> {
            PessoaTelefoneDTO telefone = alunoDTOFinal.getTelefone();
            s.assertThat(telefone.ddd).isEqualTo("44");
            s.assertThat(telefone.fone).isEqualTo("992228899");
            s.assertThat(telefone.pessoaUUID).isEqualTo(alunoDTOFinal.getUuid());
            s.assertThat(alunoDTOFinal.getEmail()).isEqualTo(emailMatheus);
        });
    }


    @Test
    public void createCargaHorarioAluno() {
        AlunoDTO dataProvider = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

        dataProvider.addDisciplina(Disciplina.GEOGRAFIA);
        dataProvider.addDisciplina(Disciplina.INGLES);
        dataProvider.addDisciplina(Disciplina.MATEMATICA);


        AlunoDTO alunoDTO = alunoService.createAluno(dataProvider, uuidEscolaValido);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoDTO.getCargaHoraria()).isEqualTo(42);
            s.assertThat(alunoDTO.getDisciplinas()).hasSize(4);
        });
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "A quantidade de disciplinas excedeu o máximo de 44 Horas")
    public void cargaHorariaAlunoExceeded() {
        AlunoDTO dataProvider = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

        dataProvider.addDisciplina(Disciplina.GEOGRAFIA);
        dataProvider.addDisciplina(Disciplina.BIOLOGIA);
        dataProvider.addDisciplina(Disciplina.LITERATURA);

        alunoService.createAluno(dataProvider, uuidEscolaValido);
    }

    public void addSameDiscipline() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

        alunoDTO.addDisciplina(Disciplina.MATEMATICA);

        alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }

    @Test
    public void removeDisciplina() {
        AlunoDTO aluno = createGenericAluno();

        alunoService.removeDisciplina(aluno.getUuid(), Disciplina.MATEMATICA);

        Pessoa byUuid = alunoRepository.findByUuid(aluno.getUuid());

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(aluno.getDisciplinas()).hasSize(3);
            s.assertThat(byUuid.getDisciplinas()).hasSize(2);
            s.assertThat(byUuid.getDisciplinas()).doesNotContain(Disciplina.MATEMATICA);
            s.assertThat(byUuid.getDisciplinas()).isEqualTo(List.of(Disciplina.PORTUGUES, Disciplina.INGLES));
        });
    }

    @Test
    public void updateAluno() {
        AlunoDTO alunoDTO = createGenericAluno();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoDTO.getNome()).isEqualTo("José Almeida");
            s.assertThat(alunoDTO.getEmail()).isEqualTo("matheus@gmail");
            s.assertThat(alunoDTO.getDisciplinas()).isEqualTo(List.of(Disciplina.PORTUGUES,
                    Disciplina.MATEMATICA,
                    Disciplina.INGLES));
            s.assertThat(alunoDTO.getCargaHoraria()).isEqualTo(31);
        });

        alunoDTO.setNome("JUJUBINHA 123");
        alunoDTO.setEmail("jujubinha_123@gmail.com");
        alunoDTO.setDisciplinas(List.of(Disciplina.GEOGRAFIA, Disciplina.INGLES));
        alunoService.updateAluno(alunoDTO);

        Pessoa alunoEntity = alunoRepository.findByUuid(alunoDTO.getUuid());

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(alunoEntity.getNome()).isEqualTo("JUJUBINHA 123");
            s.assertThat(alunoEntity.getEmail()).isEqualTo("jujubinha_123@gmail.com");
            s.assertThat(alunoEntity.getDisciplinas()).isEqualTo(List.of(Disciplina.GEOGRAFIA,
                    Disciplina.INGLES));
            s.assertThat(alunoEntity.getCargaHoraria()).isEqualTo(22);
        });
    }


    public AlunoDTO createGenericAluno() {
        AlunoDTO alunoDTO = AlunoDTODataProvider.createAlunoDTO(SerieAno.QUARTO_ANO.getValor(), "José Almeida", "110.851.399-99",
                EnderecoDTODataProvider.ofMaringa(), emailMatheus, null, Boolean.FALSE);

        return alunoService.createAluno(alunoDTO, uuidEscolaValido);
    }
}
