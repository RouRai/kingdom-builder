package logic.gameLogic;

import datastructures.gameDatastructures.boardNodes.CityNode;
import logic.cards.TerrainCard;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;
import logic.tiles.CityTile;
import logic.tiles.actionAdjacencies.ActionProcess;
import logic.tiles.actionAdjacencies.movesSettlement.Barn;
import logic.tiles.actionAdjacencies.movesSettlement.Harbor;
import logic.tiles.actionAdjacencies.placeSettlements.*;

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
    private final int playerNumber;
    private TerrainCard card;

    public ArrayList<Integer> getScores() {
        return scores;
    }
    private HashSet<CityNode> cityNodes;

    private ArrayList<Integer> scores;
    private final HashMap<ActionEnum, Integer> actionTiles;
    private int points, numSettlementsPlaced;
    private boolean hasPlacedSettlements, isPlacingRegSettlements, isUsingActionTile;
    private HashMap<ActionEnum, ? super ActionProcess> actionProcess;

    public Player (int playerNumber) {
        settlements = new ArrayList<>();
        settlementsRemaining = 40;
        this.playerNumber = playerNumber;
        actionTiles = new HashMap<>();
        points = 0;
        numSettlementsPlaced = 0;
        hasPlacedSettlements = false;
        isPlacingRegSettlements = false;
        isUsingActionTile = false;
        actionProcess = new HashMap<>();
    }

    public void setActionProcess(Board board) {
        actionProcess.put(ActionEnum.FARM, new Farm(board, this));
        actionProcess.put(ActionEnum.HARBOR, new Harbor(board, this));
        actionProcess.put(ActionEnum.BARN, new Barn(board, this));
        actionProcess.put(ActionEnum.TAVERN, new Tavern(board, this));
        actionProcess.put(ActionEnum.ORACLE, new Oracle(board, this));
        actionProcess.put(ActionEnum.OASIS, new Oasis(board, this));
        actionProcess.put(ActionEnum.TOWER, new Tower(board, this));
    }
    public HashMap<ActionEnum, ? super ActionProcess> getActionProcess(){
        return actionProcess;
    }

    public void addToHashMap(ActionEnum type){
        actionTiles.put(type, 0);
    }

    public Settlement getSettlement (int quadNum, int row, int col) {
        if(settlementsRemaining <= 0) {
            return null;
        }
        settlementsRemaining--;
        Settlement temp = new Settlement(playerNumber, row, col, quadNum, this);
        settlements.add(temp);
        return temp;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
        for(int i = 0; i < scores.size(); i++){
            addPoints(scores.get(i));
        }
    }
    public int getFinalScore(){
        return points;
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
    public void addCityNode(CityNode n){cityNodes.add(n);}

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

    public void giveActionTile (ActionEnum tile) {
        if(actionTiles.get(tile) == 1){
            actionTiles.put(tile, 2);
            return;
        }
        actionTiles.put(tile, 1);
    }

    public void removeActionTile (ActionEnum tile) {
        if(!actionTiles.containsKey(tile)){
            return;
        }
        if(actionTiles.get(tile) == 2){
            actionTiles.put(tile, 1);
            return;
        }
        actionTiles.put(tile, 0);
    }

    public HashMap<ActionEnum, Integer> getActionTiles () {
        return actionTiles;
    }

    public void addPoints (int pointsToAdd) {
        points = Math.min(points + pointsToAdd, 100);
    }

    public boolean isPlacingRegSettlements() {
        return isPlacingRegSettlements;
    }

    public boolean hasPlacedSettlements() {
        return hasPlacedSettlements;
    }

    public boolean isUsingActionTile() {
        return isUsingActionTile;
    }

    public void setHasPlacedSettlements(boolean hasPlacedSettlements) {
        this.hasPlacedSettlements = hasPlacedSettlements;
    }

    public void setPlacingRegSettlements(boolean placingRegSettlements) {
        isPlacingRegSettlements = placingRegSettlements;
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
