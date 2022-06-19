package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Publicacao;

public class ListarPublicacaoMapper {
    public static PublicacaoResponse toResponse(Publicacao publicacao) {
        return PublicacaoResponse.builder()
                .id(publicacao.getId())
                .titulo(publicacao.getTitulo())
                .descricao(publicacao.getDescricao())
                .imagem(publicacao.getImagem())
                .audio(publicacao.getAudio())
                .isPublica(publicacao.isPublica())
                .dataCriacao(publicacao.getDataCriacao())
                .build();
    }
}
