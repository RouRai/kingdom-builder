package datastructures.gameDatastructures;

import datastructures.abstractDatastructures.Node;
import logic.constantFolder.TerrainEnum;

public class TerrainNode extends Node {

    private final TerrainEnum type;

    public TerrainNode(TerrainEnum type) {
        this.type = type;
    }

    public TerrainEnum getType () {
        return type;
    }
}
