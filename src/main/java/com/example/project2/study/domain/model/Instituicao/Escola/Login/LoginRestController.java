package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import com.example.project2.study.Api.SuperRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginRestController extends SuperRestController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginDTO> createLogin(@RequestBody LoginDTO loginDTO) {
        try {
            LoginDTO toReturn = loginService.createLogin(loginDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<LoginDTO> createLogin(
//            @RequestPart("login") String loginJson,
//            @RequestPart(value = "imagemFile", required = false) MultipartFile imagemFile
//    ) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            LoginDTO loginDTO = objectMapper.readValue(loginJson, LoginDTO.class);
//
//            LoginDTO toReturn = loginService.createLogin(loginDTO, imagemFile);
//            return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
//        } catch (JsonProcessingException e) {
//            return ResponseEntity.badRequest().build();
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


//    @PostMapping("/authentic2")
//    public ResponseEntity<String> autenticarLogin(@RequestBody LoginDTO loginDTO) {
//        try {
//            Login autenticado = loginService.authenticLogin(loginDTO);
//            if (autenticado != null) {
//                return ResponseEntity.ok("Email bem-sucedido");
//            }
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorreto");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//    }

    @PostMapping("/authentic")
    public ResponseEntity<LoginDTO> autenticarLogin2(@RequestBody LoginDTO loginDTO) {
        Login login = loginService.authenticLogin(loginDTO);
        LoginDTO toReturn = LoginDTO.of(login);
        return ResponseEntity.ok(toReturn);
    }
}
