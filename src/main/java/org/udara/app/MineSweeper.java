package org.udara.app;

import org.udara.app.input.InputHandler;
import org.udara.services.MineGenerator;
import org.udara.services.RevealService;
import org.udara.services.impl.MineGeneratorImpl;
import org.udara.services.GridRenderer;
import org.udara.services.impl.GridRendererImpl;
import org.udara.services.impl.RevealServiceImpl;

public class MineSweeper {

    static void main() {
        GridRenderer gridRenderer = new GridRendererImpl();
        InputHandler inputHandler = new InputHandler();
        MineGenerator mineGenerator = new MineGeneratorImpl();
        RevealService revealService = new RevealServiceImpl();
        Game game = new Game(gridRenderer, inputHandler, mineGenerator, revealService);
        game.play();
    }
}
