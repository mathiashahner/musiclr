package br.com.cwi.crescer.musiclr.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResponse {

    private String mensagem;
    private List<ErroDetalheResponse> erros;

    @Builder
    @Getter
    public static class ErroDetalheResponse {

        private String campo;
        private String mensagem;
    }
}
