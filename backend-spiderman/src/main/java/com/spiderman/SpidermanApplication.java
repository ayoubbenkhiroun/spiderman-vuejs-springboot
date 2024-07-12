package com.spiderman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot.
 * Cette classe démarre l'application en utilisant Spring Boot.
 */
@SpringBootApplication
public class SpidermanApplication {

    /**
     * Point d'entrée de l'application.
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(SpidermanApplication.class, args);
    }
}
