package com.spiderman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle représentant une position sur la grille de jeu.
 * Cette classe contient les coordonnées en abscisse (x) et ordonnée (y).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    /** La coordonnée en abscisse (X) */
    private int x;

    /** La coordonnée en ordonnée (Y) */
    private int y;
}
