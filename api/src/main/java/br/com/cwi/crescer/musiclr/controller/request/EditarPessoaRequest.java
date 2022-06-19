package br.com.cwi.crescer.musiclr.controller.request;

import lombok.Data;

@Data
public class EditarPessoaRequest {

    private String nome;
    private String apelido;
    private byte[] imagemPerfil;
}
