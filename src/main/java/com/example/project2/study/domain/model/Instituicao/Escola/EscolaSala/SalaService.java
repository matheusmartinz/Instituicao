package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaService;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SalaService extends EntidadeService<Sala> {

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    EscolaRepository escolaRepository;

    @Autowired
    EscolaService escolaService;

    @Autowired
    EscolaValidator escolaValidator;

    @Autowired
    SalaValidator salaValidator;

    public SalaDTO createSala(SalaDTO salaDTO, UUID escolaUUID){
        Escola escola = escolaRepository.findByUuid(escolaUUID);
        escolaValidator.validaEscola(escola);
        Sala toSave = new Sala(salaDTO);
        toSave.setEscola(escola);
        Sala salvo = super.save(toSave);
        escola.addSala(salvo);
        escolaService.update(escola);
        return SalaDTO.of(salvo);
    }

    @Override
    public JpaRepository<Sala, Long> repository() {
        return salaRepository;
    }


    public void update(Sala sala) {
        super.save(sala);
    }

    public List<SalaDataGridDTO> getSalas(){
        return repository().findAll().stream()
                .map(SalaDataGridDTO::new)
                .sorted(Comparator.comparing(e -> e.numeroSala))
               .toList();
    }

    public SalaDTO updateSalaByUuid(SalaDTO salaDTO, UUID uuidEscola) {
        salaValidator.validateSalaDTO(salaDTO);
        Escola escola = escolaRepository.findByUuid(uuidEscola);
        escolaValidator.validaEscola(escola);
        Sala toSave = new Sala(salaDTO);
        toSave.setEscola(escola);
        Sala salvo = super.save(toSave);
        return SalaDTO.of(salvo);
    }
}
