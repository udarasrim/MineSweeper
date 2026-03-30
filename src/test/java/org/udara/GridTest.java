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
        Exception exception =  assertThrows(IllegalArgumentException.class, () -> {
            new Grid(3, 1);
        });

        assertTrue("Minimum grid size allowed is 4".contains(exception.getMessage()));
    }


    @Test
    public void gridSizeShouldHaveMaximumOf35PercentOfMines() {
        Exception exception =  assertThrows(IllegalArgumentException.class, () -> {
            new Grid(4, 6);
        });

        assertTrue(exception.getMessage().contains("Mines cannot exceed 35% of the grid"));
    }
}
