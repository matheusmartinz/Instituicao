package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Mensalidade mensalidade = Mensalidade.of(mensalidadeDTO, aluno.getUuid());
        Mensalidade mensalidadeSave = super.save(mensalidade);
        return MensalidadeDTO.of(mensalidadeSave);
    }


    public MensalidadeDTO pagarBoleto(MensalidadeDTO mensalidadeDTO, UUID aluno) {
        Pessoa alunoPagar = pessoaRepository.findByUuid(aluno);
        alunoValidation.validateAluno(alunoPagar);

        Mensalidade mensalidade = mensalidadeRepository.findByUuid(mensalidadeDTO.getUuid());

        mensalidade.setDataPagamento(LocalDate.now());
        mensalidade.setValorPago(mensalidadeDTO.getValorPagamento());
        mensalidade.setStatusPagamento(StatusPagamento.PAGO);

        return MensalidadeDTO.of(super.save(mensalidade));
    }

    public MensalidadeDTO consultaBoleto(MensalidadeDTO mensalidadeDTO, UUID aluno) {
        Pessoa alunoConsultar = pessoaRepository.findByUuid(aluno);
        alunoValidation.validateAluno(alunoConsultar);

        Mensalidade mensalidadeConsulta = mensalidadeRepository.findByUuid(mensalidadeDTO.getUuid());

        mensalidadeValidation.mensalidadeFound(mensalidadeConsulta);
        mensalidadeValidation.isVencido(mensalidadeDTO, mensalidadeConsulta);
        mensalidadeValidation.isPago(mensalidadeConsulta);
        return mensalidadeDTO;
    }

    @Override
    protected JpaRepository<Mensalidade, Long> repository() {
        return mensalidadeRepository;
    }
}
