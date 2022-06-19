package br.com.cwi.crescer.musiclr.controller.request;

import br.com.cwi.crescer.musiclr.security.controller.request.UsuarioRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CriarPessoaRequest {

    @NotEmpty
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    private byte[] imagemPerfil;

    private String apelido;

    @NotNull
    private UsuarioRequest usuario;
}
