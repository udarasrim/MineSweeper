package org.udara.app.input;

import org.udara.model.Position;

public class InputParser {

    public static Position parse(String input, int size) {
        try {
            input = input.trim().toUpperCase();

            char[] inputArr = input.toCharArray();
            int row = inputArr[0] - 'A' + 1;
            int col = Character.getNumericValue(inputArr[1]);
            Position p = new Position(row, col);
            if (isValid(p, size)) {
                return p;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private static boolean isValid(Position p, int size) {
        return p.getRow() >= 1 && p.getRow() <= size
                && p.getCol() >= 1 && p.getCol() <= size;
    }

}
