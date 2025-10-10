package com.example.project.study.domain.model.instituicao;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import com.example.project.study.domain.model.instituicao.escola.sala.tarefa.TarefaDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String disciplina;
    @NotEmpty @NotNull
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

}