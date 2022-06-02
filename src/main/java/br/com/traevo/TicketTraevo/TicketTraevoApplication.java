package br.com.traevo.TicketTraevo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TicketTraevoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketTraevoApplication.class, args);
    }

}
