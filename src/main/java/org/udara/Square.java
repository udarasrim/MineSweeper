package org.udara;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {

    private boolean revealed;
    private final boolean mine;
    private int adjacentMines;

    public Square() {
        this(false, false);
    }

    public Square(boolean revealed, boolean mine) {
        this.revealed = revealed;
        this.mine = mine;
    }
}
