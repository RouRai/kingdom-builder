package datastructures.baseDatastructures;

import java.util.ArrayList;

public class Node {

    private final ArrayList<Node> adjacentNodes;

    public Node () {
        adjacentNodes = new ArrayList<>();
    }

    /**
     * @return Adjacency list of this specific node
     */
    public ArrayList<Node> getAdjacentNodes () {
        return adjacentNodes;
    }

    /**
     * Adds a new node to the adjacency list that can be accessed.
     * @param newAdjacentNode The node that is to be added to the adjacency list
     */
    public void addAdjacentNode (Node newAdjacentNode) {
        adjacentNodes.add(newAdjacentNode);
    }

    public void removeAdjacentNode (Node eliminateNode) {
        adjacentNodes.remove(eliminateNode);
    }
}
