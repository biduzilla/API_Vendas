package br.com.traevo.TicketTraevo.controller;

import br.com.traevo.TicketTraevo.repository.ProdutosRepository;
import br.com.traevo.TicketTraevo.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedidos/")
public class PedidoController {
     private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
}
