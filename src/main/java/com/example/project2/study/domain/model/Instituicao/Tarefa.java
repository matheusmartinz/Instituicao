package com.example.project2.study.domain.model.Instituicao;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa.TarefaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarefa extends EntidadeIdUUID {
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    private String descricao;
    private LocalDate dataEntrega;
    private Boolean concluida;

    public Tarefa(TarefaDTO tarefaDTO) {
        this.setDisciplina(tarefaDTO.getDisciplina());
        this.setDescricao(tarefaDTO.getDescricao());
        this.setDataEntrega(getDataEntrega(tarefaDTO));
        this.setConcluida(false);
    }

    private static LocalDate getDataEntrega(TarefaDTO tarefaDTO) {
        return Optional.ofNullable(tarefaDTO.getDataEntrega()).orElse(LocalDate.now().plusDays(7L));
    }

    public static List<Tarefa> listOf(List<TarefaDTO> tarefaDTOS) {
        return tarefaDTOS.stream().map(Tarefa::new).toList();
    }


    @Override
    public boolean equals(Object o) {
        return false;
    }
}