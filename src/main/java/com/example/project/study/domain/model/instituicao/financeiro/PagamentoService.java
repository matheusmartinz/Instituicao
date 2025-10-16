package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService extends EntidadeService<Pagamento> {
    private final MensalidadeRepository mensalidadeRepository;
    private final PessoaRepository pessoaRepository;
    private final AlunoValidation alunoValidation;
    private final PagamentoRepository pagamentoRepository;


    public PagamentoDTO pagarMensalidade(MensalidadeDTO mensalidadeDTO, UUID uuidAluno, PagamentoDTO pagamentoDTO) {
        Pessoa alunoFounded = pessoaRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(alunoFounded);

        Mensalidade mensalidade = mensalidadeRepository.findByUuid(mensalidadeDTO.getUuid());
        mensalidade.updateValorPagamentoEmDia(mensalidadeDTO);

        return PagamentoDTO.of(super.save(Pagamento.of(pagamentoDTO)));
    }

    @Override
    protected JpaRepository<Pagamento, Long> repository() {
        return pagamentoRepository;
    }
}
