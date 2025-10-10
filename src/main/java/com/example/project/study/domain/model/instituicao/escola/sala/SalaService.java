package com.example.project.study.domain.model.instituicao.escola.sala;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.Escola;
import com.example.project.study.domain.model.instituicao.escola.EscolaService;
import com.example.project.study.domain.model.instituicao.escola.EscolaValidator;
import com.example.project.study.domain.repositories.EscolaRepository;
import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaService extends EntidadeService<Sala> {

    private final SalaRepository salaRepository;
    private final EscolaRepository escolaRepository;
    private final EscolaService escolaService;
    private final EscolaValidator escolaValidator;
    private final SalaValidator salaValidator;

    public SalaDTO createSala(SalaDTO salaDTO, UUID escolaUUID) {
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

    public List<SalaDataGridDTO> getSalas() {
        return repository().findAll().stream()
                .map(SalaDataGridDTO::new)
                .sorted(Comparator.comparing(e -> e.numeroSala))
                .toList();
    }

    public SalaDTO updateSalaByUuid(SalaDTO salaDTO, UUID uuidSala) {
        salaValidator.validateSalaDTO(salaDTO);
        Sala sala = salaRepository.findByUuid(uuidSala);
        if (sala != null) {
            sala.updateSala(salaDTO);
            Escola escola = sala.getEscola();

            Sala salaUpdate = super.save(sala);

            return SalaDTO.of(salaUpdate);
        } else {
            throw new EntidadeNaoEncontradaException("Sala não encontrada para edição");
        }
    }

    public void deleteSala(UUID uuidSala) {
        Sala sala = salaRepository.findByUuid(uuidSala);
        Escola escola = sala.getEscola();

        if (sala == null) {
            throw new EntidadeNaoEncontradaException("Sala inexistente");
        }

        if (escola != null) {
            escola.getSalas().remove(sala);
        }

        sala.setEscola(null);

        salaRepository.delete(sala);
    }
}
