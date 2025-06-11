# Simple Village Game - Testing Guide

This guide will help you test the Simple Village Game application and verify that it's working correctly.

## Prerequisites

Before you begin, make sure you have:

1. Java 17 or higher installed
2. Maven installed
3. MongoDB running on localhost:27017
   - If you don't have MongoDB installed, you can run it using Docker:
     ```
     docker run -d -p 27017:27017 --name mongodb mongo:latest
     ```

## Running the Application

1. Navigate to the project root directory
2. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
3. The application will start on port 8080
4. You should see a message "🏰 Prosta Gra Osadnicza uruchomiona!" in the console

## Testing the API Endpoints

You can use tools like [Postman](https://www.postman.com/), [curl](https://curl.se/), or any HTTP client to test the API endpoints.

### Player Management

1. **Create a new player**
   ```
   POST http://localhost:8080/api/players?username=player1&email=player1@example.com
   ```

2. **Get all players**
   ```
   GET http://localhost:8080/api/players
   ```

3. **Get player by ID**
   ```
   GET http://localhost:8080/api/players/{playerId}
   ```
   Replace `{playerId}` with the ID returned when creating a player.

4. **Get player by username**
   ```
   GET http://localhost:8080/api/players/username/player1
   ```

5. **Update player experience**
   ```
   PUT http://localhost:8080/api/players/{playerId}/experience?experience=100
   ```

6. **Update player resources**
   ```
   PUT http://localhost:8080/api/players/{playerId}/resources?wood=200&stone=150&food=180
   ```

### Village Management

1. **Create a new village**
   ```
   POST http://localhost:8080/api/villages?name=MyVillage&playerId={playerId}
   ```
   Replace `{playerId}` with the ID of a player you created.

2. **Get all villages**
   ```
   GET http://localhost:8080/api/villages
   ```

3. **Get village by ID**
   ```
   GET http://localhost:8080/api/villages/{villageId}
   ```

4. **Get villages by player ID**
   ```
   GET http://localhost:8080/api/villages/player/{playerId}
   ```

5. **Increase village population**
   ```
   PUT http://localhost:8080/api/villages/{villageId}/population?amount=10
   ```

6. **Recruit warriors**
   ```
   PUT http://localhost:8080/api/villages/{villageId}/warriors?amount=5
   ```

7. **Build buildings**
   ```
   PUT http://localhost:8080/api/villages/{villageId}/buildings?amount=1&type=TOWN_HALL
   ```
   Available building types: TOWN_HALL, BARRACKS

### Attack System

1. **Create a second player and village for testing attacks**
   ```
   POST http://localhost:8080/api/players?username=player2&email=player2@example.com
   POST http://localhost:8080/api/villages?name=EnemyVillage&playerId={player2Id}
   ```

2. **Perform an attack**
   ```
   POST http://localhost:8080/api/attacks?attackerPlayerId={player1Id}&defenderPlayerId={player2Id}&attackerVillageId={village1Id}&defenderVillageId={village2Id}&attackPower=50
   ```

3. **Get all attacks**
   ```
   GET http://localhost:8080/api/attacks
   ```

4. **Get attacks by player**
   ```
   GET http://localhost:8080/api/attacks/{playerId}
   ```

5. **Get attacks by village**
   ```
   GET http://localhost:8080/api/attacks/village/{villageId}
   ```

### Trading System

1. **Create a trade offer**
   ```
   POST http://localhost:8080/api/trades?fromPlayerId={player1Id}&toPlayerId={player2Id}&resourceType=wood&amount=50
   ```
   Available resource types: wood, stone, food

2. **Get all trades**
   ```
   GET http://localhost:8080/api/trades
   ```

3. **Get trades by player**
   ```
   GET http://localhost:8080/api/trades/player/{playerId}
   ```

4. **Accept a trade**
   ```
   PUT http://localhost:8080/api/trades/{tradeId}/accept
   ```

5. **Reject a trade**
   ```
   PUT http://localhost:8080/api/trades/{tradeId}/reject
   ```

### Event Logs

1. **Get all logs**
   ```
   GET http://localhost:8080/api/logs
   ```

2. **Get logs by type**
   ```
   GET http://localhost:8080/api/logs/type/{logType}
   ```
   Available log types: ATTACK, TRADE, VILLAGE_CREATE

3. **Get logs by player**
   ```
   GET http://localhost:8080/api/logs/attack/{playerId}
   ```

4. **Get logs by attack ID**
   ```
   GET http://localhost:8080/api/logs/attack/{attackId}
   ```

## Complete Test Workflow

Here's a complete workflow to test the application:

1. Create two players
2. Create a village for each player
3. Update resources for both players
4. Increase population for both villages
5. Recruit warriors for both villages
6. Build buildings in both villages
7. Perform an attack from one player to another
8. Check the event logs
9. Create a trade between the players
10. Accept the trade
11. Check the updated resources

## Troubleshooting

If you encounter any issues:

1. Make sure MongoDB is running on localhost:27017
2. Check the application logs for any error messages
3. Verify that you're using the correct IDs in your requests
4. Ensure you're using the correct HTTP methods (GET, POST, PUT) for each endpoint