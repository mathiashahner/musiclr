package br.com.cwi.crescer.musiclr.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalharPessoaResponse {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String apelido;
    private byte[] imagemPerfil;
    private boolean isAmigo;
}
