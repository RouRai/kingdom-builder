package logic.game;

import logic.tiles.TerrainTile;

public class Board {

    private TerrainTile[][] board;

    public Board () {
        board = new TerrainTile[20][20];
    }
}
