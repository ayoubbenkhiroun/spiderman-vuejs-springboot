<template>
  <!-- Conteneur principal du plateau de jeu -->
  <div class="game-board">
    <!-- Titre du jeu avec des informations -->
    <h2>Plateau de jeu Spider vs Bombes</h2>
    <b>Le bâtiment a {{ width * height }} fenêtres</b>
    <b>Vous devez trouver les bombes en {{ maxMoves }} sauts.</b>
    <b>Vous commencez en position ({{ positionX }}, {{ positionY }}).</b>
    <b>La bombe est située à la position ({{ bombPosition.x+1 }}, {{ bombPosition.y+1 }}).</b>
    <b>Vous sautez sur la fenêtre ({{ startX }}, {{ startY }}).</b>

    <!-- Tableau représentant le plateau de jeu -->
    <div class="board">
      <!-- Générer les lignes du tableau -->
      <div class="row" v-for="(row, y) in height" :key="y">
        <!-- Générer les cellules de chaque ligne -->
        <div
          class="cell"
          v-for="(cell, x) in width"
          :key="x"
          :class="['cell', getCellClass(x, y)]"
        >
          <!-- Afficher l'image correspondante selon le contenu de la cellule -->
          <img v-if="cell === 'spiderman'" src="@/assets/spiderman.png" alt="Spiderman" />
          <img v-else-if="cell === 'bomb'" src="@/assets/bombe.png" alt="Bomb" />
          <!-- Ajoutez d'autres conditions pour d'autres types de cellules si nécessaire -->
        </div>
      </div>
    </div>

    <!-- Boutons pour interagir avec le jeu -->
    <button v-if="!gameOver" @click="moveSpiderman" :disabled="gameOver">Move Spiderman</button>
    <button v-if="gameOver" @click="restartGame">Replay & Back</button>
    <button v-if="!gameOver" @click="moveSpidermanAutomatically" :disabled="gameOver">Move Spiderman Automatically</button>

    <!-- Composant de popup affiché en cas de victoire -->
    <GamePopup v-if="showPopup" :message="'Vous avez trouvé les otages. Vous pouvez désarmer les bombes à temps. Vous avez gagné !'" @close="closePopup" />
  </div>
</template>

<script>
import GamePopup from '@/components/GamePopup.vue'; // Import du composant de popup
import config from '@/config'; // Import de la configuration pour les URL

export default {
  components: {
    GamePopup
  },
  data() {
    return {
      width: 0, // Largeur du plateau de jeu
      height: 0, // Hauteur du plateau de jeu
      startX: 0, // Position de départ X de Spiderman
      startY: 0, // Position de départ Y de Spiderman
      board: [], // Tableau représentant le plateau de jeu
      bombPosition: { x: -1, y: -1 }, // Position initiale de la bombe
      autoMoveInterval: null, // Interval pour le déplacement automatique
      showPopup: false, // Indicateur pour afficher le popup de victoire
      gameOver: false, // Indicateur pour gérer la fin de jeu
      maxMoves: 0, // Nombre maximal de mouvements autorisés
      positionX: 0, // Position actuelle X de Spiderman
      positionY: 0, // Position actuelle Y de Spiderman
    };
  },
  mounted() {
    this.fetchGameBoard(); // Appel à la méthode pour charger le plateau de jeu au montage du composant
  },
  methods: {
    async fetchGameBoard() {
      try {
        // Récupérer les données de taille du tableau depuis l'API
        const response = await fetch(config.INITIALIZE_URL);
        if (!response.ok) {
          throw new Error('Failed to fetch game board');
        }
        const data = await response.json();
        this.width = data.width; // Assigner la largeur récupérée
        this.height = data.height; // Assigner la hauteur récupérée
        this.startX = data.startX; // Assigner la position de départ X de Spiderman
        this.startY = data.startY; // Assigner la position de départ Y de Spiderman
        this.board = this.createEmptyBoard(); // Créer un plateau de jeu vide
        this.positionX = data.startX; // Assigner la position actuelle X de Spiderman
        this.positionY = data.startY; // Assigner la position actuelle Y de Spiderman

        // Récupérer la position de la bombe depuis l'API
        const bombResponse = await fetch(config.BOMB_POSITION_URL);
        if (!bombResponse.ok) {
          throw new Error('Failed to fetch bomb position');
        }
        const bombData = await bombResponse.json();
        this.bombPosition = { x: bombData.x - 1, y: bombData.y - 1 }; // Ajuster pour zéro-indexé

        // Récupérer le nombre maximal de mouvements depuis l'API
        const movesResponse = await fetch(config.MOVES_URL);
        if (!movesResponse.ok) {
          throw new Error('Failed to fetch bomb position');
        }
        const movesData = await movesResponse.json();
        this.maxMoves = movesData;
      } catch (error) {
        console.error('Failed to fetch game board or bomb position:', error);
      }
    },
    getCellClass(x, y) {
      // Fonction pour déterminer la classe CSS d'une cellule
      if (x === this.startX && y === this.startY) {
        return 'cell-spiderman'; // Classe pour la position de Spiderman
      } else if (x === this.bombPosition.x + 1 && y === this.bombPosition.y + 1) {
        return 'cell-bomb'; // Classe pour la position de la bombe
      } else {
        return 'cell-inactive'; // Classe par défaut pour les autres cellules
      }
    },
    async moveSpiderman() {
      try {
        // Récupérer la direction de déplacement depuis l'API
        const directionResponse = await fetch(config.DIRECTION_URL);
        if (!directionResponse.ok) {
          throw new Error(`Failed to fetch direction: ${directionResponse.statusText}`);
        }
        const direction = await directionResponse.text(); // Ex: "R" pour droite

        // Appeler l'API de déplacement avec la direction obtenue
        const moveResponse = await fetch(config.MOVE_URL, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            direction: direction, // Envoyer la direction pour obtenir la nouvelle position
          }),
        });

        if (!moveResponse.ok) {
          throw new Error(`Failed to move Spiderman: ${moveResponse.statusText}`);
        }

        const newPosition = await moveResponse.json(); // Réponse: { "x": 5, "y": 8 }
        
        // Mettre à jour la position de Spiderman dans le jeu
        this.startX = newPosition.x; // Mettre à jour la position X de Spiderman
        this.startY = newPosition.y; // Mettre à jour la position Y de Spiderman

        // Vérifier si Spiderman a atteint la bombe
        if (this.startX === this.bombPosition.x + 1 && this.startY === this.bombPosition.y + 1) {
          console.log('Vous avez trouvé les otages. Vous pouvez désarmer les bombes à temps. Vous avez gagné !');
          this.showPopup = true; // Afficher le popup de victoire
          this.gameOver = true; // Définir la fin de jeu
          clearInterval(this.autoMoveInterval); // Arrêter le mouvement automatique s'il est en cours
          this.autoMoveInterval = null; // Réinitialiser l'intervalle
        }
      } catch (error) {
        console.error('Failed to move Spiderman:', error);
      }
    },
    moveSpidermanAutomatically() {
      if (this.autoMoveInterval === null) {
        // Démarrer le mouvement automatique avec un intervalle de 0.15 secondes
        this.autoMoveInterval = setInterval(async () => {
          try {
            await this.moveSpiderman(); // Appeler la méthode de déplacement manuel
          } catch (error) {
            console.error('Failed to move Spiderman automatically:', error);
            clearInterval(this.autoMoveInterval); // Arrêter l'intervalle en cas d'erreur
            this.autoMoveInterval = null; // Réinitialiser l'intervalle
          }
        }, 150);
      } else {
        // Si déjà en mouvement automatique, arrêter le mouvement
        clearInterval(this.autoMoveInterval);
        this.autoMoveInterval = null;
      }
    },
    createEmptyBoard() {
      // Fonction pour créer un plateau de jeu vide
      const board = [];
      for (let y = 0; y < this.height; y++) {
        const row = [];
        for (let x = 0; x < this.width; x++) {
          row.push(0); // Ajouter une cellule vide (ou autre initialisation)
        }
        board.push(row);
      }
      return board;
    },
    closePopup() {
      // Méthode pour fermer le popup de victoire
      this.showPopup = false;
    },
    restartGame() {
      // Méthode pour redémarrer le jeu
      this.$router.push('/game-initialization'); // Rediriger vers la page d'initialisation du jeu
      this.fetchGameBoard(); // Recharger les données du plateau de jeu
      this.showPopup = false; // Cacher le popup de victoire
      this.gameOver = false; // Réinitialiser l'état de fin de jeu
    }
  }
};
</script>

  
  <style scoped>
  .game-board {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
  }
  
  .board {
    display: grid;
    gap: 5px;
    background-color: #f0f0f0;
    padding: 10px;
    border-radius: 5px;
  }
  
  .row {
    display: flex;
    gap: 5px;
  }
  
  .cell {
    width: 50px;
    height: 50px;
    border: 1px solid #ccc;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    font-size: 14px;
    font-weight: bold;
  }
  
  .cell img {
    max-width: 100%;
    max-height: 100%;
  }
  
  .cell-active {
    background-color: #007bff;
    color: #fff;
  }
  
  .cell-inactive {
    background-color: #f9f9f9;
  }
  
  .cell-bomb {
    background-image: url("@/assets/bombe.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    color: transparent; /* Cacher le texte */
  }
  
  .cell-spiderman {
    background-image: url("@/assets/spiderman.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    color: transparent; /* Cacher le texte */
  }
  
  button {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
  }
  </style>
  