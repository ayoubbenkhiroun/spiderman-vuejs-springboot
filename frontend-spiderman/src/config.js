// src/config.js
const BASE_URL = process.env.VUE_APP_BASE_URL || 'http://localhost:8080';

export default {
  BASE_URL: BASE_URL,
  POSTINITIALIZE_URL: `${BASE_URL}/api/game/initialize`,
  INITIALIZE_URL: `${BASE_URL}/api/game/initializeParam`,
  BOMB_POSITION_URL: `${BASE_URL}/api/game/getBombPosition`,
  MOVES_URL: `${BASE_URL}/api/game/calculateMinimumMoves`,
  DIRECTION_URL: `${BASE_URL}/api/game/direction`,
  MOVE_URL: `${BASE_URL}/api/game/move`
};
