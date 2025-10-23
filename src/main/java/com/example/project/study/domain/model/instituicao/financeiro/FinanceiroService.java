package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.empresa.EntidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    private final MensalidadeValidation mensalidadeValidation;
    @Autowired
    private final PagamentoValidation pagamentoValidation;

    @Override
    protected JpaRepository<Pagamento, Long> repository() {
        return pagamentoRepository;
    }


//    public FinanceiroDTO pagarMensalidade(FinanceiroDTO financeiroDTO) {
//
//        Mensalidade mensalidadeFounded = mensalidadeRepository.findByUuid(financeiroDTO.getMensalidadeDTO().getUuid());
//        mensalidadeValidation.checkIsNull(mensalidadeFounded);
//
//        mensalidadeFounded.updateValorPagamentoEmDia(mensalidadeService.consultaMensalidade(financeiroDTO.getMensalidadeDTO(), financeiroDTO.getMensalidadeDTO().getAlunoFK()));
//
//        mensalidadeFounded.updateStatusPagamentoBoleto(financeiroDTO.getMensalidadeDTO());
//
//        pagamentoValidation.checkIsNull(financeiroDTO.getPagamentoDTO());
//
//        financeiroDTO.updateStatusPagamento();
//        Pagamento pagamentoSalvo = super.save(Pagamento.of(financeiroDTO.getPagamentoDTO()));
//
//        FinanceiroDTO financeiroDTOResponse = FinanceiroDTO.of(pagamentoSalvo);
//        financeiroDTOResponse.setMensalidadeDTO(MensalidadeDTO.of(mensalidadeFounded));
//
//        return financeiroDTOResponse;
//    }

    public PagamentoDTO pagarMensalidade(PagamentoDTO pagamentoDTO, UUID uuidMensalidade) {
        Mensalidade mensalidade = mensalidadeRepository.findByUuid(uuidMensalidade);
        mensalidadeValidation.checkIsNull(mensalidade);

        MensalidadeDTO mensalidadeDTO = MensalidadeDTO.of(mensalidade);
        mensalidadeService.consultaMensalidade(mensalidadeDTO, mensalidade.getAlunoFK());
        mensalidade.updateValorPagamento(mensalidadeDTO);
        mensalidade.updateStatusPagamentoBoleto(mensalidadeDTO);

        pagamentoDTO.updateStatusPagamento(mensalidadeDTO);

        return PagamentoDTO.of(super.save(Pagamento.of(pagamentoDTO)));
    }

    public void deletePagamento(UUID uuidPagamento) {
        pagamentoValidation.checkEntity(pagamentoRepository.findByUuid(uuidPagamento));
        super.delete(pagamentoRepository.findByUuid(uuidPagamento));
    }
}
