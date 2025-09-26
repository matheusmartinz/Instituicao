package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa.TarefaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.ProfessorDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

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

    public static SalaDTO of(Sala sala) {
        SalaDTO toReturn = new SalaDTO();
        toReturn.serieAno = sala.getSerieAno();
        toReturn.numeroSala = sala.getNumeroSala();
        toReturn.alunos = PessoaDTO.listOfPessoaDTO(sala.getAlunos());
        toReturn.capacidadeAlunos = sala.getCapacidadeAlunos();
        toReturn.professores = PessoaDTO.listOfPessoaDTO(sala.getProfessores());
        return toReturn;
    }

    public static List<SalaDTO> listOf(List<Sala> salas) {
        return salas.stream().map(SalaDTO::of).toList();
    }
}
