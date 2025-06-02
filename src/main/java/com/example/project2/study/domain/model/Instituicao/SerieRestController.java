package com.example.project2.study.domain.model.Instituicao;


import com.example.project2.study.Api.SuperRestController;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieRestController extends SuperRestController {

    @GetMapping
    public ResponseEntity<List<String>> getSeries(){
        List<String> todosValores = SerieAno.getTodosValores();
        return ResponseEntity.ok(todosValores);
    }
}
