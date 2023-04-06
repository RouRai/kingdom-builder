package logic.placeables;

import java.awt.*;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is a very low-level class made to represent the bare-bones of a settlement and its data.
 */
public class Settlement {

    private final int settlementColor;
    public Settlement (int playerColor) {
        settlementColor = playerColor;
    }

    public int getSettlementColor() {
        return settlementColor;
    }
}
