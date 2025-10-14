package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
