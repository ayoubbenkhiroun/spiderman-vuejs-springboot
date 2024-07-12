package com.spiderman.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test pour GameConfig.
 */
public class GameConfigTest {

    /**
     * Teste le constructeur de GameConfig.
     */
    @Test
    void testGameConfigConstructor() {
        // Création d'une instance de GameConfig
        GameConfig gameConfig = new GameConfig(10, 8, 2, 3);

        // Vérification des valeurs assignées via le constructeur
        assertThat(gameConfig.getWidth()).isEqualTo(10);
        assertThat(gameConfig.getHeight()).isEqualTo(8);
        assertThat(gameConfig.getStartX()).isEqualTo(2);
        assertThat(gameConfig.getStartY()).isEqualTo(3);
    }

    /**
     * Teste les setters de GameConfig.
     */
    @Test
    void testGameConfigSetters() {
        // Création d'une instance de GameConfig
        GameConfig gameConfig = new GameConfig();

        // Utilisation des setters pour assigner les valeurs
        gameConfig.setWidth(12);
        gameConfig.setHeight(9);
        gameConfig.setStartX(3);
        gameConfig.setStartY(4);

        // Vérification des valeurs après utilisation des setters
        assertThat(gameConfig.getWidth()).isEqualTo(12);
        assertThat(gameConfig.getHeight()).isEqualTo(9);
        assertThat(gameConfig.getStartX()).isEqualTo(3);
        assertThat(gameConfig.getStartY()).isEqualTo(4);
    }
}
