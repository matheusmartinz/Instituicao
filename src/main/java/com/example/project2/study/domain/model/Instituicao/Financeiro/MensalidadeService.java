package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensalidadeService extends EntidadeService<Mensalidade> {
    private final AlunoValidation alunoValidation;
    private final PessoaRepository pessoaRepository;
    private final MensalidadeRepository mensalidadeRepository;
    private final MensalidateValidation mensalidateValidation;

    public MensalidadeDTO createBoleto(UUID uuidAluno, MensalidadeDTO mensalidadeDTO) {
        Pessoa aluno = pessoaRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(aluno);
        mensalidadeDTO.setValorPagamento(MensalidadeAluno.findMensalidadeBySerie(aluno.getSerie()));
        mensalidateValidation.validateBoletoCreated(mensalidadeDTO, aluno);      //---- Método para calcular o valor total a pagar do aluno
//        mensalidadeDTO.setValorPago(valorMensalidadeAluno); ---- Setar o valor depois de validar e calcular tudo
        Mensalidade mensalidade = Mensalidade.of(mensalidadeDTO, aluno.getUuid());
        Mensalidade mensalidadeSave = super.save(mensalidade);
        return MensalidadeDTO.of(mensalidadeSave);
    }

    @Override
    protected JpaRepository<Mensalidade, Long> repository() {
        return mensalidadeRepository;
    }
}
