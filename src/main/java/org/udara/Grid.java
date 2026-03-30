package org.udara;

import lombok.Getter;

@Getter
public class Grid {
     private final int size;
     private final int minesCount;

    public Grid(int size, int minesCount) {
        if(size < 4) {
            throw new IllegalArgumentException("Minimum grid size allowed is 4");
        }

        int totalCells = size * size;
        int maxMinesAllowed = (int) (totalCells * 0.35);

        if (minesCount > maxMinesAllowed) {
            throw new IllegalArgumentException("Mines cannot exceed 35% of the grid (" + maxMinesAllowed + " max for size " + size + ")");
        }

        this.size = size;
        this.minesCount = minesCount;
    }
}
