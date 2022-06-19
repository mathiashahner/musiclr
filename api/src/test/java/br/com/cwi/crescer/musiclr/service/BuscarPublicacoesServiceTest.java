package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPublicacoesServiceTest {

    @InjectMocks
    private BuscarPublicacoesService buscarPublicacoesService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private DetalharPublicacaoService detalharPublicacaoService;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private BuscarPessoaService buscarPessoaService;

    @Test
    @DisplayName("deve buscar publicacoes")
    public void deveBuscarPublicacoes() {

        Pessoa pessoaAutenticada = Pessoa.builder().id(13L).build();
        Pessoa pessoa = Pessoa.builder().id(14L).build();

        Publicacao publicacao = Publicacao.builder()
                .id(1L).pessoa(pessoa).build();

        List<Publicacao> publicacaoList = new ArrayList<>();
        publicacaoList.add(publicacao);

        Page<Publicacao> publicacoes = new PageImpl<>(publicacaoList);

        PublicacaoResponse publicacaoResponse = PublicacaoResponse.builder().id(1L).build();
        List<PublicacaoResponse> publicacaoResponseList = new ArrayList<>();
        publicacaoResponseList.add(publicacaoResponse);

        when(pessoaAutenticadaService.get()).thenReturn(pessoaAutenticada);
        when(buscarPessoaService.findById(14L)).thenReturn(pessoa);

        when(amizadeRepository.verificarAmizadePorSituacao(
                pessoaAutenticada, publicacao.getPessoa(), APROVADA)).thenReturn(true);

        when(publicacaoRepository.findAllByPessoaOrderByDataCriacaoDesc(
                pessoa, Pageable.ofSize(1))).thenReturn(publicacoes);

        when(detalharPublicacaoService.detalhar(publicacoes, pessoaAutenticada)).thenReturn(publicacaoResponseList);

        Page<PublicacaoResponse> result = buscarPublicacoesService.buscar(14L, Pageable.ofSize(1));

        verify(pessoaAutenticadaService).get();
        verify(buscarPessoaService).findById(14L);
        verify(amizadeRepository).verificarAmizadePorSituacao(
                pessoaAutenticada, publicacao.getPessoa(), APROVADA);

        verify(publicacaoRepository).findAllByPessoaOrderByDataCriacaoDesc(pessoa, Pageable.ofSize(1));
        verify(detalharPublicacaoService).detalhar(publicacoes, pessoaAutenticada);

        Assertions.assertEquals(result.getContent().get(0).getId(), 1L);
    }
}