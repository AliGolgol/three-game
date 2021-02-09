## Goal:

The Goal is to implement a game with two independent units – the players – communicating with each other using an API.

##### Description

When a player starts, it insects a random (whole) number and sends it to the second player as an approach of starting the game. 
The receiving player can now always choose between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. 
The resulting whole number is then sent back to the original sender.

#### Solution 
Using rich domain model instead of anemic domain model, hence data and behaviour sit together. There are two aggregates which are Player and Game Round. I used circle array to manage Player's turn and Memento pattern to save and restore input and out of each move.

#### Config
The main and test project has its own application.properties so you can config each base on your criteria
