package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;
import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.SOLICITADA;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class AceitarAmizadeService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    public void aceitar(Long amizadeId) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Amizade amizade = buscarAmizadeService.findById(amizadeId);

        if (!Objects.equals(amizade.getSolicitado(), pessoa) || amizade.getSituacao() != SOLICITADA) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Não existe uma solicitação pendente para você com este ID.");
        }

        amizade.setSituacao(APROVADA);
        amizadeRepository.save(amizade);
    }
}
