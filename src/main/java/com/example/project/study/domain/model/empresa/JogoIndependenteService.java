package com.example.project.study.domain.model.empresa;

import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import com.example.project.study.domain.repositories.EmpresaDesenvolvedoraRepository;
import com.example.project.study.domain.repositories.JogoIndependenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogoIndependenteService extends EntidadeService<JogoIndependente> {

    private final JogoIndependenteRepository jogoIndependenteRepository;
    private final EmpresaDesenvolvedoraRepository empresaDesenvolvedoraRepository;

    public JogoIndependenteDTO createJogoIndependente(JogoIndependenteDTO jogoIndependenteDTO) {
        UUID uuidEmpresaDesenvolvedora = jogoIndependenteDTO.getUuidEmpresaDesenvolvedora();
        validaUUIDDTO(uuidEmpresaDesenvolvedora);
        EmpresaDesenvolvedora byUuid = empresaDesenvolvedoraRepository.findByUuid(uuidEmpresaDesenvolvedora);
        validateUUIDEntidade(byUuid);
        JogoIndependente jogoIndependente = new JogoIndependente(jogoIndependenteDTO, byUuid);
        JogoIndependente salvarJogoIndependente = super.save(jogoIndependente);
        return new JogoIndependenteDTO(salvarJogoIndependente);
    }


    private static void validateUUIDEntidade(EmpresaDesenvolvedora byUuid) {
        if (byUuid == null) {
            throw new EntidadeNaoEncontradaException("Não encontramos registros para o UUID informado. Favor revisar!");
        }
    }

    private static void validaUUIDDTO(UUID uuidEmpresaDesenvolvedora) {
        if (uuidEmpresaDesenvolvedora == null) {
            throw new EntidadeNaoEncontradaException("Não foi informado o UUID da empresa desenvolvedora. Favor revisar!");
        }
    }

    public List<JogoIndependenteDTO> findAll() {
        return jogoIndependenteRepository.findAll().stream().map(JogoIndependenteDTO::new).toList();
    }

    @Override
    protected JpaRepository<JogoIndependente, Long> repository() {
        return null;
    }
}
