package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class RemoverAmizadeService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private BuscarPessoaService buscarPessoaService;

    public void remover(Long pessoaId) {

        Pessoa solicitante = pessoaAutenticadaService.get();
        Pessoa solicitado = buscarPessoaService.findById(pessoaId);

        if (Objects.isNull(amizadeRepository.buscarAmizade(solicitante, solicitado))) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você precisa ser amigo antes de remover uma amizade.");
        }

        Amizade amizade = amizadeRepository.buscarAmizade(solicitante, solicitado);
        amizadeRepository.delete(amizade);
    }
}
