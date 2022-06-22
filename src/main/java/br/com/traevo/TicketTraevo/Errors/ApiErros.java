package br.com.traevo.TicketTraevo.Errors;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ApiErros {

    @Getter
    private List<String> errors;

    public ApiErros(String mensagemErro){
        this.errors = List.of(mensagemErro);
    }
}
