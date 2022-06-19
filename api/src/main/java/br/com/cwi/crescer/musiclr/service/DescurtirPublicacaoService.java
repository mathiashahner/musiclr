package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.ReagirPublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Curtida;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class DescurtirPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    public ReagirPublicacaoResponse descurtir(Long id) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = buscarPublicacaoService.findById(id);

        Curtida curtida = curtidaRepository.
                findByPublicacaoIdAndPessoaId(id, pessoa.getId())
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você precisa curtir esta publicação antes de remover sua curtida."));

        curtidaRepository.delete(curtida);

        return new ReagirPublicacaoResponse(curtidaRepository
                .countByPublicacao(publicacao));
    }
}
