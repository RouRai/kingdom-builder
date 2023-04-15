package logic.placeables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Settlement {

    private final int color;
    private int row, col, quadNum;
    public Settlement (int color, int r, int c, int q) {
        this.color = color;
        row = r;
        col = c;
        quadNum = q;
    }

    public int getImage () {
        return color;
    }
}
