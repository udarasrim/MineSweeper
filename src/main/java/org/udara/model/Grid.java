package org.udara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class Grid {
    private final int size;
    private final int minesCount;
    private final Map<Position, Square> squareMap;

    @Setter
    private GridStatus gridStatus = GridStatus.NOT_STARTED;

    public Grid(int size, int minesCount) {
        this.size = size;
        this.minesCount = minesCount;
        this.squareMap = new HashMap<>(size * size);
        initSquares(size);
    }

    private void initSquares(int size) {
        for (int r = 1; r <= size; r++) {
            for (int c = 1; c <= size; c++) {
                Position position = new Position(r, c);
                this.squareMap.put(position, new Square(position));
            }
        }
    }

    public List<Square> getNeighbors(Position position) {
        List<Square> neighbors = new ArrayList<>(8);
        int row = position.getRow();
        int col = position.getCol();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int newRow = row + i;
                int newCol = col + j;

                Position neighborPosition = new Position(newRow, newCol);
                if (isValid(neighborPosition) && !neighborPosition.equals(position)) {
                    neighbors.add(squareMap.get(neighborPosition));
                }
            }
        }
        return neighbors;
    }

    private boolean isValid(Position p) {
        return p.getRow() >= 1 && p.getRow() <= size
                && p.getCol() >= 1 && p.getCol() <= size;
    }

    public Square getSquare(Position position) {
        return getSquareMap().get(position);
    }

    public void revealAllSquares() {
        squareMap.values().forEach(square -> square.setRevealed(true));
    }

    public void startGrid() {
        gridStatus = GridStatus.IN_PROGRESS;
    }

    public Collection<Square> getAllSquares() {
        return squareMap.values();
    }
}
