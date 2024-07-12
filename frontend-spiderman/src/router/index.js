import { createRouter, createWebHistory } from 'vue-router';
import GameBoard from '../views/GameBoard.vue';
import GameInitialization from '../views/GameInitialization.vue';
import WelcomePage from '../views/WelcomePage.vue';

const routes = [
  {
    path: '/',
    name: 'WelcomePage',
    component: WelcomePage
  },
  {
    path: '/game-board',
    name: 'GameBoard',
    component: GameBoard
  },
  {
    path: '/game-initialization',
    name: 'GameInitialization',
    component: GameInitialization
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
