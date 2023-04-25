package logic.gameLogic;

import datastructures.gameDatastructures.boardMatrices.ActionMatrix;
import datastructures.gameDatastructures.boardMatrices.CityMatrix;
import datastructures.gameDatastructures.boardMatrices.TerrainMatrix;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import files.mainMakers.ActionMaker;
import files.mainMakers.CityMaker;
import files.mainMakers.TerrainMaker;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the high-level class mainly used to perform operations upon the Kingdom Builder Board.
 */
public class Board {
    private final TerrainMatrix terrainBoard;
    private final CityMatrix cityBoard;
    private final ActionMatrix actionBoard;

    public Board (ArrayList<TerrainMaker> terrainQuadrants, ArrayList<CityMaker> cityQuadrants, ArrayList<ActionMaker> actionQuadrants) {
        terrainBoard = new TerrainMatrix(terrainQuadrants);
        cityBoard = new CityMatrix(cityQuadrants);
        actionBoard = new ActionMatrix(actionQuadrants);
    }

    /**
     * Returns the tiles a specific player can use.
     * @param player <code>Player</code> whose available tiles are to be returned.
     * @param currentCard <code>TerrainCard</code> that contains the current type.
     * @return ArrayList of <code>TerrainNodes</code> that this specific player can use.
     */
    public ArrayList<TerrainNode> regularCanUseTiles(Player player, TerrainCard currentCard) {
        ArrayList<TerrainNode> validNodes = new ArrayList<>();
        for (int row = 0; row < terrainBoard.getBoardMatrix().length; row++) {
            for (int column = 0; column < terrainBoard.getBoardMatrix()[row].length; column++) {
                TerrainNode node = terrainBoard.getBoardMatrix()[row][column];
                if (canPlaceOnTile(player, node, currentCard)) {
                    validNodes.add(node);
                }
            }
        }
        return validNodes;
    }

    /**
     * Returns a boolean value if a player can place a settlement on a specific node in the Board.
     * @param player The <code>Player</code> that is trying to place the settlement.
     * @param node The <code>TerrainNode</code> that is to be placed on.
     * @param card The current <code>TerrainCard</code> being used
     * @return <code>boolean</code> of whether a player can place a settlement on a specific node.
     */
    private boolean canPlaceOnTile (Player player, TerrainNode node, TerrainCard card) {
        if(node == null){
            return false;
        }
        if (player.getSettlementsRemaining() < 1) {
            return false;
        }
        if (!(node.getTile().getType() == card.type())) {
            return false;
        }
        System.out.println(node.getTile().getSettlement());
        if (node.getTile().getSettlement() != null) {
            System.out.println( "has settlement");
            return false;
        }
        HashSet<TerrainNode> settlementsAdjacentToTerrain = hasSettlementAdjacentToTerrain(player, card);
        boolean needToUseSettlementAdjacentToTerrain = !(settlementsAdjacentToTerrain.size() == 0);
        if (needToUseSettlementAdjacentToTerrain && settlementsAdjacentToTerrain.size() < 3 && !tilesAdjacentToTerrain(node, card.type()).isEmpty()){
            for (TerrainNode n : settlementsAdjacentToTerrain){
                System.out.println(n.getTile().getSettlement()+ " -------+++++");
            }
        }
        boolean nodeChosenIsAdjacentToSettlementAdjacentToTerrain = settlementsAdjacentToTerrain.contains(node);
        return !needToUseSettlementAdjacentToTerrain || nodeChosenIsAdjacentToSettlementAdjacentToTerrain;
    }


    /**
     * Returns the settlements of this player that are adjacent to the current terrain type.
     * @param player The <code>Player</code> whose settlements are to be examined.
     * @param card The current <code>TerrainCard</code> that holds the current terrain type.
     * @return <code>HashSet</code> of TerrainNodes that have settlements adjacent to that specific terrain.
     */
    private HashSet<TerrainNode> hasSettlementAdjacentToTerrain (Player player, TerrainCard card) {
        HashSet<TerrainNode> validNodes = new HashSet<>();
        for (Settlement settlement : player.getSettlements()) {
            TerrainNode settlementNode = settlement.getLocation();
            HashSet<TerrainNode> validAdjacentToSettlement = tilesAdjacentToTerrain(settlementNode, card.type());
            validNodes.addAll(validAdjacentToSettlement);
        }
        return validNodes;
    }

    /**
     * Returns the valid placements adjacent to a single <code>Node</code>
     * @param node <code>TerrainNode</code> that contains the adjacent tiles.
     * @param type <code>TerrainEnum</code> that has the current type to be tested.
     * @return <code>HashSet</code> of <code>TerrainNodes</code> that are valid settlement placements.
     */
    private HashSet<TerrainNode> tilesAdjacentToTerrain(TerrainNode node, TerrainEnum type) {
        HashSet<TerrainNode> validSettlements = new HashSet<>();
        for (TerrainNode terrainNode : node.getAdjacentNodes().values()) {
            if (terrainNode.getType() == type && terrainNode.getTile().getSettlement() == null) {
                validSettlements.add(terrainNode);
            }
        }
        return validSettlements;
    }

    public TerrainMatrix getTerrainBoard() {
        return terrainBoard;
    }

    public CityMatrix getCityBoard() {
        return cityBoard;
    }

    public ActionMatrix getActionBoard() {
        return actionBoard;
    }


}
