package logic.tiles.actionAdjacencies;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.gameLogic.Board;
import logic.gameLogic.Player;

import java.util.ArrayList;

public class Farmer implements ActionAdjacency {
    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     * @param board  The current game board.
     * @param player The current player.
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes(Board board, Player player) {
        return null;
    }
}
