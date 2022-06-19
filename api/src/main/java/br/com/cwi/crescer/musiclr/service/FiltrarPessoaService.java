package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PessoaResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import br.com.cwi.crescer.musiclr.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.crescer.musiclr.mapper.PessoaMapper.toResponse;
import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;

@Service
public class FiltrarPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    public Page<PessoaResponse> buscar(String request, Pageable pageable) {

        Pessoa pessoa = pessoaAutenticadaService.get();

        Page<Pessoa> pessoas = pessoaRepository
                .filtrarPorNomeEmail(request, pageable);

        List<PessoaResponse> response = pessoas.stream()
                .map(p -> {
                    boolean isAmigo = amizadeRepository
                            .verificarAmizadePorSituacao(p, pessoa, APROVADA);

                    PessoaResponse pessoaResponse = toResponse(p);
                    pessoaResponse.setAmigo(isAmigo);
                    return pessoaResponse;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(response);
    }
}
