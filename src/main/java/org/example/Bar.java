package org.example;

import java.awt.*;

public class Bar {
    int X, Y, width, height;
    BarKeyHandler barKeyHandler = new BarKeyHandler();
    private static Bar bar;

    private Bar() {
        X = 0;
        Y = 640;
        width = 100;
        height = 10;
    }

    public static Bar getInstance() {
        if(bar == null) {
            bar = new Bar();
        }
        return bar;
    }

    void paintBar(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(X, Y, width, height);
    }

    void updateGameBar() {
        if (barKeyHandler.leftPressed) {
            if (X - 5 <= 0)
                X -= (X - 5);
            else X -= 5;
        }
        if (barKeyHandler.rightPressed) {
            if (X + 5 >= 380)
                X += (380 - X);
            else X += 5;
        }
    }
}
