package logic.game;

import logic.placeables.Settlement;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private ArrayList<Settlement> settlements;
    private int settlementsRemaining;
    private Color playerColor;


    public Player (Color playerColor) {
        settlements = new ArrayList<>();
        settlementsRemaining = 40;
        this.playerColor = playerColor;
    }

    public Settlement getSettlement () {
        if(settlementsRemaining <= 0) {
            return null;
        }
        settlementsRemaining--;
        return new Settlement(playerColor);
    }

}
