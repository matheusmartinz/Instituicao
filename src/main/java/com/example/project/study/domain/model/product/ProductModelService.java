//package com.example.project2.study.domain.model.Product;
//
//import com.example.project2.study.domain.Repositories.ProductModelRepository;
//import com.example.project2.study.dtos.Product.ProductModelDTO;
//import com.example.project2.study.dtos.Product.ProductRecordDto;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class ProductModelService {
//    private ProductModelRepository productModelRepository;
//    private ProductModelValidation productModelValidation;
//
//    public void deleteAge(Integer age) {
//        List<ProductModel> all = productModelRepository.findAllByAge(age);
//        if (all.size() > 1) {
////            throw new StudyExceptions("Mais de um registro encontrado");
//        }
//        if (all.isEmpty()) {
////            throw new StudyExceptions("Nenhum registro encontrado");
//        }
//        ProductModel productModel = all.get(0);
//        productModelRepository.delete(productModel);
//    }
//
//    public ProductModelDTO create(@Valid ProductRecordDto productRecordDto) {
//        ArrayList<String> erros = new ArrayList<>();
//        String sobreNome = productRecordDto.sobreNome();
//        if (productRecordDto.name().equals("Lapiseiras")) {
//            erros.add("A PONTA AINDA é FINA");
//        }
//        if (productRecordDto.age() <= 18) {
//            erros.add("NAO DA IRMAO, ELA Ainda Ê DE MENOR");
////            throw new StudyExceptions("Proibido de menor", erros);
//        }
//        ProductModel productModel = new ProductModel(productRecordDto);
//        ProductModel saved = productModelRepository.save(productModel);
//        return new ProductModelDTO(saved);
//    }
//
//    public void deleteByUUID(UUID uuid) {
//        ProductModel productModel = productModelRepository.findByIdProduct(uuid);
//        productModelValidation.isEntidadeValida(productModel);
//        productModelRepository.delete(productModel);
//    }
//
//    public List<ProductModelDTO> findAll() {
//        return productModelRepository.findAll().stream()
//                .map(ProductModelDTO::new)
//                .toList();
//    }
//}
////    public List<ProductModelDTO> createAll(@Valid List<ProductRecordDto> productRecordDto) {
////        List<ProductModel> ofs = ProductModels(productRecordDto);
////     //   validateOfs(ofs);
////        productModelRepository.saveAll(ofs);
////        List<ProductModelDTO> dtos = ofs.stream().map(ProductModelDTO::of).collect(Collectors.toList());
////        return dtos;
////    }
//
//
////    private static void validateOfs(List<ProductModel> ofs) {
////        for (ProductModel productModel : ofs) {
////            if (productModel.getName().equals("Lapiseiras")) {
////                throw new RuntimeException("NAO PODE LAPISEIRA");
////            }
////        }
////    }
//
//
///* Entidade @Entity,ele representa tudo que tem em 1 linha que será salva no banco;
//    ex: Public Class Pessoa {
//        private String nome;
//        private String sobreNome;
//    }
//
//
//    Nunca retornar uma entidade para o ResponseEntity(controller)
//
//    @Controller representa suas requisicoes, parametros que recebera e o que ira retornar, deve ser passado para a camade de service
//    @Service representa toda a logica de negocio que vc precisa rodar antes de salvar no Repository
//    @Repository responsavel por salvar, recuperar, atualizar as coisas no banco de dados.
//
//
//
// */