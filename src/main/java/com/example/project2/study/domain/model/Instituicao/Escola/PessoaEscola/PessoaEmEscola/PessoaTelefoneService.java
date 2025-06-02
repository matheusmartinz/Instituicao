package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaEmEscola;

import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PessoaTelefoneService extends EntidadeService<PessoaTelefone> {
    private final PessoaTelefoneRepository pessoaTelefoneRepository;

    public PessoaTelefone savePessoaTelefone(PessoaTelefone pessoaTelefone) {
        return super.save(pessoaTelefone);
    }

    @Override
    protected JpaRepository<PessoaTelefone, Long> repository() {
        return pessoaTelefoneRepository;
    }

    public void create(PessoaTelefone telefone) {
        super.save(telefone);
    }
}
