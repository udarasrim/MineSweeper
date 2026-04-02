package org.udara;

import org.junit.Before;
import org.junit.Test;
import org.udara.model.Grid;
import org.udara.model.Square;
import org.udara.services.impl.MineGeneratorImpl;

import static org.junit.Assert.assertEquals;

public class MineGeneratorTest {

    private MineGeneratorImpl mineGenerator;

    @Before
    public void setup() {
        mineGenerator = new MineGeneratorImpl();
    }

    @Test
    public void shouldPlaceExactNumberOfMines() {
        Grid grid = new Grid(5, 5);

        mineGenerator.generate(grid);

        long mineCount = grid.getAllSquares().stream()
                .filter(Square::isMine)
                .count();

        assertEquals(5, mineCount);
    }

    @Test
    public void shouldNotPlaceDuplicateMines() {
        Grid grid = new Grid(5, 5);

        mineGenerator.generate(grid);

        long mineCount = grid.getAllSquares().stream()
                .filter(Square::isMine)
                .count();

        assertEquals(grid.getMinesCount(), mineCount);
    }


}
