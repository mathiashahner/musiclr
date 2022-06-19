package br.com.cwi.crescer.musiclr.validator;

import br.com.cwi.crescer.musiclr.security.model.Usuario;
import br.com.cwi.crescer.musiclr.security.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailUnicoValidatorTest {

    @InjectMocks
    private EmailUnicoValidator emailUnicoValidator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("deve retornar erro se email ja existe")
    public void deveRetornarErroSeEmailJaExiste() {

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@cwi");

        when(usuarioRepository.existsByEmail(usuario.getEmail()))
                .thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            emailUnicoValidator.validar(usuario.getEmail());
        });

        assertEquals("Este e-mail já está cadastrado.", exception.getReason());
    }
}