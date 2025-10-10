package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.api.SuperRestController;
import com.example.project.study.domain.repositories.EscolaRepository;
import com.example.project.study.domain.model.instituicao.GenericTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping
    public ResponseEntity<EscolaDTO> atualizacaoEscola(@RequestBody EscolaDTO escolaDTO) {
     EscolaDTO atualizarEscola = escolaService.updateByUuid(escolaDTO);
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
