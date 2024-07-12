package com.spiderman.service.impl;

import com.spiderman.model.GameConfig;
import com.spiderman.model.Position;
import com.spiderman.service.GameInitializerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Implémentation du service d'initialisation du jeu.
 * Ce service initialise le jeu avec une configuration donnée,
 * génère une position aléatoire pour la bombe et calcule le nombre de mouvements
 * nécessaires pour atteindre la bombe à partir d'une position donnée.
 */
@Service
public class GameInitializerServiceImpl implements GameInitializerService {
    private static final Logger logger = LoggerFactory.getLogger(GameInitializerServiceImpl.class);

    private GameConfig gameConfig;
    private Position bombPosition;

    /**
     * Initializes the game with the specified configuration. The bomb position is generated randomly
     * and ensured that the minimum number of moves required to reach the bomb from the start position
     * is between 2 and 100.
     *
     * @param config the game configuration containing the width, height, start position, and other settings.
     * @throws IllegalArgumentException if the provided configuration is null.
     * @throws IllegalStateException    if the bomb position cannot be generated due to invalid game configuration.
     */
    public void initializeGame(GameConfig config) {
        if (config == null) {
            logger.error("Game configuration is null");
            throw new IllegalArgumentException("Game configuration cannot be null");
        }

        // Set the game configuration
        this.gameConfig = config;

        // Create a Position object for the starting position
        Position startPosition = new Position(config.getStartX(), config.getStartY());

        // Create a Random object for generating random positions
        Random random = new Random();

        // Define the minimum and maximum number of moves to reach the bomb
        int minMoves = 2;
        int maxMoves = 100;

        // Variables to store the bomb position and the calculated number of moves to reach it
        Position bombPosition;
        int movesToBomb;

        // Loop until a valid bomb position is found such that movesToBomb is between minMoves and maxMoves
        do {
            // Generate a random position for the bomb within the game grid
            bombPosition = new Position(random.nextInt(config.getWidth()), random.nextInt(config.getHeight()));

            // Calculate the minimum number of moves required to reach the bomb from the start position
            movesToBomb = calculateMovesUsingIntervals(startPosition, bombPosition, minMoves, maxMoves);
        } while (movesToBomb < minMoves || movesToBomb > maxMoves); // Repeat if the number of moves is outside the valid range

        // Set the bomb position to the validated position
        this.bombPosition = bombPosition;

        // Log the initialized bomb position and the number of moves to reach it
        logger.info("Game initialized with bomb at position: ({}, {}), moves to bomb: {}", bombPosition.getX(), bombPosition.getY(), movesToBomb);
    }

    /**
     * Calculates the minimum number of moves required to reach the bomb position from the start position
     * using the principle of intervals. This calculation is based on the Manhattan distance principle,
     * using the maximum of the horizontal and vertical distances.
     *
     * @param startPosition the starting position.
     * @param bombPosition  the bomb position.
     * @param minMoves      the minimum number of moves allowed.
     * @param maxMoves      the maximum number of moves allowed.
     * @return the minimum number of moves required to reach the bomb within the given interval.
     */
    private int calculateMovesUsingIntervals(Position startPosition, Position bombPosition, int minMoves, int maxMoves) {
        // Calculate the absolute horizontal distance between the start position and the bomb position
        int dx = Math.abs(bombPosition.getX() - startPosition.getX());

        // Calculate the absolute vertical distance between the start position and the bomb position
        int dy = Math.abs(bombPosition.getY() - startPosition.getY());

        // Calculate the number of moves required to reach the bomb using intervals
        int moves = Math.max(dx, dy);

        // Ensure the moves are within the specified intervals
        if (moves < minMoves) {
            moves = minMoves;
        } else if (moves > maxMoves) {
            moves = maxMoves;
        }

        return moves;
    }

    /**
     * Récupère les paramètres d'initialisation du jeu.
     *
     * @return les paramètres d'initialisation actuels du jeu
     */
    @Override
    public GameConfig getInitializeParam() {
        return this.gameConfig;
    }

    /**
     * Obtient la position de la bombe dans le jeu.
     *
     * @return la position de la bombe actuelle dans le jeu
     * @throws IllegalStateException if the bomb position is not yet initialized.
     */
    @Override
    public Position getBombPosition() {
        if (bombPosition == null) {
            logger.error("Bomb position is not initialized");
            throw new IllegalStateException("Bomb position is not initialized");
        }
        return this.bombPosition;
    }

    /**
     * Calcule le nombre minimal de mouvements nécessaires pour atteindre la bombe
     * à partir de la position de départ donnée en utilisant la recherche binaire en 2D.
     *
     * @param startPosition la position de départ
     * @return le nombre minimal de mouvements nécessaires pour atteindre la bombe
     */
    @Override
    public int calculateMinimumMoves(Position startPosition) {
        int left = 0;
        int right = Math.max(this.gameConfig.getWidth(), this.gameConfig.getHeight()); // Largeur et hauteur de la grille
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReachBombInMoves(startPosition, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        logger.info("Nombre minimal de mouvements nécessaires pour atteindre la bombe: {}", result);
        return result;
    }

    /**
     * Vérifie si la bombe peut être atteinte à partir de la position de départ donnée
     * en un nombre de mouvements spécifié.
     *
     * @param startPosition la position de départ
     * @param moves          le nombre de mouvements spécifiés
     * @return true si la bombe peut être atteinte en ce nombre de mouvements, false sinon
     */
    private boolean canReachBombInMoves(Position startPosition, int moves) {
        int dx = Math.abs(bombPosition.getX() - startPosition.getX());
        int dy = Math.abs(bombPosition.getY() - startPosition.getY());
        return dx <= moves && dy <= moves;
    }

}
