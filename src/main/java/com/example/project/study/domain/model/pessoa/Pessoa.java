//package com.example.project2.study.domain.model.Pessoa;
//
//import com.example.project2.study.dtos.Pessoa.PessoaDTO;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(name = "TB_PESSOAS",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"cpf"}) // Garantir que o CPF seja único
//        }
//)
//public class Pessoa extends NomeIdade {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estou dizendo que o valor de UUID é gerado randomico
//    private Long id;
//    private String sobrenome;
//    @Column(name = "cpf") // Garantir que o CPF seja registrado de forma unica no banco
//    private String cpf;
//    private String cep;
//    private UUID uuid;
//    private Sexo sexo;
//
//    public static Pessoa of(PessoaDTO dto) {  // Está recebendo as informações de Pessoa DTO renomeando para dto e assim fazendo get.informação OBS: Além de eu estar criando uma classe publica Pessoa estou dando um nome para o método de of
//        Pessoa pessoa = new Pessoa(); //
//        pessoa.setNome(dto.getNome()); // recebendo como parâmetro (PessoaDTO dto
//        pessoa.setSobrenome(
//                OptionalNullable(
//                                dto.getSobrenome()) // Estou dizendo que o valor de Sobrenome é Opcional ( Caso o valor não seja nulo (ofnullable ) de
//                        .map(String::toLowerCase) // getSobrenome então (.map(s -> s.toLowercase()) porém, para resumir essa expressão usamos String::toLowerCase
//                        .orElse(null) // "se não" retorna Null mesmo
//        );
//        pessoa.setUuid(UUID.randomUUID());
//        pessoa.setCpf(dto.getCpf());
//        pessoa.setCep(dto.getCep());
//        return pessoa; // Vai pegar todos os Gets do DTO e retornar ele "pessoa"
//    }
//
//    public void update(PessoaDTO pessoaDTO) {
//        if (pessoaDTO.getNome() != null && !pessoaDTO.getNome().isBlank()) {
//            super.setNome(pessoaDTO.getNome());
//        }
//        if (pessoaDTO.getSobrenome() != null) {
//            this.sobrenome = pessoaDTO.getSobrenome();
//        }
//        this.cpf = pessoaDTO.getCpf();
//        this.cep = pessoaDTO.getCep();
//    }
//}
