# Utilisez une image de base Java 11 (ou une version appropriée pour votre projet)
FROM openjdk:11-jre-slim

# Mettre en place le répertoire de travail
WORKDIR /app

# Copier le jar construit de votre application Spring Boot dans l'image Docker
COPY target/spiderman-backend-1.0.0.jar /app/spiderman-backend.jar

# Expose le port sur lequel l'application Spring Boot écoute
EXPOSE 8080

# Commande pour exécuter votre application Spring Boot quand le conteneur Docker démarre
CMD ["java", "-jar", "spiderman-backend.jar"]
