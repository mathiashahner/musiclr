package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Comentario;

import java.util.List;
import java.util.stream.Collectors;

public class ComentarioMapper {

    public static List<PublicacaoResponse.ComentarioResponse> toResponse(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> PublicacaoResponse.ComentarioResponse.builder()
                        .id(comentario.getId())
                        .texto(comentario.getTexto())
                        .dataCriacao(comentario.getDataCriacao())
                        .pessoa(PessoaMapper.toResponse(comentario.getPessoa()))
                        .build())
                .collect(Collectors.toList());
    }
}
