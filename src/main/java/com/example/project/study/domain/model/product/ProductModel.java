//package com.example.project2.study.domain.model.Product;
//
//import com.example.project2.study.dtos.Product.ProductRecordDto;
//import jakarta.persistence.*;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import java.util.Random;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "TB_PRODUCTS")
//public class ProductModel implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String sobrenome;
//    private UUID idProduct;
//    private String name;
//    private BigDecimal value;
//    @NotNull
//    private Integer age;
//
//
//    public ProductModel(ProductRecordDto productRecordDto) {
//        Random random = new Random();
//        this.setIdProduct(UUID.randomUUID());
//        this.setName(productRecordDto.name());
//        this.setSobrenome(productRecordDto.sobreNome());
//        this.setAge(productRecordDto.age());
//        this.setValue(productRecordDto.value().compareTo(BigDecimal.valueOf(50) )>= 0
//                ? BigDecimal.valueOf(random.nextDouble() * 100).setScale(2, RoundingMode.HALF_UP)
//                : productRecordDto.value()
//        );
//    }
//
//    public static List<ProductModel> ofs(@Valid List<ProductRecordDto> productRecordDto) {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//        for (ProductRecordDto recordDto : productRecordDto) {
//            productModels.add(new ProductModel(recordDto));
//        }
//        return productModels;
//    }
//}
