package org.udara;

import org.junit.Before;
import org.junit.Test;
import org.udara.model.*;
import org.udara.services.MineGenerator;
import org.udara.services.impl.MineGeneratorImpl;
import org.udara.services.impl.RevealServiceImpl;

import static org.junit.Assert.*;

public class RevealServiceImplTest {

    private RevealServiceImpl revealService;
    private MineGenerator mineGenerator;

    @Before
    public void setup() {
        revealService = new RevealServiceImpl();
        mineGenerator = new MineGeneratorImpl();
    }

    @Test
    public void shouldReturnMinusOneWithMine() {
        Grid grid = new Grid(4, 1);
        mineGenerator.generate(grid);

        Position minePos = grid.getAllSquares().stream()
                .filter(Square::isMine)
                .findFirst()
                .get()
                .getPosition();

        int result = revealService.reveal(grid, minePos);

        assertEquals(-1, result);
        assertEquals(GridStatus.HIT_MINE, grid.getGridStatus());
    }

    @Test
    public void shouldRevealSafeSquare() {
        Grid grid = new Grid(4, 1);
        mineGenerator.generate(grid);

        Position safePos = grid.getAllSquares().stream()
                .filter(s -> !s.isMine())
                .findFirst()
                .get()
                .getPosition();

        int result = revealService.reveal(grid, safePos);

        assertTrue(result >= 0);
        assertTrue(grid.getSquare(safePos).isRevealed());
    }

    @Test
    public void shouldExpandWhenZeroAdjacentMines() {
        Grid grid = new Grid(4, 1);
        mineGenerator.generate(grid);

        Square zeroSquare = grid.getAllSquares().stream()
                .filter(s -> !s.isMine() && s.getAdjacentMines() == 0)
                .findFirst()
                .orElse(null);

        revealService.reveal(grid, zeroSquare.getPosition());

        long revealedCount = grid.getAllSquares().stream()
                .filter(Square::isRevealed)
                .count();

        assertTrue(revealedCount > 1);
    }

    @Test
    public void shouldSetStatusToWonWhenAllSafeSquaresRevealed() {
        Grid grid = new Grid(4, 1);
        mineGenerator.generate(grid);

        grid.getAllSquares().stream()
                .filter(s -> !s.isMine())
                .forEach(s -> revealService.reveal(grid, s.getPosition()));

        assertEquals(GridStatus.WON, grid.getGridStatus());
    }


}