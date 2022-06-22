package br.com.traevo.TicketTraevo.service;

import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvarPedido(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
