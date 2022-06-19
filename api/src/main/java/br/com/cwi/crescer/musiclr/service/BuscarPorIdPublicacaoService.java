package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class BuscarPorIdPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private DetalharPublicacaoService detalharPublicacaoService;

    public PublicacaoResponse buscarPorId(Long id) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = buscarPublicacaoService.findById(id);

        boolean isAmigo = amizadeRepository
                .verificarAmizadePorSituacao(publicacao.getPessoa(), pessoa, APROVADA);

        if (!isAmigo && !publicacao.isPublica() && !Objects.equals(pessoa, publicacao.getPessoa())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você não tem permissão para ver esta publicação.");
        }

        return detalharPublicacaoService.getPublicacaoResponse(pessoa, publicacao);
    }
}
