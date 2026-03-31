package org.udara.app;

import org.udara.model.Position;

public class InputParser {

    // todo: validations
    public static Position parse(String input) {
        input = input.trim().toUpperCase();

        char [] inputArr = input.toCharArray();
        int row = inputArr[0] - 'A' + 1;
        int col = Character.getNumericValue(inputArr[1]);

        return new Position(row, col);
    }
}
