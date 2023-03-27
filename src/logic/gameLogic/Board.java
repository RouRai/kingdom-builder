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

    /**
     * Returns the graph of the current game board
     * @return BoardGraph board
     */
    public BoardGraph getBoard () {
        return board;
    }

    /**
     * Adds tiles to the graph given text files
     * @param files Text files to be parsed and to be used in order to add nodes
     */
    public void addTiles (ArrayList<File> files) {
        // Add tiles using txt files
    }

    /**
     * Returns the tiles a specific player can use.
     * @param player Player whose available tiles are to be returned.
     * @return ArrayList of Terrain Tiles that this specific player can use.
     */
    public ArrayList<TerrainTile> playerCanUseTiles (Player player) {
        // Returns arraylist of tiles player can use
        return null;
    }
}
