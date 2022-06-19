package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.request.CriarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.model.Publicacao;

public class CriarPublicacaoMapper {

    public static Publicacao toEntity(CriarPublicacaoRequest request) {
        return Publicacao.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .imagem(request.getImagem())
                .audio(request.getAudio())
                .isPublica(request.isPublica())
                .build();
    }
}
