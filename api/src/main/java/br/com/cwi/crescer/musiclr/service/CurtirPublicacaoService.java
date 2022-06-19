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
public class CurtirPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    public ReagirPublicacaoResponse curtir(Long id) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = buscarPublicacaoService.findById(id);

        if (curtidaRepository.existsByPublicacaoAndPessoa(publicacao, pessoa)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você já curtiu esta publicação.");
        }

        Curtida curtida = new Curtida();
        curtida.setPessoa(pessoa);
        curtida.setPublicacao(publicacao);

        curtidaRepository.save(curtida);

        return new ReagirPublicacaoResponse(curtidaRepository
                .countByPublicacao(publicacao));
    }
}
