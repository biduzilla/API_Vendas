package br.com.traevo.TicketTraevo.service;

import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.dto.InformacoesPedidoDto;
import br.com.traevo.TicketTraevo.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvarPedido(PedidoDTO dto);

    InformacoesPedidoDto obterPedidoCompleto(Integer id);
}
