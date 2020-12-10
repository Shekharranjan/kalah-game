# Kalah-Game Application

Kalah-Game Application is a Java Spring-boot application that runs Kalah Game following its rules. The general rules
of the game are explained on Wikipedia: https://en.wikipedia.org/wiki/Kalah.

## Game Rules

* Each of the two players has ** six pits ** in front of him/her. To the right of the six pits, each player has a larger pit, his
Kalah or house.

* At the start of the game, six stones are put in each pit.

* The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in
each of the following pits, including his own Kalah. No stones are put in the opponent's' Kalah. If the players last
stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other
player's turn.

* When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the
other players' pit) and puts them in his own Kalah.

* The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her pits keeps
them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.


## API End Points
Below are the Endpoints creates for this Kalah-Game Application - 

+ Endpoint to Create a new Kalah Game
    - /games : Endpoint to create a new Kalah Game with 6 pit and each pit having 6 stones
   
+ Endpoint to Make a Move in the Game
    - /games/{gameId}/pits/{pitId} : Endpoint to make move in the game according to Kalah Rules specified above.

## Requirements

* [Java 1.8](https://www.oracle.com/java/)
* [Maven](https://maven.apache.org/)
* [Mongo DB](https://www.mongodb.com/)


## Build

Use below maven command to install the dependencies

```bash
mvn clean install
```

## Run
Use below maven command to run the Kalah Game Application
```bash
mvn spring-boot:run
```

## Generate initial code with OpenAPI spec file 
Use below maven command to generate source code using spec file
```bash
mvn clean install -P generate-code
```

## Using Docker 
setting up mongodb 
```bash
docker pull mongo
```
```bash
docker run -d -p 27017:27017 --name mongodb mongo:latest
```
Building kalah-game image and running it on docker container 

Go to project home directory and run below command
```bash
docker build -f Dockerfile -t kalah-game . 
```
```bash
docker run -p 8080:8080 --name kalah-game --link mongodb:mongo  kalah-game                   
```

###Sample request - response
+ Create a new Kalah Game
 
```bash    
  request :   
    curl --location --request POST 'http://localhost:8080/games/' \
    --header 'Content-Type: application/json'
    
  ressponse :  
    {"id":"5fd0857e775afe712fd5fd9a","uri":"http://localhost:8080/games/5fd0857e775afe712fd5fd9a","status":{"1":6,"2":6,"3":6,"4":6,"5":6,"6":6,"7":0,"8":6,"9":6,"10":6,"11":6,"12":6,"13":6,"14":0},"playerTurn":"PLAYER_ONE","winner":null}
```
+ Make a move in Game
 
```bash    
  request :   
    curl --location --request PUT 'http://localhost:8080/games/5fd0857e775afe712fd5fd9a/pits/1' \
    --header 'Content-Type: application/json'
    
  ressponse : 
    {"id":"5fd0857e775afe712fd5fd9a","uri":"http://localhost:8080/games/5fd0857e775afe712fd5fd9a","status":{"1":0,"2":7,"3":7,"4":7,"5":7,"6":7,"7":1,"8":6,"9":6,"10":6,"11":6,"12":6,"13":6,"14":0},"playerTurn":"PLAYER_ONE","winner":null}
```

## API Spec
+ path : kalah-game.yaml
    




