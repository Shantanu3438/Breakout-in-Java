package org.example;

import java.awt.*;

public class Ball {
    Bar bar = Bar.getInstance();

    int X, Y, width, height;
    int velocityX, velocityY;
    private static Ball ball;

    private Ball() {
        X = 200;
        Y = 200;
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

    void paintBall(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillOval(X, Y, width, height);
    }

    void updateGameBall() {
        if (Y == (bar.Y - height) && X >= bar.X && X <= (bar.X + bar.width))
            velocityY = -velocityY;
        else if (Y == 0)
            velocityY = -velocityY;
        else if (X == 0 || X == 470)
            velocityX = -velocityX;

        X += velocityX;
        Y += velocityY;
    }
}
