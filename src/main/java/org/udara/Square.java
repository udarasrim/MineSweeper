package org.udara;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {

    private boolean revealed;
    private boolean mine;
    private int adjacentMines;
    private final Position position;

    public Square(Position position) {
        this(false, false, position);
    }

    public Square(boolean revealed, boolean mine, Position position) {
        this.revealed = revealed;
        this.mine = mine;
        this.position = position;
    }
}
