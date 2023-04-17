package logic.gameLogic;

import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
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

    private static Board board;
    private final ArrayList<Settlement> settlements;
    private ArrayList<TerrainEnum> hasNotPlacedOn;
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
        actionTiles = new HashMap<ActionTile, Integer>();
        points = 0;
        numSettlementsPlaced = 0;
        hasPlacedSettlements = false;
        isPlacingSettlements = false;
        isUsingActionTile = false;
        createSettlementsNotPlacedOn();
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
            actionTiles.put(tile, 2);
            return;
        }
        actionTiles.put(tile, 1);
    }

    public void removeActionTile (ActionTile tile) {
        if(actionTiles.containsKey(tile) == false){
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

    private void createSettlementsNotPlacedOn () {
        hasNotPlacedOn = new ArrayList<>();
        hasNotPlacedOn.add(TerrainEnum.DESERT);
        hasNotPlacedOn.add(TerrainEnum.FLOWER);
        hasNotPlacedOn.add(TerrainEnum.GRASS);
        hasNotPlacedOn.add(TerrainEnum.WATER);
        hasNotPlacedOn.add(TerrainEnum.FOREST);
    }

    public boolean hasNotPlacedOn (TerrainEnum terrain) {
        return hasNotPlacedOn.contains(terrain);
    }

    public void removeNewTerrain (TerrainEnum terrain) {
        hasNotPlacedOn.remove(terrain);
    }

    public void setBoard (Board board) {
        Player.board = board;
    }
}
