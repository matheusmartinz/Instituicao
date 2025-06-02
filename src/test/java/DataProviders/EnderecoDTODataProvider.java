package DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;

public class EnderecoDTODataProvider {

    public static EnderecoDTO ofMaringa() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.cidade = "Maringá";
        enderecoDTO.cep = "87060-550";
        enderecoDTO.estado = "PR";
        return enderecoDTO;
    }

    public static EnderecoDTO generic(String cidade, String cep, String estado) {
        return EnderecoDTO.of(cidade, cep, estado);
    }
}
