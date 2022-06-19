package br.com.cwi.crescer.musiclr.security.service;

import br.com.cwi.crescer.musiclr.security.controller.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public LoginResponse login() {
        LoginResponse response = new LoginResponse();

        response.setUsuarioId(usuarioAutenticadoService.getId());

        return response;
    }
}
