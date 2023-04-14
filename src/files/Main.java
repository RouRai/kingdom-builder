package files;

import datastructures.gameDatastructures.TerrainNode;
import logic.constantFolder.TerrainEnum;

public class Main {
    public static void main(String[] args) {
        QuadrantMaker board = new QuadrantMaker("tavern", 0);
        TerrainEnum[][] matrix = board.getEnumTiles();
        for (TerrainEnum[] row : matrix) {
            for (TerrainEnum terrainType : row) {
                TerrainNode node = new TerrainNode(terrainType);
            }
        }
    }
}
