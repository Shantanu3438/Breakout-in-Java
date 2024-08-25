package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BarKeyHandler implements KeyListener {
    boolean leftPressed, rightPressed;

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
}
