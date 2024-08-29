package org.example;

import java.awt.*;

public class Brick {
    Ball ball = Ball.getInstance();
    int row, column;
    int X, Y;
    int width, height;
    boolean isCollided;

    public Brick(int row, int column) {
        this.row = row;
        this.column = column;
        width = 80;
        height = 15;
        X = column * width + (column * 10) + 25;
        Y = row * height + (row * 10) + 10;
        isCollided = false;
    }

    public void checkCollisionWithBall() {
        if (ball.Y < 150 && !isCollided) {
            if (ball.Y >= Y && ball.Y <= Y + height && ball.X >= X && ball.X <= X + width) {
                isCollided = true;
                ball.velocityY = -ball.velocityY;
            }
        }
    }

    public void paintBrick(Graphics2D g2d) {
        if (!isCollided)
            g2d.fillRect(X, Y, width, height);
    }
}
