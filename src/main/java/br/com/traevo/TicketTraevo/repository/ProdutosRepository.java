package br.com.traevo.TicketTraevo.repository;

import br.com.traevo.TicketTraevo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
