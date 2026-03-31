package org.udara.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Grid {
    private final int size;
    private final int minesCount;
    private final Map<Position, Square> squareMap;

    public Grid(int size, int minesCount) {
        if (size < 4) {
            throw new IllegalArgumentException("Minimum grid size allowed is 4");
        }

        int totalSquares = size * size;
        int maxMinesAllowed = (int) (totalSquares * 0.35);

        if (minesCount > maxMinesAllowed) {
            throw new IllegalArgumentException("Mines cannot exceed 35% of the grid (" + maxMinesAllowed + " max for size " + size + ")");
        }

        this.size = size;
        this.minesCount = minesCount;
        this.squareMap = new HashMap<>(totalSquares);
        initSquares(size);
        setMines();
        populateAdjacentMines();
    }

    private void initSquares(int size) {
        for (int r = 1; r <= size; r++) {
            for (int c = 1; c <= size; c++) {
                Position position = new Position(r, c);
                this.squareMap.put(position, new Square(position));
            }
        }
    }

    private void setMines() {
        Random random = new Random();
        int setted = 0;

        List<Position> positions = new ArrayList<>(squareMap.keySet());

        while (setted < minesCount) {
            Position p = positions.get(random.nextInt(positions.size()));
            Square square = squareMap.get(p);

            if (!square.isMine()) {
                square.setMine(true);
                setted++;
            }
        }
    }

    private void populateAdjacentMines() {
        for (Square square : squareMap.values()) {
            if (square.isMine()) {
                continue;
            }
            List<Square> neighbors = findNeighbors(square.getPosition());
            long adjacentMineCount = neighbors.stream()
                    .filter(Square::isMine)
                    .count();
            square.setAdjacentMines((int) adjacentMineCount);
        }
    }

    public List<Square> findNeighbors(Position position) {
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

    public void revealSquareByPosition(Position position) {
        Square square = getSquare(position);
        revealSquare(square, null);

    }

    private void revealSquare(Square square, Square parentSquare) {
        square.setRevealed(true);
        List<Square> neighbors = findNeighbors(square.getPosition());
        if(parentSquare != null) {
            neighbors.remove(parentSquare);
        }

        neighbors.forEach(neighborSquare -> {
            if(neighborSquare.getAdjacentMines() == 0 && !neighborSquare.isMine()) {
                revealSquare(neighborSquare, square);
            }
        });
    }
}
