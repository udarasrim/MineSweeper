package org.udara.app;

import org.udara.app.input.InputHandler;
import org.udara.model.Grid;
import org.udara.model.GridStatus;
import org.udara.model.Position;
import org.udara.model.RevealResponse;
import org.udara.services.MineGenerator;
import org.udara.services.GridRenderer;
import org.udara.services.RevealService;

public class Game {

    private final GridRenderer gridRenderer;
    private final InputHandler inputHandler;
    private final MineGenerator mineGenerator;
    private final RevealService revealService;

    public Game(GridRenderer gridRenderer, InputHandler inputHandler, MineGenerator mineGenerator, RevealService revealService) {
        this.gridRenderer = gridRenderer;
        this.inputHandler = inputHandler;
        this.mineGenerator = mineGenerator;
        this.revealService = revealService;
    }

    public void play() {
        System.out.println("Welcome to Minesweeper!");

        while (true) {
            int size = inputHandler.askGridSize();
            int minesCount = inputHandler.askMineCount(size);

            Grid grid = new Grid(size, minesCount);
            mineGenerator.generate(grid, minesCount);
            start(grid);

            inputHandler.pressAnyKeyToContinue();
        }
    }


    public void start(Grid grid) {

        grid.startGrid();

        while (grid.getGridStatus() == GridStatus.IN_PROGRESS) {

            printGrid(grid, gridRenderer);

            Position position = inputHandler.askPosition(grid.getSize());

            int adjacentMines = revealService.reveal(grid, position);

            switch (grid.getGridStatus()) {
                case HIT_MINE -> {
                    System.out.println("Oh no, you detonated a mine! Game over.");
                    return;
                }
                case WON -> {
                    System.out.printf("This square contains %d adjacent mines.%n", adjacentMines);
                    printGrid(grid, gridRenderer);
                    System.out.println("Congratulations, you have won the game!");
                    return;
                }
                case IN_PROGRESS -> {
                    System.out.printf("This square contains %d adjacent mines.%n", adjacentMines);
                }
            }
        }
    }

    private void printGrid(Grid grid, GridRenderer gridRenderer) {
        System.out.println("Here is your minefield:");
        System.out.println(gridRenderer.render(grid));
    }

}
