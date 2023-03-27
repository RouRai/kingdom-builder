package logic.gameLogic;

import logic.cards.TerrainCard;
import logic.placeables.Settlement;
import logic.tiles.ActionTile;
import logic.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Player {

    private final ArrayList<Settlement> settlements;
    private int settlementsRemaining;
    private final Color playerColor;
    private TerrainCard card;
    private final HashSet<ActionTile> actionTiles;
    private int points;


    public Player (Color playerColor) {
        settlements = new ArrayList<>();
        settlementsRemaining = 40;
        this.playerColor = playerColor;
        actionTiles = new HashSet<>();
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

    public Color getPlayerColor() {
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
        actionTiles.add(tile);
    }

    public void removeActionTile (ActionTile tile) {
        actionTiles.remove(tile);
    }

    public HashSet<ActionTile> getActionTiles () {
        return actionTiles;
    }

    public void addPoints (int pointsToAdd) {
        points = Math.min(points + pointsToAdd, 100);
    }

    public void removePoints (int pointsToRemove) {
        points = Math.max(points - pointsToRemove, 0);
    }
}