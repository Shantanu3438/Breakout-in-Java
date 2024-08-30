package org.example;

import java.awt.*;
import java.util.Random;

public class Bar {
    int X, Y, width, height;
    KeyHandler keyHandler = KeyHandler.getInstance();
    private static Bar bar;
    GameState gameState = GameState.getInstance();

    private Bar() {
        Random gameRandom = new Random();
        X = gameRandom.nextInt(80) * 5 + 10;
        Y = 640;
        width = 100;
        height = 10;
    }

    public static Bar getInstance() {
        if (bar == null) {
            bar = new Bar();
        }
        return bar;
    }

    void paintBar(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(X, Y, width, height);
    }

    void updateGameBar() {
        if (keyHandler.leftKeyPressed && X > 0) {
            X -= 5;
        }

        if (keyHandler.rightKeyPressed && X < 385) {
            X += 5;
        }
    }
}
