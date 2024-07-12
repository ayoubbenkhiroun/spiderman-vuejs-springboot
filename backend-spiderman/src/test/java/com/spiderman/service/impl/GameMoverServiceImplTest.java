package com.spiderman.service.impl;

import com.spiderman.model.MoveRequest;
import com.spiderman.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour GameMoverServiceImpl.
 */
public class GameMoverServiceImplTest {

    private GameMoverServiceImpl gameMoverService;

    /**
     * Configure les conditions initiales avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        gameMoverService = new GameMoverServiceImpl();
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le haut.
     */
    @Test
    public void testGetNextPosition_Up() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("U");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(5, 4), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le haut droit.
     */
    @Test
    public void testGetNextPosition_UpRight() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("UR");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(6, 4), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers la droite.
     */
    @Test
    public void testGetNextPosition_Right() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("R");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(6, 5), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le bas droit.
     */
    @Test
    public void testGetNextPosition_DownRight() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("DR");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(6, 6), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le bas.
     */
    @Test
    public void testGetNextPosition_Down() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("D");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(5, 6), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le bas gauche.
     */
    @Test
    public void testGetNextPosition_DownLeft() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("DL");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(4, 6), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers la gauche.
     */
    @Test
    public void testGetNextPosition_Left() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("L");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(4, 5), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour le déplacement vers le haut gauche.
     */
    @Test
    public void testGetNextPosition_UpLeft() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("UL");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(new Position(4, 4), newPosition);
    }

    /**
     * Teste la méthode getNextPosition pour une direction inconnue.
     */
    /*
    @Test
    public void testGetNextPosition_UnknownDirection() {
        Position currentPosition = new Position(5, 5);
        MoveRequest moveRequest = new MoveRequest("X");

        Position newPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);

        assertEquals(currentPosition, newPosition); // La position ne doit pas changer pour une direction inconnue
    }
    */

}
