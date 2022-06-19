package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.response.PessoaResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;

public class PessoaMapper {

    public static PessoaResponse toResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .apelido(pessoa.getApelido())
                .imagemPerfil(pessoa.getImagemPerfil())
                .build();
    }
}
