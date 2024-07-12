package com.spiderman.service;

import com.spiderman.model.Position;

/**
 * Interface représentant un service fournissant la direction basée sur une position donnée.
 */
public interface DirectionProviderService {

    /**
     * Récupère la direction à partir de la position actuelle spécifiée.
     *
     * @param currentPosition la position actuelle du jeu
     * @return la direction calculée en fonction de la position actuelle
     */
    String getDirection(Position currentPosition);
}
