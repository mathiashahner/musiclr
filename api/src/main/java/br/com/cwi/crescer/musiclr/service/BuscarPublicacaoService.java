package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class BuscarPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Publicacao findById(Long id) {
        return publicacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "Publicação não encontrada."));
    }
}
