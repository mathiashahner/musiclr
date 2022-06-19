package br.com.cwi.crescer.musiclr.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CriarPublicacaoRequest {

    @NotEmpty
    private String descricao;

    @NotEmpty
    private byte[] imagem;

    @NotNull
    @JsonProperty("isPublica")
    private boolean isPublica;

    private String titulo;
    private byte[] audio;
}
