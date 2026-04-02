package org.udara.app;

import lombok.extern.slf4j.Slf4j;
import org.udara.app.input.InputHandler;
import org.udara.services.MineGenerator;
import org.udara.services.RevealService;
import org.udara.services.impl.MineGeneratorImpl;
import org.udara.services.GridRenderer;
import org.udara.services.impl.GridRendererImpl;
import org.udara.services.impl.RevealServiceImpl;

/**
 * The main class of the application.
 */
@Slf4j
public class MineSweeper {

    public static void main(String[] args) {
        try {
            GridRenderer gridRenderer = new GridRendererImpl();
            InputHandler inputHandler = new InputHandler();
            MineGenerator mineGenerator = new MineGeneratorImpl();
            RevealService revealService = new RevealServiceImpl();
            Game game = new Game(gridRenderer, inputHandler, mineGenerator, revealService);
            game.play(); // Game starts here
        } catch (Exception e) {
            log.error("Game ends with Error: ", e);
            throw e;
        }

    }
}
