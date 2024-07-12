package com.spiderman.service;

import com.spiderman.model.MoveRequest;
import com.spiderman.model.Position;

/**
 * Interface pour le service de déplacement du joueur dans le jeu.
 * Ce service permet de déplacer le joueur à partir de sa position actuelle
 * en fonction de la requête de déplacement reçue.
 */
public interface GameMoverService {

    /**
     * Obtient la prochaine position du joueur en fonction de sa position actuelle et de la requête de déplacement.
     *
     * @param currentPosition la position actuelle du joueur
     * @param moveRequest la requête de déplacement spécifiant la direction du mouvement
     * @return la nouvelle position du joueur après le déplacement
     */
    Position getNextPosition(Position currentPosition, MoveRequest moveRequest);
}
