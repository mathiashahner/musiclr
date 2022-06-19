package br.com.cwi.crescer.musiclr.security.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String confirmacaoSenha;
}
