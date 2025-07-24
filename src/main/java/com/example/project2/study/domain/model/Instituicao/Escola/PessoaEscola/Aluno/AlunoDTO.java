package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa.TarefaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.UtilsFormatter;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
public class AlunoDTO extends PessoaDTO {
    public String matricula;
    public List<TarefaDTO> tarefas = new LinkedList<>();
    public List<Disciplina> disciplinas;
    public String serieAno;


    public static List<AlunoDTO> listOf(List<Pessoa> alunos) {
        return alunos.stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO(Pessoa aluno) {
        this.uuid = aluno.getUuid();
        this.matricula = aluno.getMatricula();
        this.tarefas = TarefaDTO.listOf(aluno.getTarefas());
        this.disciplinas = aluno.getDisciplinas();
        this.serieAno = aluno.getSerie().getValor();
        this.nome = aluno.getNome();
        this.cpf = UtilsFormatter.formatCpf(aluno.getCpf());
        this.email = aluno.getEmail().toLowerCase();
        this.telefone = ofNullable(aluno.getTelefone())
                .map(PessoaTelefoneDTO::new)
                .orElse(null);
        this.endereco = ofNullable(aluno.getEndereco())
                .map(EnderecoDTO::new)
                .orElse(null);
    }
}
