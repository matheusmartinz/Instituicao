package com.example.project.study.domain.model.empresa;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
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

    protected void delete(T entidade) {
        repository().delete(entidade);
    }
}
