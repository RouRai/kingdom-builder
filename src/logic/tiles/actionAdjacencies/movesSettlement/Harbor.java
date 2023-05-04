package logic.tiles.actionAdjacencies.movesSettlement;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.actionAdjacencies.ActionAdjacency;
import logic.tiles.actionAdjacencies.ActionProcess;

import java.util.ArrayList;

public class Harbor extends ActionProcess implements ActionAdjacency {

    private final Board board;
    private final Player player;

    public Harbor(Board board, Player player) {
        this.board = board;
        this.player = player;
    }


    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        return board.regularCanUseTiles(player, new TerrainCard(TerrainEnum.WATER, null));
    }
}
