<template>
  <!-- Conteneur principal de la page -->
  <div class="page-container">
    <!-- Conteneur de l'initialisation du jeu -->
    <div class="game-initialization">
      <!-- Titre de l'initialisation du jeu -->
      <h2>Initialize Game</h2>
      <!-- Formulaire d'initialisation du jeu -->
      <form @submit.prevent="initializeGame">
        <!-- Champ de largeur -->
        <div class="form-group">
          <label for="width">Width:</label>
          <input
            type="number"
            id="width"
            v-model.number="width"
            class="form-control"
            min="1"
            max="10000"
            required
          />
        </div>
        <!-- Champ de hauteur -->
        <div class="form-group">
          <label for="height">Height:</label>
          <input
            type="number"
            id="height"
            v-model.number="height"
            class="form-control"
            min="1"
            max="10000"
            required
          />
        </div>
        <!-- Champ de coordonnée de départ X -->
        <div class="form-group">
          <label for="startX">Start X:</label>
          <input
            type="number"
            id="startX"
            v-model.number="startX"
            class="form-control"
            :min="0"
            :max="width - 1"
            required
          />
        </div>
        <!-- Champ de coordonnée de départ Y -->
        <div class="form-group">
          <label for="startY">Start Y:</label>
          <input
            type="number"
            id="startY"
            v-model.number="startY"
            class="form-control"
            :min="0"
            :max="height - 1"
            required
          />
        </div>
        <!-- Bouton de soumission du formulaire -->
        <button type="submit" class="btn btn-primary">Start Game</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import config from '@/config';

export default {
  // Données du composant
  data() {
    return {
      width: 10,    // Largeur par défaut
      height: 10,   // Hauteur par défaut
      startX: 0,    // Coordonnée de départ X par défaut
      startY: 0     // Coordonnée de départ Y par défaut
    };
  },
  // Méthodes du composant
  methods: {
    // Fonction d'initialisation du jeu (appelée lors de la soumission du formulaire)
    async initializeGame() {
      try {
        // Envoi d'une requête POST à l'URL de configuration avec les données actuelles
        const response = await axios.post(config.POSTINITIALIZE_URL, {
          width: this.width,
          height: this.height,
          startX: this.startX,
          startY: this.startY
        });
        // Affichage d'un message de succès dans la console
        console.log('Game initialized successfully:', response.data);
        // Redirection vers la page du tableau de jeu après une initialisation réussie
        this.$router.push('/game-board');
      } catch (error) {
        // En cas d'erreur, affichage d'un message d'erreur dans la console
        console.error('Error initializing the game:', error);
      }
    }
  }
};
</script>

<style scoped>
.page-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.game-initialization {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  width: 100%;
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  transition: border-color 0.3s;
}

.form-control:focus {
  border-color: #007bff;
  outline: none;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #0056b3;
}
</style>
