package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Node;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

public class TerrainNode extends Node<TerrainNode> {

    private final TerrainEnum type;
    private final TerrainTile tile;

    public TerrainNode(TerrainEnum type) {
        this.type = type;
        tile = new TerrainTile(type);
    }

    public TerrainEnum getType () {
        return type;
    }

    public TerrainTile getTile () {
        return tile;
    }
}
