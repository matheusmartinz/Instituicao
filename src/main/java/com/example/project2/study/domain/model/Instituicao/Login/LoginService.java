package com.example.project2.study.domain.model.Instituicao.Login;

import com.example.project2.study.domain.Repositories.LoginRepository;
import com.example.project2.study.domain.model.Empresa.EntidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService extends EntidadeService<Login> {

    private final LoginValidator loginValidator;

    @Autowired
    private LoginRepository loginRepository;

    public LoginDTO createLogin(LoginDTO loginDTO) {
        loginValidator.validateLoginDTO(loginDTO);
        loginValidator.cadastroLoginDTO(loginDTO);
        Login login = Login.of(loginDTO);
        Login save = save(login);
        return LoginDTO.of(save);
    }

    public Login authenticLogin(LoginDTO loginDTO) {
        return loginRepository.findByLoginAndSenha(loginDTO.getLogin(), loginDTO.getSenha());
    }

    @Override
    protected JpaRepository<Login, Long> repository() {
        return loginRepository;
    }

    public Login updateLogin(LoginDTO loginDTO) {
        loginValidator.validateLoginDTO(loginDTO);
        Login login = loginRepository.findByUuidAndSenha(loginDTO.getUuid(), loginDTO.getSenha());
        loginValidator.validateProfile(login);
        login.updateLogin(loginDTO);
        return save(login);
    }
}
