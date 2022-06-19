package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
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
class BuscarAmizadeServiceTest {

    @InjectMocks
    private BuscarAmizadeService buscarAmizadeService;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    @DisplayName("deve buscar amizade")
    public void deveBuscarAmizade() {

        Amizade amizade = Amizade.builder().id(13L).build();

        when(amizadeRepository.findById(13L)).thenReturn(Optional.ofNullable(amizade));

        Amizade result = buscarAmizadeService.findById(13L);

        verify(amizadeRepository).findById(13L);

        assertEquals(result.getId(), 13L);
    }
}