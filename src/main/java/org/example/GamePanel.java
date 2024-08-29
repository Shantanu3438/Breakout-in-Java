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
        if (gameState.currentGameState == GameStates.OPENED) {
            changeGameStateToRunning();
        }
        bar.updateGameBar();
        ball.updateGameBall();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        bar.paintBar(g2d);
        ball.paintBall(g2d);
        for (Brick brick : bricks) {
            brick.checkCollisionWithBall();
            brick.paintBrick(g2d);
        }
        g2d.dispose();
    }

    void changeGameStateToRunning() {
        if (keyHandler.leftKeyPressed || keyHandler.rightKeyPressed) {
            gameState.currentGameState = GameStates.RUNNING;
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
            update();
            repaint();
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
