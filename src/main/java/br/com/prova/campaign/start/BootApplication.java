package br.com.prova.campaign.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Esta classe é responsável por iniciar a aplicação e carregar o SpringBoot
 * 
 * @author Fellipe Oliveira
 *
 */
@SpringBootApplication
@EnableJpaRepositories("br.com.prova.*")
@EnableSwagger2
@EnableCaching
@Configuration
@ComponentScan("br.com.prova.*")
@EntityScan("br.com.prova.*") 
public class BootApplication {
	private static final String API_INFO_LICENSE = "prova-netbase";
    private static final String API_INFO_CONTACT = "Fellipe Oliveira - fellipe_so@hotmail.com";
    private static final String API_INFO_TERMS_URL = "teste";
    private static final String API_INFO_DESC = "Documentação das APIs prova netbase";
    private static final String API_INFO_TITLE = "netbase APIs";
    private static final String PATH_V1 = "/v.*";
    private static final String GROUP_NAME = "prova";

    public static void main(String... args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()                                  
                .apis(RequestHandlerSelectors.basePackage("br.com.prova.api"))              
                .paths(PathSelectors.regex("/campaign.*"))                          
                .build()
                .apiInfo(apiInfo());
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(API_INFO_TITLE).description(API_INFO_DESC)
                .termsOfServiceUrl(API_INFO_TERMS_URL).contact(API_INFO_CONTACT)
                .license(API_INFO_LICENSE).build();
    }
        
}
