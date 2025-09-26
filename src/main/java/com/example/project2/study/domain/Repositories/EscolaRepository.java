package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {
        Escola findByUuid(UUID uuid);

@Query(value = """
    SELECT DISTINCT e.* 
    FROM escola e 
    LEFT JOIN escola_salas es ON e.id = es.escola_fk 
    LEFT JOIN sala s ON es.salas_fk = s.id 
    WHERE s.serie_ano = :serie_ano
    """, nativeQuery = true)
List<Escola> findAllBySalasBySerieAno(@Param("serie_ano") String serieAno);

    UUID uuid(UUID uuid);
}
