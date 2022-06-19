package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;
import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AceitarAmizadeServiceTest {

    @InjectMocks
    private AceitarAmizadeService aceitarAmizadeService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Captor
    private ArgumentCaptor<Amizade> amizadeCaptor;

    @Test
    @DisplayName("deve aceitar solicitacao de amizade")
    public void deveAceitarSolicitacaoDeAmizade() {

        Pessoa pessoa = Pessoa.builder().id(1L).nome("Mathias").build();

        Amizade amizade = Amizade.builder()
                .solicitado(pessoa)
                .situacao(SOLICITADA)
                .build();

        when(pessoaAutenticadaService.get()).thenReturn(pessoa);
        when(buscarAmizadeService.findById(13L)).thenReturn(amizade);

        aceitarAmizadeService.aceitar(13L);

        verify(pessoaAutenticadaService).get();
        verify(buscarAmizadeService).findById(13L);
        verify(amizadeRepository).save(amizadeCaptor.capture());

        assertEquals(amizadeCaptor.getValue().getSituacao(), APROVADA);
    }
}