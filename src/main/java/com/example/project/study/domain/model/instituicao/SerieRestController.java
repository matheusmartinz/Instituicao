package com.example.project.study.domain.model.instituicao;


import com.example.project.study.api.SuperRestController;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
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
