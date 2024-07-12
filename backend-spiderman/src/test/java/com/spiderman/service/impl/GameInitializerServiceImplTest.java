package com.spiderman.service.impl;

import com.spiderman.model.GameConfig;
import com.spiderman.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Classe de test pour GameInitializerServiceImpl.
 */
public class GameInitializerServiceImplTest {

    private GameInitializerServiceImpl gameInitializerService;
    private GameConfig gameConfig;

    /**
     * Configure les conditions initiales avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        gameInitializerService = new GameInitializerServiceImpl();
        gameConfig = Mockito.mock(GameConfig.class);

        when(gameConfig.getWidth()).thenReturn(10);
        when(gameConfig.getHeight()).thenReturn(10);
        when(gameConfig.getStartX()).thenReturn(0);
        when(gameConfig.getStartY()).thenReturn(0);
    }

    /**
     * Teste la méthode initializeGame de GameInitializerServiceImpl.
     */
    @Test
    public void testInitializeGame() {
        gameInitializerService.initializeGame(gameConfig);

        Position bombPosition = gameInitializerService.getBombPosition();
        Position startPosition = new Position(gameConfig.getStartX(), gameConfig.getStartY());
        int movesToBomb = calculateMoves(startPosition, bombPosition);

        assertNotNull(bombPosition);
        assertTrue(movesToBomb >= 2 && movesToBomb <= 100);
    }

    /**
     * Teste la méthode getInitializeParam de GameInitializerServiceImpl.
     */
    @Test
    public void testGetInitializeParam() {
        gameInitializerService.initializeGame(gameConfig);
        GameConfig initializedConfig = gameInitializerService.getInitializeParam();

        assertEquals(gameConfig, initializedConfig);
    }

    /**
     * Teste la méthode getBombPosition de GameInitializerServiceImpl.
     */
    @Test
    public void testGetBombPosition() {
        gameInitializerService.initializeGame(gameConfig);
        Position bombPosition = gameInitializerService.getBombPosition();

        assertNotNull(bombPosition);
    }

    /**
     * Teste la méthode calculateMinimumMoves de GameInitializerServiceImpl.
     */
    @Test
    public void testCalculateMinimumMoves() {
        gameInitializerService.initializeGame(gameConfig);
        Position startPosition = new Position(gameConfig.getStartX(), gameConfig.getStartY());
        int minimumMoves = gameInitializerService.calculateMinimumMoves(startPosition);

        assertTrue(minimumMoves >= 2 && minimumMoves <= 100);
    }

    /**
     * Calcule le nombre de mouvements nécessaires pour aller d'une position de départ à une position de bombe.
     *
     * @param start la position de départ
     * @param bomb la position de la bombe
     * @return le nombre de mouvements nécessaires
     */
    private int calculateMoves(Position start, Position bomb) {
        int dx = Math.abs(bomb.getX() - start.getX());
        int dy = Math.abs(bomb.getY() - start.getY());
        return Math.max(dx, dy);
    }
}
