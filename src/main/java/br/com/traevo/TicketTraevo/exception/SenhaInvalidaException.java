package br.com.traevo.TicketTraevo.exception;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException() {
        super("Senha Inválida");
    }
}
