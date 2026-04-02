package org.udara.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.udara.model.*;
import org.udara.services.RevealService;

import java.util.List;

/**
 * Reveals the squares.
 */
@Slf4j
public class RevealServiceImpl implements RevealService {

    @Override
    public int reveal(Grid grid, Position position) {
        log.debug("Revealing position {}", position);
        Square square = grid.getSquare(position);

        if (square.isMine()) {
            log.info("Mine hit at {}", position);
            grid.setGridStatus(GridStatus.HIT_MINE);
            return -1;
        }

        revealSquare(grid, square);

        boolean isWin = grid.getAllSquares().stream()
                .allMatch(square1 -> square1.isMine() || square1.isRevealed());

        if (isWin) {
            grid.setGridStatus(GridStatus.WON);
        } else {
            grid.setGridStatus(GridStatus.IN_PROGRESS);
        }
        log.info("Grid status changed to {}", grid.getGridStatus());
        log.debug("Square has {} adjacent mines", square.getAdjacentMines());
        return square.getAdjacentMines();
    }

    private void revealSquare(Grid grid, Square square) {

        if (square.isRevealed() || square.isMine()) {
            return;
        }

        square.setRevealed(true);
        if (square.getAdjacentMines() > 0) {
            return;
        }

        List<Square> neighbors = grid.getNeighbors(square.getPosition());
        neighbors.forEach(neighbor -> revealSquare(grid, neighbor));
    }

}
