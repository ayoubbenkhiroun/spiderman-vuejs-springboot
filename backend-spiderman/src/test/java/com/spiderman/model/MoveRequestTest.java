package com.spiderman.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test pour MoveRequest.
 */
public class MoveRequestTest {

    /**
     * Teste le constructeur de MoveRequest.
     */
    @Test
    void testMoveRequestConstructor() {
        // Création d'une instance de MoveRequest avec le constructeur
        MoveRequest moveRequest = new MoveRequest("R");

        // Vérification que la direction a été correctement initialisée
        assertThat(moveRequest.getDirection()).isEqualTo("R");
    }

    /**
     * Teste les setters de MoveRequest.
     */
    @Test
    void testMoveRequestSetters() {
        // Création d'une instance de MoveRequest
        MoveRequest moveRequest = new MoveRequest();

        // Utilisation du setter pour assigner une nouvelle direction
        moveRequest.setDirection("L");

        // Vérification que la direction a été correctement modifiée
        assertThat(moveRequest.getDirection()).isEqualTo("L");
    }
}
