package com.example.project.study.domain.model.instituicao.escola.pessoa.aluno;

import com.example.project.study.domain.repositories.EscolaRepository;
import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.Disciplina;
import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoService;
import com.example.project.study.domain.model.instituicao.escola.Escola;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaTelefone;
import com.example.project.study.domain.model.instituicao.escola.sala.Sala;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaService;
import com.example.project.study.domain.model.instituicao.escola.EscolaValidator;
import com.example.project.study.domain.model.instituicao.escola.pessoa.MatriculaGenerator;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaTelefoneService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AlunoService extends EntidadeService<Pessoa> {

    private final AlunoRepository alunoRepository;
    private final EscolaRepository escolaRepository;
    private final EscolaValidator escolaValidator;
    private final SalaService salaService;
    private final EnderecoService enderecoService;
    private final AlunoValidation alunoValidation;
    private final PessoaTelefoneService telefoneService;
    private final PessoaRepository pessoaRepository;
    private final CargaHorariaValidator cargaHorariaValidator;

    public AlunoDTO createAluno(AlunoDTO alunoDTO, UUID uuidEscola) {
        alunoValidation.validateAluno(alunoDTO);
        Escola escola = escolaRepository.findByUuid(uuidEscola);
        escolaValidator.validaEscola(escola);

        alunoDTO.setMatricula(MatriculaGenerator.gerarMatricula());
        List<Disciplina> disciplinasPadrao = List.of(Disciplina.PORTUGUES,
                Disciplina.MATEMATICA,
                Disciplina.INGLES);

        alunoDTO.addAllDisciplinas(disciplinasPadrao);

        int cargaHorario = CargaHorariaPorDisciplina.getCargaHoraria(alunoDTO.getDisciplinas(), alunoDTO.getSerieAno());

        cargaHorariaValidator.validateCargaHoraria(cargaHorario);
        alunoDTO.addCargaHoraria(cargaHorario);

        Pessoa ofAluno = new Pessoa(alunoDTO);
        Pessoa aluno = customSave(ofAluno, escola);

        escola.addAluno(aluno);

        List<Sala> salasComMesmaSerie = escola.getSalas().stream()
                .filter(e -> e.getSerieAno().equals(aluno.getSerie()))
                .filter(e -> e.getCapacidadeAlunos() > 0)
                .toList();
        alunoValidation.validateSalas(salasComMesmaSerie, aluno.getSerie());
        Sala sala = getSalaComMenosAluno(salasComMesmaSerie);
        sala.addAluno(aluno);
        salaService.update(sala);
        return new AlunoDTO(aluno);
    }

    private Pessoa customSave(Pessoa aluno, Escola escola) {
        UUID uuid = UUID.randomUUID();
        aluno.setUuid(uuid);
        PessoaTelefone telefone = aluno.getTelefone();

        if (telefone != null) {
            telefone.setPessoaUUID(uuid);
            telefoneService.create(telefone);
        }
        Endereco enderecoDTO = aluno.getEndereco();
        Endereco endereco = enderecoService.findByCidadeAndCepAndEstado(
                enderecoDTO.getCidade(),
                enderecoDTO.getCep(),
                enderecoDTO.getEstado()
        );

        if (endereco == null) {
            enderecoService.saveEndereco(enderecoDTO);
        } else {
            aluno.setEndereco(endereco);
        }
        aluno.setEscola(escola);
        return super.save(aluno);
    }

    private Sala getSalaComMenosAluno(List<Sala> salas) {
        int capacidadeMaxima = salas.stream()
                .mapToInt(Sala::getCapacidadeAlunos)
                .max()
                .orElseThrow();

        return salas.stream()
                .filter(sala -> sala.getCapacidadeAlunos() == capacidadeMaxima)
                .min(Comparator.comparingLong(Sala::getId))
                .orElseThrow();
    }


    @Override
    protected JpaRepository<Pessoa, Long> repository() {
        return alunoRepository;
    }

    public List<AlunoDataGridDTO> findAll() {
        return alunoRepository.findAll().stream().map(AlunoDataGridDTO::new).toList();
    }

    public AlunoDataGridDTO updateByUuid(AlunoDTO alunoDTO) {
        alunoValidation.validateAluno(alunoDTO);
        Pessoa aluno = alunoRepository.findByUuid(alunoDTO.getUuid());
        alunoValidation.validateAluno(aluno);
        aluno.updateDados(alunoDTO);
        Pessoa alunoSave = save(aluno);
        return new AlunoDataGridDTO(alunoSave);
    }

    public void deleteAluno(UUID uuidAluno) {
        Pessoa alunofounded = pessoaRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(alunofounded);
        pessoaRepository.delete(alunofounded);
    }

    public void removeDisciplina(UUID uuid, Disciplina disciplina) {
        Pessoa aluno = alunoRepository.findByUuid(uuid);
        alunoValidation.validateAluno(aluno);
        aluno.removeDisciplina(disciplina);
        save(aluno);
    }

    public void updateAluno(AlunoDTO alunoDTO) {
        alunoValidation.validateAluno(alunoDTO);
        Pessoa aluno = alunoRepository.findByUuid(alunoDTO.getUuid());
        alunoValidation.validateAluno(aluno);
        aluno.setNome(alunoDTO.getNome());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.updateDisciplinas(alunoDTO.getDisciplinas());
        super.save(aluno);
    }
}
