package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    KeyHandler keyHandler = KeyHandler.getInstance();
    Bar bar = Bar.getInstance();
    Ball ball = Ball.getInstance();
    GameState gameState = GameState.getInstance();
    Brick[] bricks = new Brick[25];
    Thread gameThread;

    GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new Brick(i/5, i%5);
        }
    }

    void update() {
        bar.update();
        ball.update();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        bar.paint(g2d);
        ball.paint(g2d);
        for (Brick brick : bricks) {
            brick.checkCollisionWithBall();
            brick.paintBrick(g2d);
        }
        g2d.dispose();
    }

    void changeGameStateToStarted() {
        if (keyHandler.leftKeyPressed || keyHandler.rightKeyPressed) {
            gameState.currentGameState = GameStates.STARTED;
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            if (gameState.currentGameState == GameStates.RUNNING) {
                changeGameStateToStarted();
            }

            if (gameState.currentGameState == GameStates.STARTED && keyHandler.escKeyPressed) {
                gameState.currentGameState = GameStates.PAUSED;
                keyHandler.escKeyPressed = false;
            }

            if (gameState.currentGameState == GameStates.PAUSED && keyHandler.escKeyPressed) {
                gameState.currentGameState = GameStates.STARTED;
                keyHandler.escKeyPressed = false;
            }

            if (gameState.currentGameState == GameStates.STARTED) {
                update();
                repaint();
            }
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                if (remainingTime < 0)
                    remainingTime = 0;
                remainingTime /= 1000000;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
