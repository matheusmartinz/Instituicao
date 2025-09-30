package DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;

public class AlunoDTODataProvider {

    public static AlunoDTO createAlunoDTO(String serie, String nome,
                                          String cpf, EnderecoDTO enderecoDTO,
                                          String email, PessoaTelefoneDTO telefoneDTO) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.nome = nome;
        alunoDTO.serieAno = serie == null ? null : serie;
        alunoDTO.cpf = cpf;
        alunoDTO.endereco = enderecoDTO;
        alunoDTO.email = email;
        alunoDTO.telefone = telefoneDTO;
        return alunoDTO;
    }

}
