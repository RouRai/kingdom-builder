package files.mainMakers;

import files.QuadrantMaker;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

public class TerrainMaker extends QuadrantMaker<TerrainTile> {

    public TerrainMaker(int boardNumber) {
        super(boardNumber);
    }

    public void flipMatrix() {
        TerrainTile[][] destination = new TerrainTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    public TerrainTile getTypeFromSymbol(String symbol) {
        return switch (symbol) {
            case "d" -> new TerrainTile(TerrainEnum.DESERT);
            case "g" -> new TerrainTile(TerrainEnum.GRASS);
            case "f" -> new TerrainTile(TerrainEnum.FOREST);
            case "fl" -> new TerrainTile(TerrainEnum.FLOWER);
            case "w" -> new TerrainTile(TerrainEnum.WATER);
            case "v" -> new TerrainTile(TerrainEnum.CANYON);
            case "m" -> new TerrainTile(TerrainEnum.MOUNTAIN);
            default -> null;
        };
    }
}
