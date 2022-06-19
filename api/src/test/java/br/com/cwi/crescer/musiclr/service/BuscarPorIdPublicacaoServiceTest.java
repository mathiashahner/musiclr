package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPorIdPublicacaoServiceTest {

    @InjectMocks
    private BuscarPorIdPublicacaoService buscarPorIdPublicacaoService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private DetalharPublicacaoService detalharPublicacaoService;

    @Test
    @DisplayName("deve buscar amizade")
    public void deveBuscarAmizade() {

        Pessoa pessoa = Pessoa.builder().id(13L).build();
        Pessoa amigo = Pessoa.builder().id(14L).build();

        Publicacao publicacao = Publicacao.builder()
                .id(1L).pessoa(amigo).build();

        PublicacaoResponse response = PublicacaoResponse
                .builder().id(1L).isPublica(true).build();

        when(pessoaAutenticadaService.get()).thenReturn(pessoa);
        when(buscarPublicacaoService.findById(1L)).thenReturn(publicacao);
        when(amizadeRepository.verificarAmizadePorSituacao(amigo, pessoa, APROVADA)).thenReturn(true);
        when(detalharPublicacaoService.getPublicacaoResponse(pessoa, publicacao)).thenReturn(response);

        PublicacaoResponse result = buscarPorIdPublicacaoService.buscarPorId(1L);

        verify(pessoaAutenticadaService).get();
        verify(buscarPublicacaoService).findById(1L);
        verify(amizadeRepository).verificarAmizadePorSituacao(amigo, pessoa, APROVADA);
        verify(detalharPublicacaoService).getPublicacaoResponse(pessoa, publicacao);

        assertEquals(result.getId(), 1L);
        assertTrue(result.isPublica());
    }
}