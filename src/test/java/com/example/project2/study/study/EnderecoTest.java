package com.example.project2.study.study;

import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.UF;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class EnderecoTest {

//    @Autowired
//    private EnderecoService enderecoService;

    @Test
    public void testEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("87060-550");
        enderecoDTO.setCidade( "Maringá");
        enderecoDTO.setEstado(UF.PR.toString());

        Endereco endereco = Endereco.of(enderecoDTO);
        String descritivo = endereco.getDescritivoCidadeUF();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(endereco.getCep()).isEqualTo("87060550");
            s.assertThat(endereco.getCidade()).isEqualTo("Maringá");
            s.assertThat(endereco.getEstado()).isEqualTo(UF.PR);
            s.assertThat(descritivo).isEqualTo("Maringá - PR");
        });
    }
}
