package br.com.cwi.crescer.musiclr.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditarPublicacaoRequest {

    @NotNull
    @JsonProperty("isPublica")
    private boolean isPublica;
}
