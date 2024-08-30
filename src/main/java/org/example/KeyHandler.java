package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    boolean leftKeyPressed, rightKeyPressed, escKeyPressed;
    public static KeyHandler keyHandler = null;

    public static KeyHandler getInstance() {
        if (keyHandler == null) {
            keyHandler = new KeyHandler();
        }
        return keyHandler;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftKeyPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightKeyPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            escKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftKeyPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightKeyPressed = false;
    }
}
