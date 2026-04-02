#!/bin/bash

echo "Building project..."
mvn clean package

echo "Running Minesweeper..."
java -jar target/MineSweeper-1.0-1.jar