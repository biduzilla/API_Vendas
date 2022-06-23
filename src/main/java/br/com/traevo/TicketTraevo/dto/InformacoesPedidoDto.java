package br.com.traevo.TicketTraevo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDto {

    private Integer codigo;
    private String nomeCliente;
    private String dataPedido;
    private BigDecimal total;
    private String status;
    private List<InformacoesItemPedidoDto> items;
}
