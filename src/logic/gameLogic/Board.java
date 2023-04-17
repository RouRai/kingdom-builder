package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;
import datastructures.gameDatastructures.TerrainNode;
import logic.cards.TerrainCard;
import logic.tiles.TerrainTile;

import java.io.File;
import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the high-level class mainly used to perform operations upon the Kingdom Builder Board.
 */
public class Board {

    private final BoardGraph board;

    public Board () {
        board = new BoardGraph(null);
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

    public boolean canPlaceOnTile (Player player, TerrainNode node, TerrainCard card) {
        if (!(node.getTile().getType() == card.type())) {
            return false;
        }
        if (player.hasNotPlacedOn(card.type())) {
            if (hasSettlementAdjacentToTerrain())
        }
    }

    private boolean hasSettlementAdjacentToTerrain (Player player, TerrainNode node) {

    }
}
