package br.com.cwi.crescer.musiclr.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaResponse {

    private Long id;
    private String nome;
    private String apelido;
    private boolean isAmigo;
    private byte[] imagemPerfil;
}
