package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.domain.repositories.EscolaRepository;
import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoService;
import com.example.project.study.domain.model.instituicao.escola.sala.Sala;
import com.example.project.study.domain.model.instituicao.GenericTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EscolaService extends EntidadeService<Escola> {

    private final EscolaRepository escolaRepository;
    private final EscolaValidator escolaValidator;
    private final EnderecoValidation enderecoValidation;
    private final EnderecoService enderecoService;


    @Override
    protected JpaRepository<Escola, Long> repository() {
        return escolaRepository;
    }

    public List<EscolaDataGridDTO> findAll() { // 2
        return escolaRepository.findAll().stream().map(EscolaDataGridDTO::new).toList();
    }

    public EscolaDTO createEscola(EscolaDTO escolaDTO) {
        escolaValidator.validateDTO(escolaDTO);
        enderecoValidation.validateEnderecoDTO(escolaDTO);
        enderecoValidation.validateCidadeAndEstadoAndCEP(escolaDTO.getEndereco());
        Escola escola = Escola.of(escolaDTO);
        findOrCreateEnderecoAndSetOnEscola(Endereco.of(escolaDTO.getEndereco()), escola);
        Escola save = super.save(escola);
        return new EscolaDTO(save);
    }

    public EscolaDTO updateByUuid(EscolaDTO escolaDTO) { // 3
        Escola escola = escolaRepository.findByUuid(escolaDTO.getUuid());
        escolaValidator.validaEscola(escola);
        escola.updateNome(escolaDTO.getNome());
        enderecoValidation.validateCidadeAndEstadoAndCEP(escolaDTO.getEndereco());
        findOrCreateEnderecoAndSetOnEscola(Endereco.of(escolaDTO.getEndereco()), escola);
        Escola escolaAtualizada = save(escola);
        return new EscolaDTO(escolaAtualizada);
    }

    public void update(Escola escola) { // 4
        super.save(escola);
    }

    public Escola load(UUID uuidEscola) { // 5
        Escola escola = escolaRepository.findByUuid(uuidEscola);
        if (escola != null) {
            escola.initialize();
        }
        return escola;
    }

    private void findOrCreateEnderecoAndSetOnEscola(Endereco enderecoToValidate, Escola escola) {
        Endereco recuperado = enderecoService.findByCidadeAndCepAndEstado(
                enderecoToValidate.getCidade(),
                enderecoToValidate.getCep(),
                enderecoToValidate.getEstado()
        );
        if (recuperado == null) {
            Endereco endereco = enderecoService.saveEndereco(enderecoToValidate);
            escola.setEndereco(endereco);
        } else {
            escola.setEndereco(recuperado);
        }
    }

    public void deleteByUuid(UUID uuidEscola) {
        Escola escolaFounded = escolaRepository.findByUuid(uuidEscola);
        escolaValidator.validaEscola(escolaFounded);
        escolaRepository.delete(escolaFounded);
    }

    public List<GenericTO<Escola>> findAllBySerie(String serie) {
        if (serie.isBlank()) {
            return escolaRepository.findAll().stream().map(e -> new GenericTO<Escola>(e.getUuid().toString(), e.getNome()) )
                    .toList();
        }
       return escolaRepository.findAllBySalasBySerieAno(SerieAno.from(serie).name())
                .stream().map(e -> new GenericTO<Escola>(e.getUuid().toString(), e.getNome()) )
                .toList();
    }

    public void deleteSala(Escola escola, Sala sala) {
        escola.getSalas().remove(sala);
        super.save(escola);
    }
}
