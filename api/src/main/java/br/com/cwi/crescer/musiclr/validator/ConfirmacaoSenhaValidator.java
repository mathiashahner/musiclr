package br.com.cwi.crescer.musiclr.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ConfirmacaoSenhaValidator {

    public void validar(String senha, String confirmacaoSenha) {

        if (!senha.equals(confirmacaoSenha)) {
            throw new ResponseStatusException(BAD_REQUEST, "Confirmação da senha é inválida.");
        }
    }
}
