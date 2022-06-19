package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPublicacaoServiceTest {

    @InjectMocks
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Test
    @DisplayName("deve buscar publicacao")
    public void deveBuscarPublicacao() {

        Publicacao publicacao = Publicacao.builder().id(13L).build();

        when(publicacaoRepository.findById(13L)).thenReturn(Optional.ofNullable(publicacao));

        Publicacao result = buscarPublicacaoService.findById(13L);

        verify(publicacaoRepository).findById(13L);

        assertEquals(result.getId(), 13L);
    }
}