package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.response.AmizadeResponse;
import br.com.cwi.crescer.musiclr.model.Amizade;

public class AmizadeMapper {
    public static AmizadeResponse toResponse(Amizade amizade) {
        return AmizadeResponse.builder()
                .id(amizade.getId())
                .dataSolicitacao(amizade.getDataSolicitacao())
                .situacao(amizade.getSituacao())
                .build();
    }
}
