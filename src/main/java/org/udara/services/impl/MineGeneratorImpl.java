package org.udara.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.udara.model.Grid;
import org.udara.model.Square;
import org.udara.services.MineGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class MineGeneratorImpl implements MineGenerator {

    @Override
    public void generate(Grid grid, int minesCount) {
        log.debug("Generating {} mines", minesCount);
        placeMines(grid, minesCount);
        populateAdjacentMines(grid);
        log.debug("Mines placed successfully");
    }

    private void placeMines(Grid grid, int minesCount) {
        Random random = new Random();
        List<Square> squares = new ArrayList<>(grid.getAllSquares());

        int placed = 0;

        while (placed < minesCount) {
            Square square =  squares.get(random.nextInt(squares.size()));

            if (!square.isMine()) {
                square.setMine(true);
                placed++;
            }
        }
    }

    private void populateAdjacentMines(Grid grid) {

        for (Square square : grid.getAllSquares()) {
            if (square.isMine()) {
                continue;
            }
            List<Square> neighbors = grid.getNeighbors(square.getPosition());
            long adjacentMineCount = neighbors.stream()
                    .filter(Square::isMine)
                    .count();
            square.setAdjacentMines((int) adjacentMineCount);
        }
    }

}
