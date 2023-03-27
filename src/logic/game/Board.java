package logic.game;

import logic.tiles.TerrainTile;
import logic.tiles.Tile;

import java.io.File;
import java.util.ArrayList;

public class Board {

    private final Tile[][] board;

    public Board () {
        board = new Tile[20][20];
    }

    public Tile[][] getBoard () {
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
