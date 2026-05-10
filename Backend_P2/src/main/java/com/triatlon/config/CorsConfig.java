package com.triatlon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Configuración de políticas CORS.
 * Permite la comunicación entre el proyecto FrontEnd (HTML externo)
 * y el BackEnd (Spring Boot), que corren en puertos distintos.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Habilita CORS para todos los endpoints del backend,
     * aceptando peticiones desde cualquier origen (frontend externo).
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    /**
     * Configura el servidor para servir archivos estáticos de la carpeta uploads.
     * Permite acceder a las fotos de atletas desde el frontend.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fotos/**")
                .addResourceLocations("file:uploads/fotos/");
    }
}
