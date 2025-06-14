package com.example.project2.study.domain.model.Carro;

import com.example.project2.study.Exceptions.StudyExceptions;
import com.example.project2.study.domain.Repositories.CarroRepository;
import com.example.project2.study.dtos.Carro.CarroDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarroService {

    @Autowired
    CarroRepository carroRepository;

    public CarroDTO createdCarro( CarroDTO carroDTO) {
            Carro carro = new Carro(carroDTO);
            Carro salvarCarro = carroRepository.save(carro);
            return new CarroDTO(salvarCarro);
    }

    public List<CarroDTO> createdCarros(List<CarroDTO> carroDTOs) {
        List<Carro> carros = carroDTOs.stream()
                .map(Carro::new) //transforma DTO em entidade
                .toList();

        List<Carro> carrosSalvos = carroRepository.saveAll(carros);

        return carrosSalvos.stream().map(CarroDTO::new).toList();
    }

    public List<CarroDTO> findAll() {
        return carroRepository.findAll().stream().map(CarroDTO::new).toList();
    }

    public void deleteAll() {
        carroRepository.deleteAll();
    }

    public void deleteAllNulos() {
        carroRepository.deleteAllByNomeIsNullAndMarcaIsNullAndAnoIsNull();
    }

    public Carro attCarros(CarroDTO carroDTOAtt) {
        Carro carro = carroRepository.findByUuid(UUID.fromString(carroDTOAtt.uuid));
        if (carro == null) {
            throw new RuntimeException("Carro nao encontrando");
        }
            carro.setNome(carroDTOAtt.nome);
            carro.setMarca(carroDTOAtt.marca);
            carro.setAno(carroDTOAtt.ano);

            return carroRepository.save(carro);
    }
}
