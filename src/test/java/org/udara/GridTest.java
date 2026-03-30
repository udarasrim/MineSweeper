package org.udara;

import org.junit.Test;


import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void gridShouldHaveGivenSize() {
        Grid grid = new Grid(4, 1);

        assertEquals(4, grid.getSize());
    }

    @Test
    public void gridShouldHaveGivenMineCount() {
        Grid grid = new Grid(4, 1);

        assertEquals(1, grid.getMinesCount());
    }

    @Test
    public void gridSizeShouldBeGraterThanOrEqualFour() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Grid(3, 1);
        });

        assertTrue("Minimum grid size allowed is 4".contains(exception.getMessage()));
    }


    @Test
    public void gridSizeShouldHaveMaximumOf35PercentOfMines() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Grid(4, 6);
        });

        assertTrue(exception.getMessage().contains("Mines cannot exceed 35% of the grid"));
    }

    //---------------------------------------------------------------

    @Test
    public void gridShouldHaveGivenNumberOfSquares() {
        Grid grid = new Grid(4, 1);

        assertEquals(4 * 4, grid.getSquareMap().size());
    }

    @Test
    public void gridShouldHaveGivenNumberOfMines() {
        Grid grid = new Grid(4, 1);
        long mineCount = grid.getSquareMap().values().stream()
                .filter(Square::isMine)
                .count();
        assertEquals(1, mineCount);
    }

    @Test
    public void gridShouldHaveAdjacentMines() {
        Grid grid = new Grid(4, 1);
        int adjacentMinesCount = grid.getSquareMap().values().stream()
                .map(Square::getAdjacentMines)
                .reduce(0, Integer::sum);
        assertTrue(adjacentMinesCount >= 3 && adjacentMinesCount <= 8);
    }
}
