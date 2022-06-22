package br.com.traevo.TicketTraevo.controller;

import br.com.traevo.TicketTraevo.Errors.ApiErros;
import br.com.traevo.TicketTraevo.exception.RegrasDeNegociosException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegrasDeNegociosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegrasDeNegociosException ex){
        String msg = ex.getMessage();
        return new ApiErros(msg);
    }
}
