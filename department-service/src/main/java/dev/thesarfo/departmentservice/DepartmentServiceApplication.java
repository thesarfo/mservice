package dev.thesarfo.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Department Service APIs",
                description = "Department Service APIs Documentations",
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
                description = "Department-Service Doc",
                url = "https://github.com/thesarfo"
        )
)
@SpringBootApplication
public class DepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

}
