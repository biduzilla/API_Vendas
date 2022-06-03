package br.com.traevo.TicketTraevo.controller;

import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.dto.PedidoDTO;
import br.com.traevo.TicketTraevo.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pedidos/")
public class PedidoController {
     private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvarPedido(dto);
        return pedido.getId();
    }

}
