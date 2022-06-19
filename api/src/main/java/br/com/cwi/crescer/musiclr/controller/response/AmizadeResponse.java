package br.com.cwi.crescer.musiclr.controller.response;

import br.com.cwi.crescer.musiclr.model.SituacaoAmizade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder @Getter @Setter
public class AmizadeResponse {

    private Long id;
    private LocalDateTime dataSolicitacao;
    private SituacaoAmizade situacao;
    private PessoaResponse solicitante;
}
