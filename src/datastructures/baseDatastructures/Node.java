package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;
import logic.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 * Forms a basic node that can carry data of its adjacent nodes. This type of node relies on direction
 * in order to be able to tell where each adjacent node is. These directions are represented through constants
 * in the Direction Enum, found in the directory "logic/constantFolder/DirectionEnum". This Node also does not
 * hold its own data, and requires for it to be extended through another class in order to do so.
 */
public class Node {

    private final HashMap<DirectionEnum, Node> adjacentNodes;
    private Enum type;

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
     * @param direction The direction in which the node is to be added to the adjacency Hash Map.
     */
    public void addAdjacentNode (DirectionEnum direction, Node newAdjacentNode) {
        adjacentNodes.put(direction, newAdjacentNode);
    }
}
