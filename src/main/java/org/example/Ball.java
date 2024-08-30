package org.example;

import java.awt.*;

public class Ball extends Entity implements EntityInterface {
    Bar bar = Bar.getInstance();
    int velocityX, velocityY;
    private static Ball ball;

    private Ball() {
        keyHandler = KeyHandler.getInstance();
        X = bar.X + 50;
        Y = 625;
        width = 15;
        height = 15;
        velocityX = 5;
        velocityY = 5;
    }

    public static Ball getInstance() {
        if (ball == null) {
            ball = new Ball();
        }
        return ball;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillOval(X, Y, width, height);
    }

    public void update() {
        if (Y == (bar.Y - height) && X >= bar.X && X <= (bar.X + bar.width)) {
            if ((keyHandler.leftKeyPressed && velocityX > 0) || (keyHandler.rightKeyPressed && velocityX < 0)) {
                velocityX = -velocityX;
            }
            velocityY = -velocityY;
        }
        else if (Y == 0 || Y == 700)
            velocityY = -velocityY;
        else if (X == 0 || X == 470)
            velocityX = -velocityX;

        X += velocityX;
        Y += velocityY;
    }
}
