package com.example.project.study.domain.repositories;

import com.example.project.study.domain.model.instituicao.escola.Escola;
import com.example.project.study.domain.model.instituicao.escola.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {
    Escola findByUuid(UUID uuidEscola);

    @Query(value = """
            select escola.*
            from escola escola
                inner join sala sala on sala.escola_fk = escola.id
                where sala.serie_ano = :serie_ano;
            """, nativeQuery = true)
    List<Escola> findAllBySalasBySerieAno(@Param("serie_ano") String serieAno);

    UUID uuid(UUID uuid);

    List<Escola> findAllBySalasContaining(Sala sala);
}
