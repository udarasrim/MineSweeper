package org.udara.services.impl;

import org.udara.model.Grid;
import org.udara.model.Position;
import org.udara.model.Square;
import org.udara.services.GridRenderer;

public class GridRendererImpl implements GridRenderer {

    @Override
    public String render(Grid grid) {
        StringBuilder sb = new StringBuilder();
        int size = grid.getSize();

        appendHeader(sb, size);

        for (int row = 1; row <= size; row++) {
            char rowName =  (char) ('A' + row - 1);
            sb.append(rowName).append(" ");

            for (int col = 1; col <= size; col++) {
                Square square = grid.getSquare(new Position(row, col));
                appendSquare(square, sb);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void appendSquare(Square square, StringBuilder sb) {
        if (!square.isRevealed()) {
            sb.append("_ ");
        } else if (square.isMine()) {
            sb.append("M ");
        } else {
            sb.append(square.getAdjacentMines()).append(" ");
        }
    }

    private void appendHeader(StringBuilder sb, int size) {
        sb.append("  ");
        for (int i = 1; i <= size; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
    }

}
