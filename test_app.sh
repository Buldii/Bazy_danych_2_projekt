#!/bin/bash

# Simple Village Game Test Script
# This script tests the basic functionality of the Simple Village Game application

echo "Simple Village Game Test Script"
echo "==============================="
echo

# Check if the application is running
echo "Checking if the application is running..."
if ! curl -s http://localhost:8080/api/players > /dev/null; then
  echo "Error: Application is not running. Please start the application first with 'mvn spring-boot:run'"
  exit 1
fi
echo "Application is running!"
echo

# Create players
echo "Creating players..."
PLAYER1=$(curl -s -X POST "http://localhost:8080/api/players?username=testPlayer1&email=player1@example.com")
PLAYER1_ID=$(echo $PLAYER1 | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Player 1 with ID: $PLAYER1_ID"

PLAYER2=$(curl -s -X POST "http://localhost:8080/api/players?username=testPlayer2&email=player2@example.com")
PLAYER2_ID=$(echo $PLAYER2 | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Player 2 with ID: $PLAYER2_ID"
echo

# Create villages
echo "Creating villages..."
VILLAGE1=$(curl -s -X POST "http://localhost:8080/api/villages?name=TestVillage1&playerId=$PLAYER1_ID")
VILLAGE1_ID=$(echo $VILLAGE1 | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Village 1 with ID: $VILLAGE1_ID"

VILLAGE2=$(curl -s -X POST "http://localhost:8080/api/villages?name=TestVillage2&playerId=$PLAYER2_ID")
VILLAGE2_ID=$(echo $VILLAGE2 | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Village 2 with ID: $VILLAGE2_ID"
echo

# Update player resources
echo "Updating resources for players..."
curl -s -X PUT "http://localhost:8080/api/players/$PLAYER1_ID/resources?wood=200&stone=150&food=180" > /dev/null
echo "Updated resources for Player 1"

curl -s -X PUT "http://localhost:8080/api/players/$PLAYER2_ID/resources?wood=180&stone=200&food=150" > /dev/null
echo "Updated resources for Player 2"
echo

# Increase population
echo "Increasing population for villages..."
curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE1_ID/population?amount=10" > /dev/null
echo "Increased population for Village 1"

curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE2_ID/population?amount=8" > /dev/null
echo "Increased population for Village 2"
echo

# Recruit warriors
echo "Recruiting warriors for villages..."
curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE1_ID/warriors?amount=5" > /dev/null
echo "Recruited warriors for Village 1"

curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE2_ID/warriors?amount=3" > /dev/null
echo "Recruited warriors for Village 2"
echo

# Build buildings
echo "Building structures in villages..."
curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE1_ID/buildings?amount=1&type=TOWN_HALL" > /dev/null
echo "Built Town Hall in Village 1"

curl -s -X PUT "http://localhost:8080/api/villages/$VILLAGE2_ID/buildings?amount=1&type=BARRACKS" > /dev/null
echo "Built Barracks in Village 2"
echo

# Perform an attack
echo "Performing an attack..."
ATTACK=$(curl -s -X POST "http://localhost:8080/api/attacks?attackerPlayerId=$PLAYER1_ID&defenderPlayerId=$PLAYER2_ID&attackerVillageId=$VILLAGE1_ID&defenderVillageId=$VILLAGE2_ID&attackPower=50")
ATTACK_ID=$(echo $ATTACK | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Attack with ID: $ATTACK_ID"
echo

# Check event logs
echo "Checking event logs..."
LOGS=$(curl -s "http://localhost:8080/api/logs")
LOG_COUNT=$(echo $LOGS | grep -o '"id"' | wc -l)
echo "Found $LOG_COUNT event logs"
echo

# Create a trade
echo "Creating a trade..."
TRADE=$(curl -s -X POST "http://localhost:8080/api/trades?fromPlayerId=$PLAYER1_ID&toPlayerId=$PLAYER2_ID&resourceType=wood&amount=50")
TRADE_ID=$(echo $TRADE | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created Trade with ID: $TRADE_ID"
echo

# Accept the trade
echo "Accepting the trade..."
curl -s -X PUT "http://localhost:8080/api/trades/$TRADE_ID/accept" > /dev/null
echo "Trade accepted"
echo

# Display final state
echo "Final state of players and villages:"
echo "-----------------------------------"
echo "Player 1:"
curl -s "http://localhost:8080/api/players/$PLAYER1_ID" | python3 -m json.tool
echo
echo "Player 2:"
curl -s "http://localhost:8080/api/players/$PLAYER2_ID" | python3 -m json.tool
echo
echo "Village 1:"
curl -s "http://localhost:8080/api/villages/$VILLAGE1_ID" | python3 -m json.tool
echo
echo "Village 2:"
curl -s "http://localhost:8080/api/villages/$VILLAGE2_ID" | python3 -m json.tool
echo

echo "Test completed successfully!"
echo "For more detailed testing, please refer to the testing_guide.md file."