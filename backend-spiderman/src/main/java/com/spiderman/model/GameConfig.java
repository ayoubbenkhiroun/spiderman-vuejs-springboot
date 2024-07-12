package com.spiderman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle représentant la configuration du jeu.
 * Cette classe contient les dimensions de la grille de jeu, le nombre de sauts possibles,
 * ainsi que les coordonnées de départ.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameConfig {
    /** La largeur de la grille de jeu */
    private int width;

    /** La hauteur de la grille de jeu */
    private int height;

    /** La position de départ en abscisse (X) */
    private int startX;

    /** La position de départ en ordonnée (Y) */
    private int startY;
}
