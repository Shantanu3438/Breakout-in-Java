package org.example;

import java.awt.*;
import java.util.Random;

public class Bar extends Entity implements EntityInterface {

    private static Bar bar;

    private Bar() {
        Random gameRandom = new Random();
        keyHandler = KeyHandler.getInstance();
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

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(X, Y, width, height);
    }

    public void update() {
        if (keyHandler.leftKeyPressed && X > 0) {
            X -= 5;
        }

        if (keyHandler.rightKeyPressed && X < 385) {
            X += 5;
        }
    }
}
