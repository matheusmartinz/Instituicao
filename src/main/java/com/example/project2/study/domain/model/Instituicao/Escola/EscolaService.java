package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoService;
import com.example.project2.study.domain.model.Instituicao.GenericTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
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
        enderecoValidation.validateCidadeAndEstadoAndCEP(escolaDTO.endereco);
        Escola escola = new Escola(escolaDTO);
        findOrCreateEnderecoAndSetOnEscola(new Endereco(escolaDTO.endereco), escola);
        Escola save = super.save(escola);
        return new EscolaDTO(save);
    }

    public EscolaDTO updateByUuid(EscolaDTO escolaDTO) { // 3
        Escola escola = escolaRepository.findByUuid(escolaDTO.uuid);
        escolaValidator.validaEscola(escola);
        escola.updateNome(escolaDTO.nome);
        enderecoValidation.validateCidadeAndEstadoAndCEP(escolaDTO.endereco);
        findOrCreateEnderecoAndSetOnEscola(new Endereco(escolaDTO.endereco), escola);
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
        if (serie == null) {
            return escolaRepository.findAll().stream().map(e -> new GenericTO<Escola>(e.getUuid().toString(), e.getNome()) )
                    .toList();
        }
       return escolaRepository.findAllBySalasBySerieAno(SerieAno.from(serie).name())
                .stream().map(e -> new GenericTO<Escola>(e.getUuid().toString(), e.getNome()) )
                .toList();
    }
}
