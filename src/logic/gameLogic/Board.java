package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;
import datastructures.gameDatastructures.BoardMatrix;
import datastructures.gameDatastructures.TerrainNode;
import files.QuadrantMaker;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;
import logic.tiles.TerrainTile;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the high-level class mainly used to perform operations upon the Kingdom Builder Board.
 */
public class Board {

    private final BoardGraph board;
    private final BoardMatrix boardMatrix;

    public Board (ArrayList<QuadrantMaker> quadrants) {
        board = new BoardGraph(null);
        boardMatrix = new BoardMatrix(quadrants);
    }

    /**
     * Returns the graph of the current game board
     * @return BoardGraph board
     */
    public BoardGraph getBoard () {
        return board;
    }

    /**
     * Adds tiles to the graph given text files
     * @param files Text files to be parsed and to be used in order to add nodes
     */
    public void addTiles (ArrayList<File> files) {
        // Add tiles using txt files
    }

    /**
     * Returns the tiles a specific player can use.
     * @param player Player whose available tiles are to be returned.
     * @return ArrayList of Terrain Tiles that this specific player can use.
     */
    public ArrayList<TerrainTile> playerCanUseTiles (Player player) {
        // Returns arraylist of tiles player can use
        return null;
    }

    private boolean canPlaceOnTile (Player player, TerrainNode node, TerrainCard card) {
        if (!(node.getTile().getType() == card.type())) {
            return false;
        }
        ArrayList<TerrainNode> settlementsAdjacentToTerrain = hasSettlementAdjacentToTerrain(player, card);
        boolean needToUseSettlementAdjacentToTerrain = !(settlementsAdjacentToTerrain.size() == 0);
        boolean nodeChosenIsAdjacentToSettlementAdjacentToTerrain = settlementsAdjacentToTerrain.contains(node);
        if (needToUseSettlementAdjacentToTerrain && !nodeChosenIsAdjacentToSettlementAdjacentToTerrain) {
            return false;
        }
        return true;
    }

    private ArrayList<TerrainNode> hasSettlementAdjacentToTerrain (Player player, TerrainCard card) {
        ArrayList<TerrainNode> validNodes = new ArrayList<>();
        for (Settlement settlement : player.getSettlements()) {
            TerrainNode settlementNode = settlement.getLocation();
            ArrayList<TerrainNode> validAdjacentToSettlement = tilesAdjacentToTerrain(settlementNode, card.type());
            validNodes.addAll(validAdjacentToSettlement);
        }
        return validNodes;
    }

    private ArrayList<TerrainNode> tilesAdjacentToTerrain(TerrainNode node, TerrainEnum type) {
        ArrayList<TerrainNode> validSettlements = new ArrayList<>();
        for (TerrainNode terrainNode : node.getAdjacentNodes().values()) {
            if (terrainNode.getType() == type) {
                validSettlements.add(terrainNode);
            }
        }
        return validSettlements;
    }

}
