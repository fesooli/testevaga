package br.com.prova.campanha.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Esta classe é responsável por iniciar a aplicação e carregar o SpringBoot
 * 
 * @author Fellipe Oliveira
 *
 */
@SpringBootApplication
@EnableJpaAuditing(modifyOnCreate = true)
@EnableSwagger2
@EnableCaching
public class BootApplication {

    public static void main(String... args) {
        SpringApplication.run(BootApplication.class, args);
    }
        
}
