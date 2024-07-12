package com.spiderman.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test pour Position.
 */
public class PositionTest {

    /**
     * Teste le constructeur de Position.
     */
    @Test
    void testPositionConstructor() {
        // Création d'une instance de Position avec le constructeur
        Position position = new Position(5, 10);

        // Vérification que les coordonnées ont été correctement initialisées
        assertThat(position.getX()).isEqualTo(5);
        assertThat(position.getY()).isEqualTo(10);
    }

    /**
     * Teste les setters de Position.
     */
    @Test
    void testPositionSetters() {
        // Création d'une instance de Position
        Position position = new Position();

        // Utilisation des setters pour assigner de nouvelles valeurs aux coordonnées
        position.setX(3);
        position.setY(7);

        // Vérification que les coordonnées ont été correctement modifiées
        assertThat(position.getX()).isEqualTo(3);
        assertThat(position.getY()).isEqualTo(7);
    }
}
