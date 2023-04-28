package logic.tiles.actionAdjacencies;

import datastructures.gameDatastructures.boardNodes.TerrainNode;

import java.util.ArrayList;

public interface ActionAdjacency {

    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     *
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    public ArrayList<TerrainNode> getValidNodes ();
}
