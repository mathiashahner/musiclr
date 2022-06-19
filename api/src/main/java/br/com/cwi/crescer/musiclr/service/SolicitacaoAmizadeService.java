package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.AmizadeResponse;
import br.com.cwi.crescer.musiclr.mapper.AmizadeMapper;
import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.crescer.musiclr.mapper.PessoaMapper.toResponse;
import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.SOLICITADA;

@Service
public class SolicitacaoAmizadeService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<AmizadeResponse> solicitacoes() {

        Pessoa pessoa = pessoaAutenticadaService.get();

        List<Amizade> solicitacoesAmizades = amizadeRepository
                .findAllBySolicitadoIdAndSituacao(pessoa.getId(), SOLICITADA);
        
        return solicitacoesAmizades.stream()
                .map(amizade -> {
                    AmizadeResponse response = AmizadeMapper.toResponse(amizade);
                    response.setSolicitante(toResponse(amizade.getSolicitante()));
                    return response;
                })
                .collect(Collectors.toList());
    }
}
