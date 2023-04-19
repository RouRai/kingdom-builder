package logic.gameLogic;

import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;
import logic.tiles.ActionTile;
import logic.tiles.Tile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the high-level class mainly used to track and store relevant user action data.
 */

public class Player {
    private final ArrayList<Settlement> settlements;
    private int settlementsRemaining;
    private final int playerNumber;
    private TerrainCard card;
    private final HashMap<ActionTile, Integer> actionTiles;
    private int points, numSettlementsPlaced;
    private boolean hasPlacedSettlements, isPlacingSettlements, isUsingActionTile;

    public Player (int playerNumber) {
        settlements = new ArrayList<>();
        settlementsRemaining = 40;
        this.playerNumber = playerNumber;
        actionTiles = new HashMap<>();
        points = 0;
        numSettlementsPlaced = 0;
        hasPlacedSettlements = false;
        isPlacingSettlements = false;
        isUsingActionTile = false;
    }

    public Settlement getSettlement (int quadNum, int row, int col) {
        if(settlementsRemaining <= 0) {
            return null;
        }
        settlementsRemaining--;
        Settlement temp = new Settlement(playerNumber, row, col, quadNum);
        settlements.add(temp);
        return temp;
    }

    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }

    public int getSettlementsRemaining() {
        return settlementsRemaining;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void removeSettlement (int amount) {
        setSettlementsRemaining(getSettlementsRemaining() - amount);
    }

    public void removeSettlement () {
        removeSettlement(1);
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

    public void giveActionTile (ActionTile tile) {
        if(actionTiles.containsKey(tile)){
            actionTiles.put(tile, 2);
            return;
        }
        actionTiles.put(tile, 1);
    }

    public void removeActionTile (ActionTile tile) {
        if(!actionTiles.containsKey(tile)){
            return;
        }
        if(actionTiles.get(tile) == 2){
            actionTiles.put(tile, 1);
            return;
        }
        actionTiles.remove(tile);
    }

    public HashMap<ActionTile, Integer> getActionTiles () {
        return actionTiles;
    }

    public void addPoints (int pointsToAdd) {
        points = Math.min(points + pointsToAdd, 100);
    }

    public void removePoints (int pointsToRemove) {
        points = Math.max(points - pointsToRemove, 0);
    }

    public boolean isPlacingSettlements() {
        return isPlacingSettlements;
    }

    public boolean isHasPlacedSettlements() {
        return hasPlacedSettlements;
    }

    public boolean isUsingActionTile() {
        return isUsingActionTile;
    }

    public void setHasPlacedSettlements(boolean hasPlacedSettlements) {
        this.hasPlacedSettlements = hasPlacedSettlements;
    }

    public void setPlacingSettlements(boolean placingSettlements) {
        isPlacingSettlements = placingSettlements;
    }

    public void setUsingActionTile(boolean usingActionTile) {
        isUsingActionTile = usingActionTile;
    }
    public void setNumSettlementsPlaced(int num){
        numSettlementsPlaced = num;
    }
    public int getNumSettlementsPlaced(){
        return numSettlementsPlaced;
    }
}
