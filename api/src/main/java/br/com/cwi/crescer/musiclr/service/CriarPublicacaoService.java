package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.CriarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.cwi.crescer.musiclr.mapper.CriarPublicacaoMapper.toEntity;

@Service
public class CriarPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public void criar(CriarPublicacaoRequest request) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = toEntity(request);

        publicacao.setDataCriacao(LocalDateTime.now());
        publicacao.setPessoa(pessoa);

        publicacaoRepository.save(publicacao);
    }
}
