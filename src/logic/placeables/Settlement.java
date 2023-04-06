package logic.placeables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Settlement {

    private final Color color;

    public Settlement (Color color) {
        this.color = color;
    }

    public Color getImage () {
        return color;
    }
}
