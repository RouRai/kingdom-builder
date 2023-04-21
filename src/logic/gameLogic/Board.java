package logic.gameLogic;

import datastructures.gameDatastructures.BoardMatrix;
import datastructures.gameDatastructures.TerrainNode;
import files.QuadrantMaker;
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
    private final BoardMatrix board;

    public Board (ArrayList<QuadrantMaker> quadrants) {
        board = new BoardMatrix(quadrants);
    }

    /**
     * Returns the graph of the current game board
     * @return BoardGraph board
     */
    public BoardMatrix getBoard () {
        return board;
    }

    /**
     * Returns the tiles a specific player can use.
     * @param player <code>Player</code> whose available tiles are to be returned.
     * @param currentCard <code>TerrainCard</code> that contains the current type.
     * @return ArrayList of <code>TerrainNodes</code> that this specific player can use.
     */
    public ArrayList<TerrainNode> regularCanUseTiles(Player player, TerrainCard currentCard) {
        if (player.hasPlacedSettlements()) return null;
        ArrayList<TerrainNode> validNodes = new ArrayList<>();
        for (TerrainNode[] row : board.getTerrainBoardMatrix()) {
            for (TerrainNode node : row) {
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
        if (player.getSettlementsRemaining() < 1) {
            return false;
        }
        if (!(node.getTile().getType() == card.type())) {
            return false;
        }
        if (node.getTile().getSettlement() != null) {
            return false;
        }
        HashSet<TerrainNode> settlementsAdjacentToTerrain = hasSettlementAdjacentToTerrain(player, card);
        boolean needToUseSettlementAdjacentToTerrain = !(settlementsAdjacentToTerrain.size() == 0);
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
            if (terrainNode.getType() == type) {
                validSettlements.add(terrainNode);
            }
        }
        return validSettlements;
    }

}
