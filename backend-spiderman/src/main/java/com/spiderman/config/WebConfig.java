package com.spiderman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration de la classe pour définir les paramètres CORS.
 * Cette configuration permet de spécifier quelles origines, méthodes, et en-têtes
 * sont autorisés pour les requêtes CORS dans l'application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure les paramètres CORS pour l'application.
     *
     * @param registry l'objet CorsRegistry utilisé pour ajouter des mappings CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autoriser toutes les URL
                .allowedOrigins("http://192.168.1.6:8081",
                        "http://localhost:8081",
                        "http://localhost:8085") // URL de front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
