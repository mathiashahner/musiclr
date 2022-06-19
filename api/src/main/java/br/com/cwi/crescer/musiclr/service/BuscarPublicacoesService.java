package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;

@Service
public class BuscarPublicacoesService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private DetalharPublicacaoService detalharPublicacaoService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private BuscarPessoaService buscarPessoaService;

    public Page<PublicacaoResponse> buscar(Long pessoaId, Pageable pageable) {

        Pessoa pessoaAutenticada = pessoaAutenticadaService.get();
        Pessoa pessoa = buscarPessoaService.findById(pessoaId);
        Page<Publicacao> publicacoes = null;

        boolean isAmigo = amizadeRepository
                .verificarAmizadePorSituacao(pessoaAutenticada, pessoa, APROVADA);

        if (isAmigo || Objects.equals(pessoa, pessoaAutenticada)) {
            publicacoes = publicacaoRepository.findAllByPessoaOrderByDataCriacaoDesc(pessoa, pageable);
        } else {
            publicacoes = publicacaoRepository.findAllByPessoaAndIsPublicaTrueOrderByDataCriacaoDesc(pessoa, pageable);
        }

        List<PublicacaoResponse> response = detalharPublicacaoService.detalhar(publicacoes, pessoaAutenticada);

        return new PageImpl<>(response);
    }
}
