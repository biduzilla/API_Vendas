package br.com.traevo.TicketTraevo;

import br.com.traevo.TicketTraevo.domain.entity.Cliente;
import br.com.traevo.TicketTraevo.domain.entity.Pedido;
import br.com.traevo.TicketTraevo.repository.ClientesRepository;
import br.com.traevo.TicketTraevo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RestController
public class TicketTraevoApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesRepository clientes,
            @Autowired PedidosRepository pedidos) {
        return args -> {
            System.out.println("Salvando");
            Cliente toddy = new Cliente("Toddy");
            clientes.save(toddy);

            Pedido p = new Pedido();
            p.setCliente(toddy);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            Cliente cliente = clientes.findClienteFetchPedidos(toddy.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

//            List<Cliente> todosClients = clientes.findAll();
//            todosClients.forEach(System.out::println);
//
//            System.out.println("Atualizando");
//            todosClients.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientes.save(c);
//            });
//
//            todosClients.forEach(System.out::println);
//
//            System.out.println("buscando");
//            clientes.findByNomeLike("Luc").forEach(System.out::println);
//
//            System.out.println("deletando");
//            clientes.deleteAll(clientes.findAll());
//
//            todosClients = clientes.findAll();
//            todosClients.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(TicketTraevoApplication.class, args);
    }

}
