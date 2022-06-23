package br.com.traevo.TicketTraevo.service.impl;

import br.com.traevo.TicketTraevo.domain.entity.Cliente;
import br.com.traevo.TicketTraevo.domain.entity.ItemPedido;
import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.domain.entity.Produto;
import br.com.traevo.TicketTraevo.domain.enums.StatusPedido;
import br.com.traevo.TicketTraevo.dto.InformacoesItemPedidoDto;
import br.com.traevo.TicketTraevo.dto.InformacoesPedidoDto;
import br.com.traevo.TicketTraevo.dto.ItensPedidoDTO;
import br.com.traevo.TicketTraevo.dto.PedidoDTO;
import br.com.traevo.TicketTraevo.exception.PedidoNaoEncontradoException;
import br.com.traevo.TicketTraevo.exception.RegrasDeNegociosException;
import br.com.traevo.TicketTraevo.repository.ClientesRepository;
import br.com.traevo.TicketTraevo.repository.ItensPedidosRepository;
import br.com.traevo.TicketTraevo.repository.PedidosRepository;
import br.com.traevo.TicketTraevo.repository.ProdutosRepository;
import br.com.traevo.TicketTraevo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidosRepository itensPedidosRepository;


    @Override
    @Transactional
    public Pedido salvarPedido(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegrasDeNegociosException("Código de cliente Inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidosRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);
        return pedido;
    }

    @Override
    public InformacoesPedidoDto obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id).map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido Não Encontrado"));
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id).map(pedido -> {
            pedido.setStatus(statusPedido);
            return pedidosRepository.save(pedido);
        }).orElseThrow(()-> new PedidoNaoEncontradoException());
    }

    private InformacoesPedidoDto converter(Pedido pedido) {
        return InformacoesPedidoDto.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDto> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacoesItemPedidoDto.builder()
                .descricao(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItensPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegrasDeNegociosException("Não é possível realizar pedido sem itens!");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    ()-> new RegrasDeNegociosException(
                                            "Produto Inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
