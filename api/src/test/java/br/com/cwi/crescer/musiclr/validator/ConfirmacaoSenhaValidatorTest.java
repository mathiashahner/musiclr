package br.com.cwi.crescer.musiclr.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfirmacaoSenhaValidatorTest {

    @InjectMocks
    private ConfirmacaoSenhaValidator confirmacaoSenhaValidator;

    @Test
    @DisplayName("deve retornar erro se senha e confirmacao nao sao iguais")
    public void deveRetornarErroSeSenhaEConfirmacaoNaoSaoIguais() {

        String senha = "123";
        String confrimacaoSenha = "1234";

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            confirmacaoSenhaValidator.validar(senha, confrimacaoSenha);
        });

        assertEquals("Confirmação da senha é inválida.", exception.getReason());
    }
}