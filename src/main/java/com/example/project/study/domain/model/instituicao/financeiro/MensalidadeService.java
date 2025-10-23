package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensalidadeService extends EntidadeService<Mensalidade> {
    private final AlunoValidation alunoValidation;
    private final PessoaRepository pessoaRepository;
    private final MensalidadeRepository mensalidadeRepository;
    private final MensalidadeValidation mensalidadeValidation;

    public MensalidadeDTO createBoleto(UUID uuidAluno) {
        Pessoa aluno = pessoaRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(aluno);
        MensalidadeDTO mensalidadeDTO = new MensalidadeDTO();
        MensalidadeJurosMultaDTO mensalidadeBySerie = MensalidadeAlunoSerieJurosMulta.findMensalidadeBySerie(aluno.getSerie());
        mensalidadeDTO.setMensalidadeJurosMulta(mensalidadeBySerie);
        mensalidadeValidation.validateBoletoCreated(mensalidadeDTO, aluno);

        return MensalidadeDTO.of(super.save(Mensalidade.of(mensalidadeDTO, aluno.getUuid())));
    }

    public MensalidadeDTO consultaMensalidade(MensalidadeDTO mensalidadeDTO, UUID aluno) {
        Pessoa alunoConsultar = pessoaRepository.findByUuid(aluno);
        alunoValidation.validateAluno(alunoConsultar);

        Mensalidade mensalidadeConsulta = mensalidadeRepository.findByUuid(mensalidadeDTO.getUuid());

        mensalidadeValidation.checkIsNull(mensalidadeConsulta);
        mensalidadeValidation.isVencido(mensalidadeDTO, mensalidadeConsulta);
        mensalidadeValidation.isPago(mensalidadeConsulta);
        return mensalidadeDTO;
    }

    @Override
    protected JpaRepository<Mensalidade, Long> repository() {
        return mensalidadeRepository;
    }

    public List<MensalidadeDTO> findAll() {
        return mensalidadeRepository.findAll().stream().map(MensalidadeDTO::of).toList();
    }

    public MensalidadeDTO findByUuid(UUID uuidBoleto) {
        Mensalidade mensalidade = mensalidadeRepository.findByUuid(uuidBoleto);
        mensalidadeValidation.checkIsNull(mensalidade);
        return MensalidadeDTO.of(mensalidade);
    }

    public void deleteBoleto(UUID uuidBoleto) {
        Mensalidade mensalidade = mensalidadeRepository.findByUuid(uuidBoleto);
        mensalidadeValidation.checkIsNull(mensalidade);
        mensalidadeRepository.delete(mensalidade);
    }

    public MensalidadeDTO updateByUuid(MensalidadeDTO mensalidadeDTO, UUID uuidBoleto) {
        Mensalidade mensalidade = mensalidadeRepository.findByUuid(uuidBoleto);
        mensalidadeValidation.checkIsNull(mensalidade);
        mensalidadeValidation.validatePagarBoleto(mensalidade, mensalidadeDTO);
        Mensalidade updateDados = Mensalidade.updateMensalidade(mensalidadeDTO);
        return MensalidadeDTO.of(super.save(updateDados));
    }
}
