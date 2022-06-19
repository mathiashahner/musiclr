package br.com.cwi.crescer.musiclr.security.mapper;

import br.com.cwi.crescer.musiclr.security.controller.request.UsuarioRequest;
import br.com.cwi.crescer.musiclr.security.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest usuario) {
        return Usuario.builder()
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }
}
