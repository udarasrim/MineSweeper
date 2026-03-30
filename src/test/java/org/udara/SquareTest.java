package org.udara;

import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void shouldCreateUnRevealedNonMineSquare() {
        Square square = new Square();

        Assert.assertFalse(square.isRevealed());
        Assert.assertFalse(square.isMine());
    }

    @Test
    public void shouldCreateUnRevealedMineSquare() {
        Square square = new Square(false, true);

        Assert.assertFalse(square.isRevealed());
        Assert.assertTrue(square.isMine());
    }
}
