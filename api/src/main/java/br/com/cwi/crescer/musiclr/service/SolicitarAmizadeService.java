package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.SOLICITADA;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private BuscarPessoaService buscarPessoaService;

    public void solicitar(Long pessoaId) {

        Pessoa solicitante = pessoaAutenticadaService.get();
        Pessoa solicitado = buscarPessoaService.findById(pessoaId);

        if (Objects.equals(solicitante.getId(), solicitado.getId())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você não pode solicitar amizade para si mesmo.");
        }

        if (Objects.nonNull(amizadeRepository.buscarAmizade(solicitante, solicitado))) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você já é amigo desta pessoa ou já existe uma solicitação pendente.");
        }

        Amizade amizade = new Amizade();
        amizade.setSolicitado(solicitado);
        amizade.setSolicitante(solicitante);
        amizade.setSituacao(SOLICITADA);
        amizade.setDataSolicitacao(LocalDateTime.now());

        amizadeRepository.save(amizade);
    }
}
