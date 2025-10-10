//package com.example.project2.study.Api.Products;
//
//import com.example.project2.study.Exceptions.StudyExceptions;
//import com.example.project2.study.domain.Repositories.ProductModelRepository;
//import com.example.project2.study.domain.model.Product.ProductModelService;
//import com.example.project2.study.dtos.Product.ProductModelDTO;
//import com.example.project2.study.dtos.Product.ProductRecordDto;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    ProductModelRepository productModelRepository;
//
//    @Autowired
//    ProductModelService productModelService;
//
//    @PostMapping
//    public ResponseEntity<List<ProductModelDTO>> saveProduct(@RequestBody @Valid List<ProductRecordDto> productRecordDto) {
//        ArrayList<ProductModelDTO> productModelDTOS = new ArrayList<>();
//        for (ProductRecordDto recordDto : productRecordDto) {
//            ProductModelDTO productModelDTO = productModelService.create(recordDto);
//            productModelDTOS.add(productModelDTO);
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(productModelDTOS);
//    }
//
//    @DeleteMapping("/delete-old")
//    public ResponseEntity<Void> deleteProduct(@RequestParam Integer age) {
//        if (age <= 0) {
////            throw new StudyExceptions("Valor invalido");
//        }
//        productModelService.deleteAge(age);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/delete-uuid")
//    public ResponseEntity<String> deleteByUUID(@RequestParam UUID uuid) {
//        productModelService.deleteByUUID(uuid);
//        return ResponseEntity.ok("Deletado com sucesso.");
//    }
//
//    @GetMapping("/find-all")
//    public ResponseEntity<List<ProductModelDTO>> findAll() {
//        return ResponseEntity.ok(productModelService.findAll());
//    }
//
//}
