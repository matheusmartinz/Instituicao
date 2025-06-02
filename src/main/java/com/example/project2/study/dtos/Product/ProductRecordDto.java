package com.example.project2.study.dtos.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value,  String sobreNome, Integer age, BigDecimal desconto){

}



//    private final ProductModelService productModelService;
//
//    public ProductRestController(ProductModelService productModelService) {
//        this.productModelService = productModelService;
//    }
//
//    @GetMapping("/by-id")
//    public ResponseEntity<ProductModel> getByID(@RequestParam Long id) {
//        ProductModel byId = productModelService.findById(id);
//        return ResponseEntity.ok(byId);
//    }
//
//    @GetMapping("/by-uuid")
//    public ResponseEntity<ProductModel> findByUUId(@RequestParam UUID uuid){
//        ProductModel byUUId = productModelService.findByUUID(uuid);
//        return ResponseEntity.ok(byUUId);
//    }
//    @PostMapping
//    public void createProduct() {
//        productModelService.createTeste();
//    }

