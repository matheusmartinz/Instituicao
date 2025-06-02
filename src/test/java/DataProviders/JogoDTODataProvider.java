package DataProviders;

import com.example.project2.study.domain.model.Empresa.Genero;
import com.example.project2.study.domain.model.Empresa.JogoIndependenteDTO;
import com.example.project2.study.domain.model.Empresa.Plataforma;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class JogoDTODataProvider {

    public static JogoIndependenteDTO getDarkSouls(UUID uuidEmpresaDesenvolvedora) {
        JogoIndependenteDTO jogoIndependenteDTO = new JogoIndependenteDTO();
        jogoIndependenteDTO.setTitulo("Dark Souls |||");
        List<Genero> generos = jogoIndependenteDTO.getGeneros();
        generos.add(Genero.RPG);
        generos.add(Genero.ACAO);
        jogoIndependenteDTO.setGeneros(generos);
        jogoIndependenteDTO.setPlataforma(Plataforma.PS5);
        LocalDate dataLancamento = LocalDate.of(2016, 4, 24);
        jogoIndependenteDTO.setDataLancamento(dataLancamento);
        jogoIndependenteDTO.setPreco(BigDecimal.valueOf(192.58));
        jogoIndependenteDTO.setNomeEquipe("MATHEUS E EU");
        jogoIndependenteDTO.setUuidEmpresaDesenvolvedora(uuidEmpresaDesenvolvedora);
        jogoIndependenteDTO.setIsFinanciado(jogoIndependenteDTO.getDataLancamento().getYear() % 2 == 0);
        return jogoIndependenteDTO;
    }
}
