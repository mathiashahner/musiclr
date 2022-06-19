package br.com.cwi.crescer.musiclr.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ComentarPublicacaoRequest {

    @NotEmpty
    private String texto;
}
