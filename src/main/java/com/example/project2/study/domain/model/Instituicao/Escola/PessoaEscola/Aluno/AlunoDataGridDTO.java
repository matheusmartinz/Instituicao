package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.UtilsFormatter;
import com.example.project2.study.domain.model.Instituicao.GenericTO;

import java.util.Optional;
import java.util.UUID;

public class AlunoDataGridDTO extends PessoaDataGridDTO {
    public String matricula;
    public String serie;
    public Integer tarefas;
    public Integer disciplinas;
    public UUID uuid;
    public GenericTO<Escola> escola;

    public AlunoDataGridDTO(Aluno aluno) {
        this.matricula = aluno.getMatricula();
        this.serie = aluno.getSerie().getValor();
        this.tarefas = aluno.getTarefas().size();
        this.disciplinas = aluno.getDisciplinas().size();
        this.nome = aluno.getNome();
        this.cpf = UtilsFormatter.formatCpf(aluno.getCpf());
        this.email = aluno.getEmail();
        this.fone = Optional.ofNullable(aluno.getTelefone()).map(PessoaTelefone::getFone).orElse(null);
        this.ddd = Optional.ofNullable(aluno.getTelefone()).map(PessoaTelefone::getDdd).orElse(null);
        this.cidadeEstado = String.format("%s - %s", aluno.getEndereco().getCidade(), aluno.getEndereco().getEstado());
        this.cep = UtilsFormatter.formatCep(aluno.getEndereco().getCep());
        this.uuid = aluno.getUuid();
//        this.escola = new GenericTO<Escola>()
    }
}
