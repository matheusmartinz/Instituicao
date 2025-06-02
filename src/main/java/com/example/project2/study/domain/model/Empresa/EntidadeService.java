package com.example.project2.study.domain.model.Empresa;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Transactional
public abstract class EntidadeService<T extends EntidadeIdUUID> {

    protected abstract JpaRepository<T, Long> repository();

    protected T save(T entidade) {
        if (entidade.getUuid() == null) {
            entidade.setUuid(UUID.randomUUID());
        }
        return repository().save(entidade);
    }
}
