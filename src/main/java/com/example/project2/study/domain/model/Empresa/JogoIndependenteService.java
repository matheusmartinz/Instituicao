package com.example.project2.study.domain.model.Empresa;

import com.example.project2.study.domain.Repositories.EmpresaDesenvolvedoraRepository;
import com.example.project2.study.domain.Repositories.JogoIndependenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public  class JogoIndependenteService extends EntidadeService<JogoIndependente>{

    @Autowired
    JogoIndependenteRepository jogoIndependenteRepository;

    @Autowired
    EmpresaDesenvolvedoraRepository empresaDesenvolvedoraRepository;

    public JogoIndependenteDTO createJogoIndependente(JogoIndependenteDTO jogoIndependenteDTO) {
        UUID uuidEmpresaDesenvolvedora = jogoIndependenteDTO.getUuidEmpresaDesenvolvedora();
        validaUUIDDTO(uuidEmpresaDesenvolvedora);
        EmpresaDesenvolvedora byUuid = empresaDesenvolvedoraRepository.findByUuid(uuidEmpresaDesenvolvedora);
        validateUUIDEntidade(byUuid);
        JogoIndependente jogoIndependente = new JogoIndependente(jogoIndependenteDTO, byUuid);
        JogoIndependente salvarJogoIndependente = super.save(jogoIndependente);
        JogoIndependenteDTO toReturn = new JogoIndependenteDTO(salvarJogoIndependente);
        return toReturn;
    }



    private static void validateUUIDEntidade(EmpresaDesenvolvedora byUuid) {
        if (byUuid == null) {
            throw new RuntimeException("Não encontramos registros para o UUID informado. Favor revisar!");
        }
    }

    private static void validaUUIDDTO(UUID uuidEmpresaDesenvolvedora) {
        if (uuidEmpresaDesenvolvedora == null) {
            throw new RuntimeException("Não foi informado o UUID da empresa desenvolvedora. Favor revisar!");
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
