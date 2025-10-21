package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService extends EntidadeService<Pagamento> {
    private final MensalidadeRepository mensalidadeRepository;
    private final PagamentoRepository pagamentoRepository;


/*    public Pagamento pagarMensalidade(FinanceiroDTO financeiroDTO) {
        Mensalidade mensalidade = mensalidadeRepository.findByUuid(financeiroDTO.getMensalidadeDTO().getUuid());
        mensalidade.updateValorPagamentoEmDia(financeiroDTO.getMensalidadeDTO());

        return super.save(Pagamento.of(financeiroDTO));
    }*/

    @Override
    protected JpaRepository<Pagamento, Long> repository() {
        return pagamentoRepository;
    }
}
