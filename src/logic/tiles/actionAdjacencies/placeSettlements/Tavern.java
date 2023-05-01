package logic.tiles.actionAdjacencies.placeSettlements;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.actionAdjacencies.ActionAdjacency;

import java.util.ArrayList;

public class Tavern implements ActionAdjacency {
    private final Board board;
    private final Player player;

    public Tavern(Board board, Player player) {
        this.board = board;
        this.player = player;
    }
    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     *
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; r++){

            }
        }
        return null;
    }

    //https://code-with-me.global.jetbrains.com/TQj0J8I2xw83Rg-fitfb3w#p=IC&fp=2AD9154C027F93D81993DDF7D68E7842B3469E095D53B6EA4CDF170CC1D108DA
}
