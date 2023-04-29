package logic.tiles.actionAdjacencies.placeSettlements;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.actionAdjacencies.ActionAdjacency;

import java.util.ArrayList;

public class Tower implements ActionAdjacency {

    private final Board board;
    private final Player player;
    private final ArrayList<TerrainNode> edgeAdjacentToSettlement;
    private final ArrayList<TerrainNode> allEdgeNodes;
    private boolean settlementAdjacentToEdge;

    public Tower(Board board, Player player) {
        this.board = board;
        this.player = player;
        edgeAdjacentToSettlement = new ArrayList<>();
        allEdgeNodes = new ArrayList<>();
    }


    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        edgeNodesAdjacentToSettlement();
        if (settlementAdjacentToEdge)
            return edgeAdjacentToSettlement;
        return allEdgeNodes;
    }

    private void edgeNodesAdjacentToSettlement() {
        TerrainNode[][] terrainNodes = board.getTerrainBoard().getBoardMatrix();
        for(int row = 0; row < terrainNodes.length; row++) {
            for (int column = 0; column < terrainNodes[row].length; column++) {
                decideAdditionAction(row, column, terrainNodes);
            }
        }
    }

    private void decideAdditionAction (int row, int column, TerrainNode[][] terrainNodes) {
        if (isOnEdge(row, column) && hasSettlementNearNode(terrainNodes[row][column])) {
            settlementAdjacentToEdge = true;
            edgeAdjacentToSettlement.add(terrainNodes[row][column]);
        }
        if (isOnEdge(row, column)) {
            allEdgeNodes.add(terrainNodes[row][column]);
        }
    }

    private boolean isOnEdge (int row, int column) {
        return row == 0 || column == 0 || row == 19 || column == 19;
    }
    private boolean hasSettlementNearNode(TerrainNode node) {
        for (TerrainNode adjacentNodes : node.getAdjacentNodes().values()) {
            if (adjacentNodes.getTile().getSettlement().getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }
}
