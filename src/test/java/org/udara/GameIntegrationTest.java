package org.udara;

import org.junit.Before;
import org.junit.Test;
import org.udara.model.Grid;
import org.udara.model.GridStatus;
import org.udara.model.Position;
import org.udara.model.Square;
import org.udara.services.impl.MineGeneratorImpl;
import org.udara.services.impl.RevealServiceImpl;

import static org.junit.Assert.assertTrue;

public class GameIntegrationTest {
    private MineGeneratorImpl mineGenerator;
    private RevealServiceImpl revealService;

    @Before
    public void setup() {
        mineGenerator = new MineGeneratorImpl();
        revealService = new RevealServiceImpl();
    }

    @Test
    public void shouldPlayGameUntilWinOrMineHit() {
        Grid grid = new Grid(4, 3);

        mineGenerator.generate(grid);

        for (Square square : grid.getAllSquares()) {

            if (grid.getGridStatus() == GridStatus.WON ||
                    grid.getGridStatus() == GridStatus.HIT_MINE) {
                break;
            }

            Position pos = square.getPosition();
            revealService.reveal(grid, pos);
        }

        assertTrue(
                grid.getGridStatus() == GridStatus.WON ||
                        grid.getGridStatus() == GridStatus.HIT_MINE
        );
    }

}
