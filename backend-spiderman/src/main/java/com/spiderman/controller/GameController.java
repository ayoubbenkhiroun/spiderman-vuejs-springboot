package com.spiderman.controller;

import com.spiderman.model.GameConfig;
import com.spiderman.model.MoveRequest;
import com.spiderman.model.Position;
import com.spiderman.service.DirectionProviderService;
import com.spiderman.service.GameInitializerService;
import com.spiderman.service.GameMoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Le contrôleur REST pour gérer les opérations de jeu telles que l'initialisation du jeu,
 * la fourniture de directions et le déplacement.
 */
@RestController
@RequestMapping("/api/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private final GameInitializerService gameInitializerService;
    private final DirectionProviderService directionProviderService;
    private final GameMoverService gameMoverService;
    private Position currentPosition;

    /**
     * Constructeur pour injecter les services nécessaires.
     *
     * @param gameInitializerService service pour initialiser le jeu
     * @param directionProviderService service pour fournir des directions
     * @param gameMoverService service pour gérer les mouvements du jeu
     */
    @Autowired
    public GameController(GameInitializerService gameInitializerService,
                          DirectionProviderService directionProviderService,
                          GameMoverService gameMoverService) {
        this.gameInitializerService = gameInitializerService;
        this.directionProviderService = directionProviderService;
        this.gameMoverService = gameMoverService;
    }

    /**
     * Initialise le jeu avec la configuration fournie.
     *
     * @param config la configuration du jeu
     * @return la configuration initialisée du jeu
     * @throws IllegalArgumentException si la configuration fournie est invalide
     */
    @PostMapping("/initialize")
    public GameConfig initializeGame(@RequestBody GameConfig config) {
        try {
            gameInitializerService.initializeGame(config);
            currentPosition = new Position(config.getStartX(), config.getStartY());
            logger.info("Game initialized. Current Position: ({}, {})", currentPosition.getX(), currentPosition.getY());
            return gameInitializerService.getInitializeParam();
        } catch (IllegalArgumentException ex) {
            logger.error("Error initializing game with config: {}", config, ex);
            throw ex;
        }
    }

    /**
     * Obtient les paramètres d'initialisation du jeu.
     *
     * @return les paramètres d'initialisation du jeu
     */
    @GetMapping("/initializeParam")
    public GameConfig getInitializeParam() {
        return gameInitializerService.getInitializeParam();
    }

    /**
     * Obtient la position de la bombe.
     *
     * @return la position actuelle de la bombe
     */
    @GetMapping("/getBombPosition")
    public Position getBombPosition() {
        try {
            Position position = gameInitializerService.getBombPosition();
            logger.info("Bomb Position: ({}, {})", position.getX(), position.getY());
            return position;
        } catch (Exception ex) {
            logger.error("Error retrieving bomb position", ex);
            throw ex;
        }
    }

    /**
     * Fournit la direction de la bombe par rapport à la position actuelle.
     *
     * @return la direction de la bombe
     */
    @GetMapping("/direction")
    public String getDirection() {
        try {
            String direction = directionProviderService.getDirection(currentPosition);
            logger.info("Current Position: ({}, {}), Bomb Direction: {}", currentPosition.getX(), currentPosition.getY(), direction);
            return direction;
        } catch (Exception ex) {
            logger.error("Error providing direction", ex);
            throw ex;
        }
    }

    /**
     * Déplace la position actuelle selon la requête de déplacement.
     *
     * @param moveRequest la requête de déplacement
     * @return la nouvelle position après le déplacement
     * @throws IllegalArgumentException si la requête de déplacement est invalide
     */
    @PostMapping("/move")
    public Position move(@RequestBody MoveRequest moveRequest) {
        try {
            logger.info("Move Request Received: {}", moveRequest.getDirection());
            currentPosition = gameMoverService.getNextPosition(currentPosition, moveRequest);
            logger.info("New Current Position: ({}, {})", currentPosition.getX(), currentPosition.getY());
            return currentPosition;
        } catch (IllegalArgumentException ex) {
            logger.error("Error processing move request: {}", moveRequest, ex);
            throw ex;
        }
    }

    /**
     * Calcule le nombre minimal de mouvements nécessaires pour atteindre la bombe
     * à partir de la position actuelle en utilisant la recherche dichotomique (recherche binaire).
     *
     * @return le nombre minimal de mouvements nécessaires pour atteindre la bombe
     */
    @GetMapping("/calculateMinimumMoves")
    public int calculateMinimumMoves() {
        try {
            int movesToBomb = gameInitializerService.calculateMinimumMoves(currentPosition);
            return movesToBomb;
        } catch (Exception ex) {
            logger.error("Error calculating minimum moves", ex);
            throw ex;
        }
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
}
