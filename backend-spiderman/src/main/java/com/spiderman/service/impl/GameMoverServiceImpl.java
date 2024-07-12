package com.spiderman.service.impl;

import com.spiderman.model.MoveRequest;
import com.spiderman.model.Position;
import com.spiderman.service.GameMoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implémentation du service de déplacement du joueur dans le jeu.
 * Ce service permet de déplacer le joueur à partir de sa position actuelle
 * en fonction de la requête de déplacement spécifiée.
 */
@Service
public class GameMoverServiceImpl implements GameMoverService {
    private static final Logger logger = LoggerFactory.getLogger(GameMoverServiceImpl.class);

    /**
     * Obtient la prochaine position du joueur en fonction de sa position actuelle et de la requête de déplacement.
     *
     * @param currentPosition la position actuelle du joueur
     * @param moveRequest     la requête de déplacement spécifiant la direction du mouvement (chaîne de caractères)
     * @return la nouvelle position du joueur après le déplacement
     * @throws IllegalArgumentException si la direction spécifiée dans la requête n'est pas valide
     */
    @Override
    public Position getNextPosition(Position currentPosition, MoveRequest moveRequest) {
        if (currentPosition == null || moveRequest == null || moveRequest.getDirection() == null) {
            logger.error("Invalid arguments: currentPosition={}, moveRequest={}", currentPosition, moveRequest);
            throw new IllegalArgumentException("Current position and move request cannot be null");
        }

        // Créer une nouvelle position à partir de la position actuelle
        Position newPosition = new Position(currentPosition.getX(), currentPosition.getY());

        // Journalisation de la position actuelle et de la requête de déplacement
        logger.debug("Current Position: ({}, {})", currentPosition.getX(), currentPosition.getY());
        logger.debug("Move Request: {}", moveRequest.getDirection());

        // Déterminer la nouvelle position en fonction de la direction spécifiée dans la requête
        switch (moveRequest.getDirection()) {
            case "U":
                newPosition.setY(currentPosition.getY() - 1);
                break;
            case "UR":
                newPosition.setY(currentPosition.getY() - 1);
                newPosition.setX(currentPosition.getX() + 1);
                break;
            case "R":
                newPosition.setX(currentPosition.getX() + 1);
                break;
            case "DR":
                newPosition.setY(currentPosition.getY() + 1);
                newPosition.setX(currentPosition.getX() + 1);
                break;
            case "D":
                newPosition.setY(currentPosition.getY() + 1);
                break;
            case "DL":
                newPosition.setY(currentPosition.getY() + 1);
                newPosition.setX(currentPosition.getX() - 1);
                break;
            case "L":
                newPosition.setX(currentPosition.getX() - 1);
                break;
            case "UL":
                newPosition.setY(currentPosition.getY() - 1);
                newPosition.setX(currentPosition.getX() - 1);
                break;
            default:
                logger.warn("Unknown direction: {}", moveRequest.getDirection());
                throw new IllegalArgumentException("Unknown direction: " + moveRequest.getDirection());
        }

        // Journalisation de la nouvelle position
        logger.debug("New Position: ({}, {})", newPosition.getX(), newPosition.getY());
        return newPosition;
    }
}
