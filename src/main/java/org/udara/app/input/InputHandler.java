package org.udara.app.input;

import lombok.extern.slf4j.Slf4j;
import org.udara.model.Position;

import java.util.Scanner;

import static java.util.Objects.nonNull;

@Slf4j
public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public void pressAnyKeyToContinue() {
        System.out.println("Press any key to play again...");
        scanner.nextLine();
    }

    public int askGridSize() {
        while (true) {
            try {
                System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
                int gridSize = Integer.parseInt(scanner.nextLine());

                if(gridSize < 4) {
                    System.out.println("Minimum grid size allowed is 4");
                    log.warn("Invalid input received: {}", gridSize);
                } else {
                    return gridSize;
                }
            } catch (Exception e) {
                System.out.println("Invalid number!");
                log.warn("Invalid input received: ", e);
            }
        }
    }
    
    public int askMineCount(int size) {
        while (true) {
            try {
                System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
                int minesCount = Integer.parseInt(scanner.nextLine());
                int totalSquares = size * size;
                int maxMinesAllowed = (int) (totalSquares * 0.35);

                if (minesCount > maxMinesAllowed) {
                    System.out.println("Mines cannot exceed 35% of the grid (" + maxMinesAllowed + " max for size " + size + ")");
                    log.warn("Invalid input received: {}", minesCount);
                } else {
                    return minesCount;
                }
            } catch (Exception e) {
                System.out.println("Invalid number!");
                log.warn("Invalid input received: ", e);
            }
        }
    }

    public Position askPosition(int size) {
        while (true) {
            System.out.print("Select a square to reveal (e.g. A1):");
            String input = scanner.nextLine();

            Position pos = InputParser.parse(input, size);
            if (nonNull(pos)) {
                return pos;
            }
            log.warn("Invalid position received: {}", pos);
            System.out.println("Invalid input!");
        }
    }

}