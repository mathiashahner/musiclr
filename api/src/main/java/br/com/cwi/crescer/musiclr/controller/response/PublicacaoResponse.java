package br.com.cwi.crescer.musiclr.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicacaoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private byte[] imagem;
    private byte[] audio;
    private boolean isPublica;
    private LocalDateTime dataCriacao;
    private PessoaResponse pessoa;
    private boolean isCurtida;
    private Long numCurtidas;
    private Long numComentarios;
    private List<ComentarioResponse> comentarios;

    @Builder @Getter @Setter
    public static class ComentarioResponse {

        private Long id;
        private PessoaResponse pessoa;
        private String texto;
        private LocalDateTime dataCriacao;
    }
}
