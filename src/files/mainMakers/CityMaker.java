package files.mainMakers;

import files.QuadrantMaker;
import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.tiles.CityTile;
import logic.tiles.TerrainTile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class CityMaker extends QuadrantMaker<CityTile> {

    public CityMaker(int boardNumber) {
        super(boardNumber);
    }

    public void flipMatrix() {
        CityTile[][] destination = new CityTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    public CityTile getTypeFromSymbol(String symbol) {
        if (symbol.equals("c")) {
            return new CityTile();
        }
        return null;
    }
}
