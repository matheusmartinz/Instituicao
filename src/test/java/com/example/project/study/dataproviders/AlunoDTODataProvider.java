package com.example.project.study.dataproviders;

import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.PessoaTelefoneDTO;

public class AlunoDTODataProvider {

    public static AlunoDTO createAlunoDTO(String serie, String nome,
                                          String cpf, EnderecoDTO enderecoDTO,
                                          String email, PessoaTelefoneDTO telefoneDTO, Boolean bolsista) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome(nome);
        alunoDTO.setSerieAno(serie);
        alunoDTO.alterarCpf(cpf);
        alunoDTO.setEndereco(enderecoDTO);
        alunoDTO.setEmail(email);
        alunoDTO.setTelefone(telefoneDTO);
        alunoDTO.setBolsista(bolsista);
        return alunoDTO;
    }

}
