package org.udara;

import org.junit.Test;
import org.udara.model.Grid;
import org.udara.services.GridRenderer;
import org.udara.services.impl.GridRendererImpl;

import static org.junit.Assert.assertEquals;

public class GridRendererTest {

    GridRenderer gridRenderer = new GridRendererImpl();

    @Test
    public void gridShouldHaveGivenSize() {
        Grid grid = new Grid(4, 3);
        String gridString = gridRenderer.render(grid);
        long underscoreCount = gridString.chars()
                .filter(c -> c == '_')
                .count();
        assertEquals(4 * 4, underscoreCount);
    }

    @Test
    public void gridShouldHaveGivenMines() {
        Grid grid = new Grid(4, 3);
        grid.revealAllSquares();
        String gridString = gridRenderer.render(grid);
        long mineCount = gridString.chars()
                .filter(c -> c == 'M')
                .count();
        assertEquals(3, mineCount);
    }

}
