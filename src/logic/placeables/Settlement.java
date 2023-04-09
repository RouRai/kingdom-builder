package logic.placeables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Settlement {

    private final int color;

    public Settlement (int color) {
        this.color = color;
    }

    public int getImage () {
        return color;
    }
}
