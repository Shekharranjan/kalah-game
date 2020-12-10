FROM openjdk:8

WORKDIR /kalah-game-app

COPY ./target/kalah-game-1.0.0-SNAPSHOT.jar ./kalah-game-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/kalah-game-app/kalah-game-1.0.0.jar"]

EXPOSE 8080
