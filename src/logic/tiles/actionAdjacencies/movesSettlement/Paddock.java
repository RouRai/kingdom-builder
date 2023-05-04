package logic.tiles.actionAdjacencies.movesSettlement;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.constantFolder.Constants;
import logic.constantFolder.DirectionEnum;
import logic.constantFolder.TerrainEnum;
import logic.tiles.actionAdjacencies.ActionAdjacency;
import logic.tiles.actionAdjacencies.ActionProcess;

import java.util.ArrayList;

public class Paddock extends ActionProcess implements ActionAdjacency {

    private final TerrainNode currentNode;

    public Paddock(TerrainNode currentNode) {
        this.currentNode = currentNode;
    }


    /**
     * Returns the valid nodes for settlement placement when using an Action Tile.
     * @return <code>ArrayList</code> of TerrainNodes that can be settled upon.
     */
    @Override
    public ArrayList<TerrainNode> getValidNodes() {
        ArrayList<TerrainNode> validNodes = new ArrayList<>();
        for (DirectionEnum direction : Constants.allDirections) {
            boolean validTerrainNodeType = getNodeTwoFar(currentNode, direction).getType() != TerrainEnum.MOUNTAIN || getNodeTwoFar(currentNode, direction).getType() != TerrainEnum.WATER;
            if (getNodeTwoFar(currentNode, direction) != null && validTerrainNodeType) {
                validNodes.add(getNodeTwoFar(currentNode, direction));
            }
        }
        return validNodes;
    }

    public TerrainNode getNodeTwoFar (TerrainNode node, DirectionEnum directionEnum) {
        if (node.getAdjacentNodes().get(directionEnum) == null) {
            return null;
        }
        return node.getAdjacentNodes().get(directionEnum).getAdjacentNodes().get(directionEnum);
    }
}
