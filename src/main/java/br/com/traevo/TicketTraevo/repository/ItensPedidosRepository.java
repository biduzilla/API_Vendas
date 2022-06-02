package br.com.traevo.TicketTraevo.repository;

import br.com.traevo.TicketTraevo.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidosRepository extends JpaRepository<ItemPedido, Integer> {

}
