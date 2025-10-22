package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Pagamento findByUuid(UUID uuid);

    UUID uuid(UUID uuid);
}
