package com.example.project.study.domain.model.instituicao.escola.pessoa;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import com.example.project.study.domain.model.instituicao.Disciplina;
import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.Escola;
import com.example.project.study.domain.model.instituicao.escola.PessoaDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.CargaHorariaPorDisciplina;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import com.example.project.study.domain.model.instituicao.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Entity
@Getter
@Setter
public class Pessoa extends EntidadeIdUUID {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_telefone")
    private PessoaTelefone telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco", nullable = false)
    private Endereco endereco;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_fk")
    private Escola escola;
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    @ElementCollection
    private List<Disciplina> disciplinas = new LinkedList<>();


    // Dados aluno
    @Column(unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SerieAno serie;
    @OneToMany
    private List<Tarefa> tarefas = new LinkedList<>();
    private Integer cargaHoraria;
    private Boolean bolsista;


    //Dados Professor
    private String quantidadeAulas;

    protected Pessoa() {
    }

    public static List<Pessoa> listOfPessoa(List<PessoaDTO> pessoas) {
        return pessoas.stream().map(Pessoa::new).toList();
    }

    public List<Disciplina> getDisciplinas() {
        return List.copyOf(disciplinas); //TODO COLOCAR TODAS AS LISTAS DESTA FORMA - TIRAR DE add.ALUNO - add.SALA E RETORNAR SOMENTE A COPIA DA LISTA IGUAL DESTA FORMA
    }

    protected Pessoa(PessoaDTO pessoaDTO) {
        this.setNome(pessoaDTO.getNome());
        this.setCpf(pessoaDTO.getCpf());
        this.setEmail(pessoaDTO.getEmail());
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
    }

    public Pessoa(AlunoDTO pessoaDTO) {
        this.setNome(pessoaDTO.getNome());
        this.setCpf(pessoaDTO.getCpf());
        this.setEmail(pessoaDTO.getEmail());
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
        this.setMatricula(pessoaDTO.getMatricula());
        this.setTipoPessoa(TipoPessoa.ALUNO);
        this.setSerie(SerieAno.from(pessoaDTO.getSerieAno()));
        this.setDisciplinas(pessoaDTO.getDisciplinas());
        this.setCargaHoraria(pessoaDTO.getCargaHoraria());
        this.setBolsista(pessoaDTO.isBolsista());
    }


    private static Endereco getEndereco(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.getEndereco())
                .map(e -> Endereco.of(pessoaDTO.getEndereco()))
                .orElse(null);
    }

    private static PessoaTelefone getTelefone(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.getTelefone())
                .map(e -> new PessoaTelefone(pessoaDTO.getTelefone()))
                .orElse(null);
    }

    public void updateDados(AlunoDTO alunoDTO) {
        this.setNome(alunoDTO.getNome());
        this.setTelefone(new PessoaTelefone(alunoDTO.getTelefone()));
        this.setSerie(SerieAno.from(alunoDTO.getSerieAno()));
        this.setDisciplinas(alunoDTO.getDisciplinas());
    }

    public void removeDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
        updateCargaHoraria();
    }

    public void updateDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas.clear();
        this.disciplinas.addAll(disciplinas);
        updateCargaHoraria();
    }

    public void updateDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
        updateCargaHoraria();
    }

    private void updateCargaHoraria() {
        this.setCargaHoraria(CargaHorariaPorDisciplina.getCargaHoraria(this.disciplinas, this.serie.getValor()));
    }

    public boolean isBolsista() {
        return bolsista;
    }
}
