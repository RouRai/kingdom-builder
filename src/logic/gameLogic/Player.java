package logic.gameLogic;

import logic.cards.TerrainCard;
import logic.placeables.Settlement;
import logic.tiles.ActionTile;
import logic.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the high-level class mainly used to track and store relevant user action data.
 */

public class Player {

    private final ArrayList<Settlement> settlements;
    private int settlementsRemaining;
    private final int playerColor;
    private TerrainCard card;
    private final HashMap<ActionTile, Integer> actionTiles;
    private int points;


    public Player (int playerColor) {
        settlements = new ArrayList<>();
        settlementsRemaining = 40;
        this.playerColor = playerColor;
        actionTiles = new HashMap<ActionTile, Integer>();
        points = 0;
    }

    public Settlement getSettlement () {
        if(settlementsRemaining <= 0) {
            return null;
        }
        settlementsRemaining--;
        return new Settlement(playerColor);
    }

    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }

    public int getSettlementsRemaining() {
        return settlementsRemaining;
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public void setSettlementsRemaining(int settlementsRemaining) {
        this.settlementsRemaining = settlementsRemaining;
    }

    public TerrainCard getCard () {
        return card;
    }

    public void setCard (TerrainCard card) {
        this.card = card;
    }

    public boolean canUseTile (Tile<?> tile) {
        // Returns if a player can use a specific tile or not
        return false;
    }

    public void giveActionTile (ActionTile tile) {
        if(actionTiles.containsKey(tile)){

        }
    }

    public void removeActionTile (ActionTile tile) {
        actionTiles.remove(tile);
    }

    public HashSet<ActionTile> getActionTiles () {
        return null;
    }

    public void addPoints (int pointsToAdd) {
        points = Math.min(points + pointsToAdd, 100);
    }

    public void removePoints (int pointsToRemove) {
        points = Math.max(points - pointsToRemove, 0);
    }
}
