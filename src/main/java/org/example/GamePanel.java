package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    boolean leftPressed, rightPressed;

    int[][] bricks = new int[][] {
            { 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1 }
    };

    int barX = 0, barY = 640, barWidth = 100, barHeight = 10;
    int ballX = 200, ballY = 100, ballWidth = 15, ballHeight = 15;
    int ballVelocityX = 5, ballVelocityY = 5;
    int brickWidth = 80, brickHeight = 15;
    int brickX = 0, brickY = 0;
    int totalBricksX = bricks.length;
    int totalBricksY = bricks[0].length;
    Thread gameThread;
    KeyListener gameKeyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                leftPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                rightPressed = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                leftPressed = false;
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                rightPressed = false;
        }
    };

    GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(gameKeyListener);
        this.setFocusable(true);
    }

    void paintBar(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(barX, barY, barWidth, barHeight);
    }

    void paintBall(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillOval(ballX, ballY, ballWidth, ballHeight);
    }

    void paintBricks(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for(int i = 0; i < totalBricksX; i++) {
            brickY = i * brickHeight + (i * 10) + 10;
            for(int j = 0; j < totalBricksY; j++) {
                brickX = j * brickWidth + (j * 10) + 25;
                if(bricks[i][j] == 1) {
                    g2d.fillRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }
    }

    void updateGameBar() {
        if (leftPressed) {
            if (barX - 5 <= 0)
                barX -= (barX - 5);
            else barX -= 5;
        }
        if (rightPressed) {
            if (barX + 5 >= 380)
                barX += (380 - barX);
            else barX += 5;
        }
    }

    void updateGameBall() {
        if (ballY == (barY - ballHeight) && ballX >= barX && ballX <= (barX + barWidth))
            ballVelocityY = -ballVelocityY;
        else if (ballY == 0)
            ballVelocityY = -ballVelocityY;
        else if (ballX == 0 || ballX == 470)
            ballVelocityX = -ballVelocityX;

        ballX += ballVelocityX;
        ballY += ballVelocityY;
    }

    void update() {
        updateGameBar();
        updateGameBall();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBar(g2d);
        paintBall(g2d);
        paintBricks(g2d);
        g2d.dispose();
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
                if(remainingTime < 0)
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
