package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;
import logic.tiles.TerrainTile;

import java.io.File;
import java.util.ArrayList;

public class Board {

    private final BoardGraph board;

    public Board () {
        board = new BoardGraph(null);
    }

    public BoardGraph getBoard () {
        return board;
    }

    public void addTiles (ArrayList<File> files) {
        // Add tiles using txt files
    }

    public ArrayList<TerrainTile> playerCanUseTiles (Player player) {
        // Returns arraylist of tiles player can use
        return null;
    }
}
