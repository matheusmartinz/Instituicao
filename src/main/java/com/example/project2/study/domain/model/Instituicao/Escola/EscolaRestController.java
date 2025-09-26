package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.Api.SuperRestController;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.GenericTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/escola")
public class EscolaRestController extends SuperRestController {

    @Autowired
    EscolaService escolaService;

    @Autowired
    EscolaRepository escolaRepository;

    @PostMapping
    public ResponseEntity<EscolaDTO> createEscola(@RequestBody EscolaDTO escolaDTO) {
         EscolaDTO toReturn = escolaService.createEscola(escolaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
    }

    @GetMapping
    public ResponseEntity<List<EscolaDataGridDTO>> findAll(){
        List<EscolaDataGridDTO> listarEscola = escolaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listarEscola);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<EscolaDTO> atualizacaoEscola(@RequestBody EscolaDTO escolaDTO, @PathVariable UUID uuid) {
     EscolaDTO atualizarEscola = escolaService.updateByUuid(escolaDTO, uuid);
     return ResponseEntity.ok(atualizarEscola);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> excluirEscola(@PathVariable UUID uuid) {
         escolaService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generics")
    public ResponseEntity<List<GenericTO<Escola>>> listAllEscolasTO(@RequestParam(required = false) String serie) {
       List<GenericTO<Escola>> tos = escolaService.findAllBySerie(serie);
        return ResponseEntity.ok(tos);
    }
}
