package co.edu.udistrital.backend.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase encargada de manejar las politicas CORS,
 * para que el Front pueda consumir los endpoints del Back ,
 * se declaran en una clase aparte pues así se respeta el Simple Responsability
 * con una clase encargada de la configuración del sistema 
 * @author Grupo Programación Avanzada
 * @version 1.0
 */

/**El @Configuration para decirle a Spring que esta clase contiene instrucciones sobre el funcionamiento del aplicativo,
 * sin la anotación Spring ingoraria las intrucciones 
**/
@Configuration
public class CORS implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        /**
         * Se esta definiendo con:
         * addMapping que puede acceder a todos los endpoints
         * allowedMethods que puede acceder a todos los métodos (GET, PATCH, DELETE, etc)
         * allowedOrigins que cualquiera pueda consumir los microservicios del servidor
         * allowedHeaders permite todas las cabeceras que mande el Front
         * maxAge cuanto tiempo el navegador debe "recordar" esta configuración 
         */
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*").maxAge(3600);
    }
}
