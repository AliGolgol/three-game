## Goal:

The Goal is to implement a game with two independent units – the players – communicating with each other using an API.

##### Description

When a player starts, it insects a random (whole) number and sends it to the second player as an approach of starting the game. 
The receiving player can now always choose between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. 
The resulting whole number is then sent back to the original sender.

#### Solution 
Using rich domain model instead of anemic domain model, hence data and behaviour sit together. There are two aggregates which are Player and Game Round. I used circle array to manage Player's turn and Memento pattern to save and restore output of each move.

#### Config
The main and test project has its own application.properties so you can config each base on your criteria
##### Configuration
- to configure the application set the `resources/application.properties`
- to change logging level set in the `resources/log4j.properties`
##### Requirements
- java 11
- maven 3

### Run
- To run it use this command: `sudo docker build -t game-of-three .`
- Then we run the docker image: `sudo docker run -p 8090:8080 game-of-three`
- The address of rest api is: `http://loclahost:8080`
- The address of swagger is: `http://loclahost:8080/swagger-ui.html`

### Or
- run to build app: `mvn compile` or `mvn clean`
- also run tests: `mvn test` 
- It is recommended to run the AcceptanceTest, to do that you can look in test/java/com.codeassignment.gameofthree/controller/AcceptanceTest and see the end to end test result

Thanks