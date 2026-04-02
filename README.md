# Minesweeper Game

## Prerequisites
Before running the application, make sure you have installed:
- Java 21

## Run App (in Linux)

In the command line, execute ./run-linux.sh

## Run App (in Windows)

In the command line, execute run-windows.bat

## Run App (with Maven)

In the command line, execute ./run.sh


## Built App

mvn clean package

java -jar target/MineSweeper-1.0-1.jar

## Run Tests

mvn test

## Log file location

./logs/minesweeper.log

## Assumptions

* Minimum grid size is 4
* Mines to place on the grid (maximum is 35% of the total squares)


## Rules:
- You may NOT use any external libraries that directly solve this problem, but you may use external libraries or tools for building or testing purposes. Specifically, you may use unit-testing libraries or build tools available for your chosen language (e.g., JUnit, NUnit, etc.).
- You should NOT include any executable attachments, including those with .exe or .lib extensions. System security is very important to us and certain file extensions will be blocked for security purposes, resulting in delays to your application. We need to be able to run and build your code ourselves, so please submit your code as a zipped file of source code and supporting files, without any compiled code. If you're submitting in C#, please do not submit your code as a .msi file.
- Please include a brief explanation of your design and assumptions, along with your code, as well as detailed instructions to run your application. Also include the environment required to run the application, eg. Windows, Linux.
- We assess a number of things including your application code design, your tests and general readability. While these are relatively small problems, we expect you to submit what you believe is production-quality code; code that you'd be able to run, maintain, and evolve. You don't need to gold plate your solution, however we are looking for something more than a bare-bones algorithm.

# Problem statement: MineSweeper App
Write a program that simulates a Minesweeper game on a square grid.

- The game should begin by prompting the user for the grid size and the number of mines to be randomly placed on the grid.

- The program should then generate the grid and randomly place the specified number of mines on the grid.

- The user should then be prompted to select a square on the grid to uncover.
    - If the selected square contains a mine, the game is over and the user loses.
    - Otherwise, the selected square is uncovered and reveals a number indicating how many of its adjacent squares contain mines.
    - If an uncovered square has no adjacent mines, the program should automatically uncover all adjacent squares until it reaches squares that do have adjacent mines.

- The game is won when all non-mine squares have been uncovered.

- The program should display the game grid and allow the user to input their choices through a command line interface.

- Additionally, the program should track the user's progress throughout the game, displaying the minefield after each user input.