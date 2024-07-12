package com.spiderman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle représentant une requête de déplacement.
 * Cette classe contient la direction du déplacement.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveRequest {
    /** La direction du déplacement (par exemple, "NORD", "SUD", "EST", "OUEST") */
    private String direction;
}
