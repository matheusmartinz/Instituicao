package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MensalidadeDTO extends EntidadeIdUUIDDTO {
    private StatusPagamento statusPagamento = StatusPagamento.EM_ABERTO;
    private UUID alunoFK;
    private BigDecimal valorPagamento = BigDecimal.ZERO;
    private LocalDate dataVencimento;
    private LocalDate dataEmissao = LocalDate.now();
    private BigDecimal juros = BigDecimal.ZERO;
    private BigDecimal multa = BigDecimal.ZERO;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private LocalDateTime dataPagamento;



    private MensalidadeDTO(Mensalidade mensalidade) {
        super.setId(mensalidade.getId());
        super.setUuid(mensalidade.getUuid());

        this.setStatusPagamento(mensalidade.getStatusPagamento());
        this.setValorPago(mensalidade.getValorPago());
        this.setDataEmissao(mensalidade.getDataEmissao());
        this.setDataVencimento(mensalidade.getDataVencimento());
        this.setAlunoFK(mensalidade.getAlunoFK());
        this.setValorPagamento(mensalidade.getValorPagamento());
    }

    public static MensalidadeDTO of(Mensalidade mensalidadeSave) {
        return new MensalidadeDTO(mensalidadeSave);
    }

}
