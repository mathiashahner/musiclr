package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Curtida;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.CurtidaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurtirPublicacaoServiceTest {

    @InjectMocks
    private CurtirPublicacaoService curtirPublicacaoService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Captor
    private ArgumentCaptor<Curtida> curtidaCapture;

    @Test
    @DisplayName("deve comentar publicacao")
    public void deveComentarPublicacao() {

        Pessoa pessoa = Pessoa.builder().id(13L).build();
        Publicacao publicacao = Publicacao.builder().id(1L).build();
        Curtida curtida = Curtida.builder().pessoa(pessoa).publicacao(publicacao).build();

        when(pessoaAutenticadaService.get()).thenReturn(pessoa);
        when(buscarPublicacaoService.findById(1L)).thenReturn(publicacao);
        when(curtidaRepository.existsByPublicacaoAndPessoa(publicacao, pessoa)).thenReturn(false);

        curtirPublicacaoService.curtir(1L);

        verify(pessoaAutenticadaService).get();
        verify(buscarPublicacaoService).findById(1L);
        verify(curtidaRepository).existsByPublicacaoAndPessoa(publicacao, pessoa);
        verify(curtidaRepository).save(curtidaCapture.capture());
        verify(curtidaRepository).countByPublicacao(publicacao);

        Curtida response = curtidaCapture.getValue();

        assertEquals(response.getPessoa(), curtida.getPessoa());
        assertEquals(response.getPublicacao(), curtida.getPublicacao());
    }
}