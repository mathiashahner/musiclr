package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Amizade findById(Long amizadeId) {
        return amizadeRepository
                .findById(amizadeId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Amizade não encontrada"));
    }
}
