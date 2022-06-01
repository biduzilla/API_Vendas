package br.com.traevo.TicketTraevo.repository;

import br.com.traevo.TicketTraevo.domain.entity.Cliente;
import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
