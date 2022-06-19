package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.response.DetalharPessoaResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;

public class DetalharPessoaMapper {

    public static DetalharPessoaResponse toResponse(Pessoa pessoa) {
        return DetalharPessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .apelido(pessoa.getApelido())
                .imagemPerfil(pessoa.getImagemPerfil())
                .build();
    }
}
