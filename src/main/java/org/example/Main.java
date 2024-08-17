package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        gameFrame.setSize(500, 700);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();

        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }
}