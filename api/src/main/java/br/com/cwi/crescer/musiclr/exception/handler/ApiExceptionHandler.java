package br.com.cwi.crescer.musiclr.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handlerResponseStatusException(ResponseStatusException ex) {

        ErroResponse erro = ErroResponse.builder()
                .mensagem(ex.getReason())
                .build();

        return new ResponseEntity(erro, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ErroResponse erro = ErroResponse.builder()
                .mensagem("Ocorreram erros de validação")
                .erros(extrairDetalhes(ex))
                .build();

        return new ResponseEntity(erro, HttpStatus.BAD_REQUEST);
    }

    private List<ErroResponse.ErroDetalheResponse> extrairDetalhes(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(e -> ErroResponse.ErroDetalheResponse.builder()
                        .campo(((FieldError) e).getField())
                        .mensagem(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
