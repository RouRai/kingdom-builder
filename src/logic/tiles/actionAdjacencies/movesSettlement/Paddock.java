package logic.tiles.actionAdjacencies.movesSettlement;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.tiles.actionAdjacencies.ActionAdjacency;

import java.util.ArrayList;

public class Paddock implements ActionAdjacency {
    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     *
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        return null;
    }
}
