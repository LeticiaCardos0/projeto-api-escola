package com.superdev.projetoapiescola.exceptions;

import com.superdev.projetoapiescola.dto.ErroResposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*
 * Converter exceceos em respostas {codigo,mensagem,detalhes},
 * para o front tratar esses erros de uma forma so
 * */
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ErroAplicacao.class)
    public ResponseEntity<ErroResposta> tratarErroAplicacao(ErroAplicacao exception){
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ErroResposta(exception.getCodigo(), exception.getMessage(), List.of()));
    }
}