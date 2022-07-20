package br.com.traevo.TicketTraevo.controller;

import br.com.traevo.TicketTraevo.exception.Errors.ApiErros;
import br.com.traevo.TicketTraevo.exception.PedidoNaoEncontradoException;
import br.com.traevo.TicketTraevo.exception.RegrasDeNegociosException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegrasDeNegociosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegrasDeNegociosException ex) {
        String msg = ex.getMessage();
        return new ApiErros(msg);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return new ApiErros(erros);
    }
}
