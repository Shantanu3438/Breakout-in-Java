package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    boolean leftPressed, rightPressed;
    int barX = 0, barY = 640, barWidth = 100, barHeight = 10;
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

    void paintRec(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(barX, barY, barWidth, barHeight);
    }

    void update() {
        if (leftPressed)
            if (barX - 5 <= 0)
                barX -= (barX - 5);
            else barX -= 5;
        if (rightPressed) {
            System.out.println("barX: " + barX);
            System.out.println("barWidth: " + (380 - barX));

            if (barX + 5 >= 380)
                barX += (380 - barX);
            else barX += 5;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintRec(g2d);
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
