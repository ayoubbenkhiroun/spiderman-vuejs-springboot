package com.spiderman.controller;

import com.spiderman.model.GameConfig;
import com.spiderman.model.MoveRequest;
import com.spiderman.model.Position;
import com.spiderman.service.DirectionProviderService;
import com.spiderman.service.GameInitializerService;
import com.spiderman.service.GameMoverService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Classe de test pour GameController.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameInitializerService gameInitializerService;

    @Mock
    private DirectionProviderService directionProviderService;

    @Mock
    private GameMoverService gameMoverService;

    /**
     * Configure les conditions initiales avant chaque test.
     */
    @Before
    public void setUp() {
        Position initialPosition = new Position(0, 0); // Position initiale pour les tests
        gameController.setCurrentPosition(initialPosition); // Définir la position initiale dans le GameController
    }

    /**
     * Teste la méthode initializeGame de GameController.
     */
    @Test
    public void testInitializeGame() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setWidth(10);
        gameConfig.setHeight(10);
        gameConfig.setStartX(0);
        gameConfig.setStartY(0);

        // Mock des comportements du service d'initialisation
        Mockito.when(gameInitializerService.getInitializeParam()).thenReturn(gameConfig);

        GameConfig result = gameController.initializeGame(gameConfig);

        // Vérification des résultats ou comportements attendus
        Assert.assertEquals(gameConfig.getWidth(), result.getWidth());
        Assert.assertEquals(gameConfig.getHeight(), result.getHeight());
        Assert.assertEquals(gameConfig.getStartX(), result.getStartX());
        Assert.assertEquals(gameConfig.getStartY(), result.getStartY());
    }

    /**
     * Teste la méthode getDirection de GameController.
     */
    @Test
    public void testGetDirection() {
        Position testPosition = new Position(3, 3); // Position arbitraire pour le test
        gameController.setCurrentPosition(testPosition); // Mise à jour de la position dans le GameController

        // Mock des comportements du service de fourniture de direction
        Mockito.when(directionProviderService.getDirection(testPosition)).thenReturn("RIGHT");

        String direction = gameController.getDirection();

        // Vérification des résultats ou comportements attendus
        Assert.assertEquals("RIGHT", direction);
    }

    /**
     * Teste la méthode move de GameController.
     */
    @Test
    public void testMove() {
        MoveRequest moveRequest = new MoveRequest();
        moveRequest.setDirection("RIGHT");

        Position currentPosition = gameController.getCurrentPosition();

        // Mock des comportements du service de mouvement
        Mockito.when(gameMoverService.getNextPosition(currentPosition, moveRequest)).thenReturn(new Position(4, 3)); // Position arbitraire après le déplacement

        Position newPosition = gameController.move(moveRequest);

        // Vérification des résultats ou comportements attendus
        Assert.assertEquals(4, newPosition.getX());
        Assert.assertEquals(3, newPosition.getY());
    }
}
