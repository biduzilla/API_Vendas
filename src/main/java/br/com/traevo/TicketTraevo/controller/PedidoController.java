package br.com.traevo.TicketTraevo.controller;

import br.com.traevo.TicketTraevo.domain.entity.ItemPedido;
import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.domain.enums.StatusPedido;
import br.com.traevo.TicketTraevo.dto.AtualizacaoStatusPedidoDto;
import br.com.traevo.TicketTraevo.dto.InformacoesItemPedidoDto;
import br.com.traevo.TicketTraevo.dto.InformacoesPedidoDto;
import br.com.traevo.TicketTraevo.dto.PedidoDTO;
import br.com.traevo.TicketTraevo.service.PedidoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos/")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.salvarPedido(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id) {
        return pedidoService.obterPedidoCompleto(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDto statusPedidoDto){
        String novoStatus = statusPedidoDto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
