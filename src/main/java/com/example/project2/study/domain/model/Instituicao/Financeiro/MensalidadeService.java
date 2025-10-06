package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
public class MensalidadeService extends EntidadeService<Mensalidade> {

    private AlunoValidation alunoValidation;
    private PessoaRepository pessoaRepository;
    private MensalidadeRepository mensalidadeRepository;

    public MensalidadeDTO createMensalidade(UUID uuidAluno, MensalidadeDTO mensalidadeDTO) {
        Pessoa aluno = pessoaRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(aluno);


        return MensalidadeDTO.of(Mensalidade);
    }

    @Override
    protected JpaRepository<Mensalidade, Long> repository() {
        return mensalidadeRepository;
    }
}
