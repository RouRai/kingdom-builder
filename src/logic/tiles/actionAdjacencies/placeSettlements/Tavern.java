package logic.tiles.actionAdjacencies.placeSettlements;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.constantFolder.Constants;
import logic.constantFolder.DirectionEnum;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.actionAdjacencies.ActionAdjacency;
import logic.tiles.actionAdjacencies.ActionProcess;

import java.util.ArrayList;
import java.util.HashSet;

public class Tavern extends ActionProcess implements ActionAdjacency {
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
        HashSet<TerrainNode> validNodes = new HashSet<>();
        for(int row = 0; row < 20; row++){
            for(int column = 0; column < 20; column++){
                TerrainNode currentNode = board.getTerrainBoard().getBoardMatrix()[row][column];
                if (currentNode != null)
                    validNodes.addAll(processDirections(board.getTerrainBoard().getBoardMatrix()[row][column]));
            }
        }
        return new ArrayList<>(validNodes);
    }

    private HashSet<TerrainNode> processDirections (TerrainNode node) {
        HashSet<TerrainNode> nodes = new HashSet<>();
        nodes.addAll(getEnds(node, DirectionEnum.LEFT));
        nodes.addAll(getEnds(node, DirectionEnum.RIGHT));
        nodes.addAll(getEnds(node, DirectionEnum.TOP_LEFT));
        nodes.addAll(getEnds(node, DirectionEnum.TOP_RIGHT));
        nodes.addAll(getEnds(node, DirectionEnum.BOTTOM_LEFT));
        nodes.addAll(getEnds(node, DirectionEnum.BOTTOM_RIGHT));
        return nodes;
    }

    private HashSet<TerrainNode> getEnds (TerrainNode firstNode, DirectionEnum direction) {
        HashSet<TerrainNode> validNodes = new HashSet<>();
        if (!isFullRowOfThree(firstNode, direction))
            return validNodes;
        validNodes.add(firstNode.getAdjacentNodes().get(Constants.getOppositeDirection(direction)));
        validNodes.add(secondEnd(firstNode, direction));
        validNodes.remove(null);
        return validNodes;
    }

    private boolean isFullRowOfThree (TerrainNode firstNode, DirectionEnum direction) {
        if (firstNode.getAdjacentNodes().get(direction) == null) {
            return false;
        }
        if (firstNode.getAdjacentNodes().get(direction).getType() == null) {
            return false;
        }
        TerrainNode secondNode = firstNode.getAdjacentNodes().get(direction);
        TerrainNode thirdNode = secondEnd(firstNode, direction);
        if(firstNode.getTile().getSettlement() == null || secondNode.getTile().getSettlement() == null || thirdNode.getTile().getSettlement() == null){
            return false;
        }
        boolean firstNodeSettlementOfPlayer = firstNode.getTile().getSettlement().getPlayer().equals(player);
        boolean secondNodeSettlementOfPlayer = secondNode.getTile().getSettlement().getPlayer().equals(player);
        boolean thirdNodeSettlementOFPlayer = thirdNode.getTile().getSettlement().getPlayer().equals(player);

        return firstNodeSettlementOfPlayer && secondNodeSettlementOfPlayer && thirdNodeSettlementOFPlayer;
    }

    private TerrainNode secondEnd (TerrainNode node, DirectionEnum direction) {
        return node.getAdjacentNodes().get(direction).getAdjacentNodes().get(direction);
    }
}