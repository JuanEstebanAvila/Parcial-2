package co.edu.udistrital.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Clase principal encargada de iniciar el aplicativo 
/**Aqui toco meterle scanBasePackages porque el @SpringBootApplication 
* solo escaneaba el paquete donde estaba y sus subpaquetes porque como estaba en 
* ...backend.application, no ve ...backend.controller, ...backend.service, etc.
* entonces toco meterle eso para decirle 
* a Spring que escanee todo el paquete backend y todos sus subpaquetes 
* 
* @author Grupo Programación Avanzada
* @version 1.1
* */
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication(scanBasePackages = "co.edu.udistrital.backend")
@EnableJpaRepositories(basePackages = "co.edu.udistrital.backend.repository")
@EntityScan(basePackages = "co.edu.udistrital.backend.model")
public class TriatlonBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriatlonBackendApplication.class, args);
    }
}
