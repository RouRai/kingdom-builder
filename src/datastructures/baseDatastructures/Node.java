package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;
import logic.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private final HashMap<DirectionEnum, Node> adjacentNodes;

    public Node () {
       adjacentNodes = new HashMap<>();
    }

    /**
     * @return Adjacency list of this specific node
     */
    public HashMap<DirectionEnum, Node> getAdjacentNodes () {
        return adjacentNodes;
    }

    /**
     * Adds a new node to the adjacency list that can be accessed.
     * @param newAdjacentNode The node that is to be added to the adjacency list
     */
    public void addAdjacentNode (DirectionEnum direction, Node newAdjacentNode) {
        adjacentNodes.put(direction, newAdjacentNode);
    }
}
