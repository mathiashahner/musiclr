package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Curtida;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.CurtidaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescurtirPublicacaoServiceTest {

    @InjectMocks
    private DescurtirPublicacaoService descurtirPublicacaoService;

    @Mock
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Test
    @DisplayName("deve comentar publicacao")
    public void deveComentarPublicacao() {

        Pessoa pessoa = Pessoa.builder().id(13L).build();
        Publicacao publicacao = Publicacao.builder().id(1L).build();
        Curtida curtida = Curtida.builder().pessoa(pessoa).publicacao(publicacao).build();

        when(pessoaAutenticadaService.get()).thenReturn(pessoa);
        when(buscarPublicacaoService.findById(1L)).thenReturn(publicacao);

        when(curtidaRepository.findByPublicacaoIdAndPessoaId(
                1L, pessoa.getId())).thenReturn(ofNullable(curtida));

        descurtirPublicacaoService.descurtir(1L);

        verify(pessoaAutenticadaService).get();
        verify(buscarPublicacaoService).findById(1L);
        verify(curtidaRepository).findByPublicacaoIdAndPessoaId(1L, pessoa.getId());
        verify(curtidaRepository).delete(curtida);
        verify(curtidaRepository).countByPublicacao(publicacao);
    }
}