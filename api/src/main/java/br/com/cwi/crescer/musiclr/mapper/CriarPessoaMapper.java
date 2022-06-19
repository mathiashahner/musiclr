package br.com.cwi.crescer.musiclr.mapper;

import br.com.cwi.crescer.musiclr.controller.request.CriarPessoaRequest;
import br.com.cwi.crescer.musiclr.model.Pessoa;

public class CriarPessoaMapper {

    public static Pessoa toEntity(CriarPessoaRequest request) {
        return Pessoa.builder()
                .nome(request.getNome())
                .dataNascimento(request.getDataNascimento())
                .imagemPerfil(request.getImagemPerfil())
                .apelido(request.getApelido())
                .build();
    }
}
