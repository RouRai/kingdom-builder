package logic.placeables;

import java.awt.*;

public class Settlement {

    private final Color settlementColor;
    public Settlement (Color playerColor) {
        settlementColor = playerColor;
    }

    public Color getSettlementColor() {
        return settlementColor;
    }
}
