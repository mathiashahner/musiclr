package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.DetalharPessoaResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.crescer.musiclr.mapper.DetalharPessoaMapper.toResponse;
import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;

@Service
public class DetalharPessoaService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private BuscarPessoaService buscarPessoaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public DetalharPessoaResponse detalhar(Long id) {

        Pessoa pessoaAutenticada = pessoaAutenticadaService.get();
        Pessoa pessoa = buscarPessoaService.findById(id);

        boolean isAmigo = amizadeRepository
                .verificarAmizadePorSituacao(pessoaAutenticada, pessoa, APROVADA);

        DetalharPessoaResponse response = toResponse(pessoa);
        response.setAmigo(isAmigo);

        return response;
    }
}
