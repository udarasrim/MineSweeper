package org.udara;

import org.junit.Test;
import org.udara.app.input.InputParser;
import org.udara.model.Position;

import static org.junit.Assert.assertEquals;

public class InputParserTest {

    @Test
    public void verifyInputPosition() {

        Position position = InputParser.parse("B4", 4);

        assertEquals(new Position(2, 4), position);
    }
}
