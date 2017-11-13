package br.com.prova.campanha.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Esta classe é responsável por iniciar a aplicação e carregar o SpringBoot
 * 
 * @author Fellipe Oliveira
 *
 */
@SpringBootApplication
@EnableCaching
public class BootApplication {

    public static void main(String... args) {
        SpringApplication.run(BootApplication.class, args);
    }
        
}
