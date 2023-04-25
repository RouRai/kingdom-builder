package datastructures.gameDatastructures.boardNodes;

import datastructures.baseDatastructures.Node;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This node is used in order to store data related to Terrain Tiles. It stores nodes adjacent to it as well.
 */
public class TerrainNode extends Node<TerrainNode> {
    private final TerrainTile tile;

    public TerrainNode(TerrainEnum type, int row, int column) {
        super(row, column);
        tile = new TerrainTile(type);
    }

    /**
     * Returns the type of tile
     * @return TerrainEnum type
     */
    public TerrainEnum getType () {
        return tile.getType();
    }

    /**
     * Returns the TerrainTile object inside of this node.
     * @return TerrainTile
     */
    public TerrainTile getTile () {
        return tile;
    }
}
