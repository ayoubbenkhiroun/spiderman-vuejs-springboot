# Utilise une image de base contenant Node.js pour construire l'application
FROM node:14-alpine as build

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie les fichiers nécessaires et installe les dépendances
COPY package*.json ./
RUN npm install

# Copie les autres fichiers frontend nécessaires
COPY . .

# Construit l'application frontend
RUN npm run build

# Utilise une image légère de production pour servir l'application
FROM nginx:alpine

# Copie les fichiers buildés du frontend Vue.js vers le conteneur nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expose le port 80 pour le serveur HTTP de nginx
EXPOSE 80

# Commande pour démarrer nginx lorsque le conteneur démarre
CMD ["nginx", "-g", "daemon off;"]
