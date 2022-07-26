package br.com.traevo.TicketTraevo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CredenciaisDto {
    private String login;
    private String senha;
}
