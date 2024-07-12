package com.spiderman.service.impl;

import com.spiderman.model.Position;
import com.spiderman.service.DirectionProviderService;
import com.spiderman.service.GameInitializerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implémentation du service qui fournit la direction vers la bombe en fonction de la position actuelle.
 */
@Service
public class DirectionProviderServiceImpl implements DirectionProviderService {
    private static final Logger logger = LoggerFactory.getLogger(DirectionProviderServiceImpl.class);

    private final GameInitializerService gameInitializer;

    /**
     * Constructeur pour injecter le service d'initialisation du jeu.
     *
     * @param gameInitializer le service d'initialisation du jeu
     */
    public DirectionProviderServiceImpl(GameInitializerService gameInitializer) {
        this.gameInitializer = gameInitializer;
    }

    /**
     * Récupère la direction vers la bombe à partir de la position actuelle.
     *
     * @param currentPosition la position actuelle du joueur
     * @return la direction vers la bombe (U pour haut, D pour bas, R pour droite, L pour gauche)
     *         ou un message indiquant que le joueur est déjà à la bombe
     * @throws IllegalArgumentException si la position actuelle ou la position de la bombe est null
     */
    @Override
    public String getDirection(Position currentPosition) {
        if (currentPosition == null) {
            logger.error("Current position is null");
            throw new IllegalArgumentException("Current position cannot be null");
        }

        Position bombPosition = gameInitializer.getBombPosition();
        if (bombPosition == null) {
            logger.error("Bomb position is null");
            throw new IllegalStateException("Bomb position cannot be null");
        }

        int dx = bombPosition.getX() - currentPosition.getX();
        int dy = bombPosition.getY() - currentPosition.getY();

        logger.debug("Current Position: ({}, {})", currentPosition.getX(), currentPosition.getY());
        logger.debug("Bomb Position: ({}, {})", bombPosition.getX(), bombPosition.getY());
        logger.debug("dx: ({})", dx);
        logger.debug("dy: ({})", dy);

        // Vérifie si le joueur est déjà à la position de la bombe
        if (dx == 0 && dy == 0) return "You have reached the bomb!"; // Spiderman is already at the bomb

        // Construit la direction en fonction de la différence de coordonnées
        StringBuilder direction = new StringBuilder();
        if (dy < 0) direction.append("U");
        if (dy > 0) direction.append("D");
        if (dx > 0) direction.append("R");
        if (dx < 0) direction.append("L");

        logger.debug("Direction: {}", direction);
        return direction.toString();
    }
}
