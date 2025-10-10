package com.example.project.study.domain.model.carro;


import com.example.project.study.dtos.carro.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO carroSaved = carroService.createdCarro(carroDTO);
        return ResponseEntity.ok(carroSaved);
    }

    @PostMapping("/carros")
    public ResponseEntity<List<CarroDTO>> salvarCarros(@RequestBody List<CarroDTO> carroDTOs) {
        List<CarroDTO> carrosSavos = carroService.createdCarros(carroDTOs);
         return ResponseEntity.ok(carrosSavos);
    }

    @GetMapping
    public ResponseEntity<List<CarroDTO>> findCarros() {
        List<CarroDTO> acharCarros = carroService.findAll();
        return ResponseEntity.ok(acharCarros);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarCarros() {
        carroService.deleteAll();
        return ResponseEntity.ok("Todos os registros deletados com sucesso");
    }

    @DeleteMapping("/nulos")
    public ResponseEntity<String> deleteNulos() {
        carroService.deleteAllNulos();
        return ResponseEntity.ok("Todos os registros nulos foram deletados");
    }

    @PutMapping
    public ResponseEntity<Carro> atualizarCarro(@RequestBody CarroDTO carroDTO) {
        Carro atualizaCarro = carroService.attCarros(carroDTO);
        return ResponseEntity.ok(atualizaCarro);
    }
}
