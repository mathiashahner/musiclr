package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.PessoaRepository;
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
class BuscarPessoaServiceTest {

    @InjectMocks
    private BuscarPessoaService buscarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("deve buscar amizade")
    public void deveBuscarAmizade() {

        Pessoa pessoa = Pessoa.builder().id(13L).build();

        when(pessoaRepository.findById(13L)).thenReturn(Optional.ofNullable(pessoa));

        Pessoa result = buscarPessoaService.findById(13L);

        verify(pessoaRepository).findById(13L);

        assertEquals(result.getId(), 13L);
    }
}