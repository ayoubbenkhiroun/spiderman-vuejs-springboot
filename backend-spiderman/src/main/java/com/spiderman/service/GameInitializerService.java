package com.spiderman.service;

import com.spiderman.model.GameConfig;
import com.spiderman.model.Position;

/**
 * Interface pour le service d'initialisation du jeu.
 * Ce service permet d'initialiser le jeu, de récupérer les paramètres d'initialisation,
 * d'obtenir la position de la bombe et de calculer le nombre de mouvements nécessaires
 * pour atteindre la bombe à partir d'une position donnée.
 */
public interface GameInitializerService {

    /**
     * Initialise le jeu avec la configuration spécifiée.
     *
     * @param config la configuration du jeu à utiliser pour l'initialisation
     */
    void initializeGame(GameConfig config);

    /**
     * Récupère les paramètres d'initialisation du jeu.
     *
     * @return les paramètres d'initialisation actuels du jeu
     */
    GameConfig getInitializeParam();

    /**
     * Obtient la position de la bombe dans le jeu.
     *
     * @return la position de la bombe actuelle dans le jeu
     */
    Position getBombPosition();

    /**
     * Calcule le nombre minimal de mouvements nécessaires pour atteindre la position de la bombe
     * à partir d'une position de départ donnée en utilisant le principe de recherche dichotomique (recherche binaire).
     *
     * @param startPosition La position de départ de Spiderman.
     * @return Le nombre minimal de mouvements nécessaires pour atteindre la bombe.
     */
    int calculateMinimumMoves(Position startPosition);
}
