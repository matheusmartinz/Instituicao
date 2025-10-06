package DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;

public class AlunoDTODataProvider {

    public static AlunoDTO createAlunoDTO(String serie, String nome,
                                          String cpf, EnderecoDTO enderecoDTO,
                                          String email, PessoaTelefoneDTO telefoneDTO) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome(nome);
        alunoDTO.setSerieAno(serie);
        alunoDTO.alterarCpf(cpf);
        alunoDTO.setEndereco(enderecoDTO);
        alunoDTO.setEmail(email);
        alunoDTO.setTelefone(telefoneDTO);
        return alunoDTO;
    }

}
