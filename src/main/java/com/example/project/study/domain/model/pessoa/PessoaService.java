//package com.example.project2.study.domain.model.Pessoa;
//
//import com.example.project2.study.domain.Repositories.PessoaRepository;
//import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
//import com.example.project2.study.dtos.Pessoa.PessoaDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service // estou colocando que é do tipo "seviço" - Onde faço a regra de negócio / Lógica aplicada
//public class PessoaService {
//
//    @Autowired // estou basicamente criando
//    PessoaRepository pessoaRepository; //Injeção do PessoaRepository no PessoaService ( Acesso as informações )
//
//
//    public PessoaDTO createPessoa(PessoaDTO pessoaDTO) { //Estou criando um método publico PessoaDTO ( o tipo de retorno ), o nome do método é createPessoa
//        pessoaDTO.setCpf(                                 //PessoaDTO pessoaDTO é o parâmetro que o método recebe
//                pessoaDTO.getCpf().replaceAll("[\\.\\-]", "")
//        );
//
//        if (pessoaDTO.getCpf() == null) {
////            throw new
//        }
//        if (pessoaDTO.getCpf().length() != 11) {
//
//        }
////        if (pessoaDTO.getCpf().equals(pessoaDTO.getCpf())) {
////            throw new StudyExceptions("Erro ao salvar Pessoa",
////                    "PessoaService.Salvar()",
////                    List("CPF já existe", "CEP inválido"));
////        }
//        Pessoa pessoa = Pessoa(pessoaDTO); //Estou chamando a classe Pessoa renomeando como pessoa e que vai receber Pessoa(como parâmetro das informações pessoaDTO)
//        Pessoa salvaNoBanco = pessoaRepository.save(pessoa); // Aqui mesma ideia, estou chamando a classe Pessoa, renomeando como salvaNoBanco = onde vai receber pessoa.repositório."save"(pessoa), que vai acabar de receber do DTO
//        return PessoaDTO(salvaNoBanco); // Retorna PessoaDTO(salvaNoBanco) justamente o que acabamos de declarar acima.
//    }
//
//    public PessoaDTO updatePessoa(UUID uuidPessoa, PessoaDTO pessoaDTO) {
//        Pessoa pessoa = pessoaRepository.findByUuid(uuidPessoa);
//        pessoa.update(pessoaDTO);
//        Pessoa save = pessoaRepository.save(pessoa);
//        return PessoaDTO(save);
//    }
//
//    public List<PessoaDTO> findAll() {
//        return pessoaRepository.findAll().stream()
//                .map(PessoaDTO::of)
//                .toList();
//    }
//
//    public void deleteAll() {
//        pessoaRepository.deleteAll();
//    }
//
//    public long findCount() {
//        return pessoaRepository.count();
//    }
//}
