package com.example.project.study.domain.model.instituicao.escola.pessoa;

import com.example.project.study.domain.model.empresa.EntidadeService;
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
