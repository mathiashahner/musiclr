package br.com.cwi.crescer.musiclr.validator;

import br.com.cwi.crescer.musiclr.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class EmailUnicoValidator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(BAD_REQUEST, "Este e-mail já está cadastrado.");
        }
    }
}
