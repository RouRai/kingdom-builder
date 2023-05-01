package logic.tiles.actionAdjacencies.placeSettlements;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.actionAdjacencies.ActionAdjacency;

import java.util.ArrayList;

public class Farm implements ActionAdjacency {
    private final Board board;
    private final Player player;

    public Farm(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        return board.regularCanUseTiles(player, new TerrainCard(TerrainEnum.GRASS, null));
    }
}
