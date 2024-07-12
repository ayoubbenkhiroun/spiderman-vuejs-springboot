<template>
    <div class="welcome-page">
      <h2>Bienvenue dans Spider vs Bombes!</h2>
      <p>
        Spider vs Bombes est un jeu où vous devez naviguer Spiderman à travers un bâtiment pour trouver et désamorcer des bombes. 
        Vous devez respecter les règles suivantes :
      </p>
      <ul>
        <li>Le bâtiment a une largeur et une hauteur spécifiques.</li>
        <li>Vous commencez à une position donnée.</li>
        <li>Vous devez trouver la bombe en un nombre limité de sauts.</li>
      </ul>
      <p>Cliquez sur le bouton ci-dessous pour commencer à jouer!</p>
      <button @click="startGame" class="btn btn-primary">Commencer le jeu</button>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      startGame() {
        this.$router.push('/game-initialization'); // Redirection vers la page d'initialisation du jeu
      }
    }
  };
  </script>
  
  <style scoped>
  .welcome-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  h2 {
    margin-bottom: 20px;
    color: #333;
  }
  
  p {
    margin-bottom: 15px;
    font-size: 16px;
    line-height: 1.5;
  }
  
  ul {
    text-align: left;
    margin-left: 30px;
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
    transition: background-color 0.3s;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  