package DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaDTO;

public class EscolaDTODataProvider {

        public static EscolaDTO createAlunoDTO(String nome, EnderecoDTO enderecoDTO ) {
            EscolaDTO escolaDTO = new EscolaDTO();
            escolaDTO.nome = nome;
            escolaDTO.endereco = enderecoDTO;
            return escolaDTO;
        }
}
