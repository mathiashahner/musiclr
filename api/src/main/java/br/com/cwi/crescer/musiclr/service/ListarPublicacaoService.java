package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private DetalharPublicacaoService detalharPublicacaoService;

    public Page<PublicacaoResponse> listar(Pageable pageable) {

        Pessoa pessoa = pessoaAutenticadaService.get();

        Page<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoes(pessoa.getId(), pageable);
        List<PublicacaoResponse> response = detalharPublicacaoService.detalhar(publicacoes, pessoa);

        return new PageImpl<>(response);
    }
}
