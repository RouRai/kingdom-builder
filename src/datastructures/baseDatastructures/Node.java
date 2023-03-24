package datastructures.baseDatastructures;

import java.util.ArrayList;

public class Node<T> {

    private final ArrayList<T> adjacentNodes;

    public Node () {
        adjacentNodes = new ArrayList<>();
    }

    /**
     * @return Adjacency list of this specific node
     */
    public ArrayList<T> getAdjacentNodes () {
        return adjacentNodes;
    }

    /**
     * Adds a new node to the adjacency list that can be accessed.
     * @param newAdjacentNode The node that is to be added to the adjacency list
     */
    public void addAdjacentNode (T newAdjacentNode) {
        adjacentNodes.add(newAdjacentNode);
    }

    public void removeAdjacentNode (T eliminateNode) {
        adjacentNodes.remove(eliminateNode);
    }
}
