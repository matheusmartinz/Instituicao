package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceiroService extends EntidadeService<Pagamento> {

    @Autowired
    private final PagamentoRepository pagamentoRepository;
    @Autowired
    private final MensalidadeService mensalidadeService;
    @Autowired
    private final MensalidadeRepository mensalidadeRepository;
    @Autowired
    private final PessoaRepository pessoaRepository;
    @Autowired
    private final AlunoValidation alunoValidation;
    @Autowired
    MensalidadeValidation mensalidadeValidation;

    @Override
    protected JpaRepository<Pagamento, Long> repository() {
        return pagamentoRepository;
    }


    public PagamentoDTO pagarMensalidade(FinanceiroDTO financeiroDTO) {

        Mensalidade mensalidadeFounded =  mensalidadeRepository.findByUuid(financeiroDTO.getMensalidadeDTO().getUuid());
        mensalidadeValidation.checkIsNull(mensalidadeFounded);

        MensalidadeDTO mensalidadeDTO = MensalidadeDTO.of(financeiroDTO);

        mensalidadeFounded.updateValorPagamentoEmDia(mensalidadeService.consultaMensalidade(mensalidadeDTO, financeiroDTO.getMensalidadeDTO().getAlunoFK()));

        return PagamentoDTO.of(super.save(Pagamento.of(financeiroDTO)));
    }
}
