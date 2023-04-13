package files;

import datastructures.gameDatastructures.TerrainNode;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FileCheckerBoard board = new FileCheckerBoard("tavern", 0);
        TerrainEnum[][] matrix = board.getEnumTiles();
        for (TerrainEnum[] row : matrix) {
            for (TerrainEnum terrainType : row) {
                TerrainNode node = new TerrainNode(terrainType);
            }
        }
    }
}
