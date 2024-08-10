package dev.thesarfo.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service APIs",
                description = "Employee Service APIs Documentations",
                version = "v1.0",
                contact = @Contact(
                        name = "Ernest",
                        email = "thesarfo@gmail.com",
                        url = "https://github.com/thesarfo"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/thesarfo"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Employee-Service Doc",
                url = "https://github.com/thesarfo"
        )
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

}
