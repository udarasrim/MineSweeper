package org.udara.app;

import org.udara.model.Grid;
import org.udara.model.Position;
import org.udara.model.Square;
import org.udara.ui.GridRenderer;
import org.udara.ui.impl.GridRendererImpl;

import java.util.Scanner;

public class MindSweeper {

    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int size = scanner.nextInt();

        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
        int minesCount = scanner.nextInt();

        Grid grid = new Grid(size, minesCount);
        GridRenderer gridRenderer = new GridRendererImpl();

        System.out.println("Here is your minefield:");
        System.out.println(gridRenderer.render(grid));

        while (true) {
            System.out.print("Select a square to reveal (e.g. A1):");
            String gridCoordinates = scanner.next();
            Position position = InputParser.parse(gridCoordinates);
            Square square = grid.getSquare(position);
            if (square.isMine()) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                break;
            } else {
                grid.revealSquareByPosition(position);
                System.out.println("Here is your minefield:");
                System.out.println(gridRenderer.render(grid));
            }

        }
    }
}
