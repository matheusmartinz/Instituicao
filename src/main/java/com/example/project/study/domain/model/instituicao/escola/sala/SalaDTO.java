package com.example.project.study.domain.model.instituicao.escola.sala;

import com.example.project.study.domain.model.instituicao.escola.sala.tarefa.TarefaDTO;
import com.example.project.study.domain.model.instituicao.escola.PessoaDTO;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SalaDTO {
    @NotNull
    public String numeroSala;
    @NotNull
    public SerieAno serieAno;
    public Integer capacidadeAlunos = 30;

    public List<PessoaDTO> alunos = new LinkedList<>();
    public List<PessoaDTO> professores = new LinkedList<>();
    public List<TarefaDTO> tarefas = new LinkedList<>();
    public UUID uuid;

    public static SalaDTO of(Sala sala) {
        SalaDTO toReturn = new SalaDTO();
        toReturn.serieAno = sala.getSerieAno();
        toReturn.numeroSala = sala.getNumeroSala();
        toReturn.alunos = PessoaDTO.listOfPessoaDTO(sala.getAlunos());
        toReturn.capacidadeAlunos = sala.getCapacidadeAlunos();
        toReturn.professores = PessoaDTO.listOfPessoaDTO(sala.getProfessores());
        toReturn.uuid = sala.getUuid();
        return toReturn;
    }

    public static List<SalaDTO> listOf(List<Sala> salas) {
        return salas.stream().map(SalaDTO::of).toList();
    }
}
