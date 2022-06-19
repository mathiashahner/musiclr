package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.ComentarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.model.Comentario;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.ComentarioRepository;
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
class ComentarPublicacaoServiceTest {

    @InjectMocks
    private ComentarPublicacaoService comentarPublicacaoService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("deve comentar publicacao")
    public void deveComentarPublicacao() {

        Pessoa pessoa = Pessoa.builder().id(13L).build();
        Publicacao publicacao = Publicacao.builder().id(1L).build();

        Comentario comentario = Comentario.builder().texto("teste")
                .pessoa(pessoa).publicacao(publicacao).build();

        ComentarPublicacaoRequest request = new ComentarPublicacaoRequest();
        request.setTexto("teste");

        when(pessoaAutenticadaService.get()).thenReturn(pessoa);
        when(buscarPublicacaoService.findById(1L)).thenReturn(publicacao);

        comentarPublicacaoService.comentar(1L, request);

        verify(pessoaAutenticadaService).get();
        verify(buscarPublicacaoService).findById(1L);
        verify(comentarioRepository).save(comentarioCaptor.capture());

        Comentario response = comentarioCaptor.getValue();

        assertEquals(response.getTexto(), comentario.getTexto());
        assertEquals(response.getPessoa(), comentario.getPessoa());
        assertEquals(response.getPublicacao(), comentario.getPublicacao());
    }
}