# Simple Village Game

A simple village-building game implemented as a RESTful API using Spring Boot and MongoDB.

## Overview

Simple Village Game is a backend application that simulates a village-building strategy game. Players can create villages, gather resources, attack other players' villages, and trade resources.

## Features

- **Player Management**: Create and manage player accounts
- **Village Management**: Build and develop villages with resources (wood, stone, food)
- **Combat System**: Attack other players' villages and steal resources
- **Trading System**: Trade resources with other players
- **Battle Logs**: Track all game activities

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- MongoDB
- Maven

## Prerequisites

Before running the application, make sure you have:

1. Java 17 or higher installed
2. Maven installed
3. MongoDB running on localhost:27017
   - If you don't have MongoDB installed, you can run it using Docker:
     ```
     docker run -d -p 27017:27017 --name mongodb mongo:latest
     ```

## Running the Application

1. Clone this repository
2. Navigate to the project root directory
3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
4. The application will start on port 8080
5. You should see a message "🏰 Prosta Gra Osadnicza uruchomiona!" in the console

## Testing the Application

There are two ways to test the application:

### 1. Using the Test Script

We've provided a shell script that automatically tests all the core functionality of the application:

1. Make the script executable:
   ```
   chmod +x test_app.sh
   ```

2. Run the script:
   ```
   ./test_app.sh
   ```

The script will create test players and villages, perform attacks, create trades, and display the results.

### 2. Manual Testing

For more detailed testing, refer to the [Testing Guide](testing_guide.md) which provides comprehensive instructions for testing all API endpoints.

## API Endpoints

The application exposes the following main API endpoints:

- `/api/players` - Player management
- `/api/villages` - Village management
- `/api/attacks` - Combat system
- `/api/trades` - Trading system
- `/api/logs` - Battle logs

For detailed information about each endpoint, refer to the [Testing Guide](testing_guide.md).

## Project Structure

- `src/main/java/com/game/controller` - REST controllers
- `src/main/java/com/game/model` - Data models
- `src/main/java/com/game/repository` - MongoDB repositories
- `src/main/java/com/game/service` - Business logic services
- `src/main/resources` - Application configuration

## License

This project is open source and available under the [MIT License](LICENSE).