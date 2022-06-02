package br.com.traevo.TicketTraevo.service.impl;

import br.com.traevo.TicketTraevo.repository.PedidosRepository;
import br.com.traevo.TicketTraevo.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }
}
