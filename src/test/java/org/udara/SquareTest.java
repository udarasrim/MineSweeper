package org.udara;

import org.junit.Test;
import org.udara.model.Position;
import org.udara.model.Square;

import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void shouldCreateUnRevealedNonMineSquare() {
        Square square = new Square(new Position(1, 1));

        assertFalse(square.isRevealed());
        assertFalse(square.isMine());
    }

    @Test
    public void shouldCreateUnRevealedMineSquare() {
        Square square = new Square(false, true, new Position(1, 1));

        assertFalse(square.isRevealed());
        assertTrue(square.isMine());
    }

    @Test
    public void shouldContainPosition() {
        Square square = new Square(false, true, new Position(1, 1));

        assertNotNull(square.getPosition());
    }
}
