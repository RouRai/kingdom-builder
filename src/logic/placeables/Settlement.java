package logic.placeables;

import java.awt.*;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is a very low-level class made to represent the bare-bones of a settlement and its data.
 */
public class Settlement {

    private final Color settlementColor;
    public Settlement (Color playerColor) {
        settlementColor = playerColor;
    }

    public Color getSettlementColor() {
        return settlementColor;
    }
}
