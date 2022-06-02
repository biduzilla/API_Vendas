package br.com.traevo.TicketTraevo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDTO {

    private Integer produto;
    private Integer quantidade;
}
