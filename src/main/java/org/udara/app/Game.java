package org.udara.app;

import lombok.extern.slf4j.Slf4j;
import org.udara.app.input.InputHandler;
import org.udara.model.Grid;
import org.udara.model.GridStatus;
import org.udara.model.Position;

import org.udara.services.MineGenerator;
import org.udara.services.GridRenderer;
import org.udara.services.RevealService;

import java.util.UUID;


@Slf4j
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
            try {
                int size = inputHandler.askGridSize();
                int minesCount = inputHandler.askMineCount(size);

                Grid grid = new Grid(size, minesCount);
                mineGenerator.generate(grid);
                start(grid);
            } catch (Exception e) {
                log.error("This iteration of the game ends with Error: ", e);
            }

            inputHandler.pressAnyKeyToContinue();
        }
    }


    public void start(Grid grid) {
        String gameId = UUID.randomUUID().toString();
        log.info("Starting game (Game ID : {}) with grid size: {} and mine count: {}", gameId, grid.getSize(), grid.getMinesCount());
        grid.startGrid();

        while (grid.getGridStatus() == GridStatus.IN_PROGRESS) {

            printGrid(grid, gridRenderer);

            Position position = inputHandler.askPosition(grid.getSize());
            log.info("User selected position {}", position);

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
                    log.info("Game won");
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
