package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This is the basic Graph class. This class only stores an origin and has Depth-First Search as its default
 * search algorithm. This is meant to store all the adjacent nodes in a graph in order to make traversal
 * and adjacency simpler to handle. This class is not to be used in a basic fashion, and relies on being extended.
 */
public abstract class Graph{

    private Node origin;

    public Graph() {}

    /**
     * Returns the origin of the graph
     * @return origin
     */
    public Node getOrigin() {
        return origin;
    }

    public void setOrigin (Node origin) {
        this.origin = origin;
    }

    /**
     * Adds an edge between two vertices given the direction each are to correlate with
     * @param vertexOne The first vertex
     * @param vertexTwo The second vertex
     * @param directionOne The direction of the second vertex relative to the first vertex
     * @param directionTwo The direction of the first vertex relative to the second vertex
     */
    public void addEdge (Node vertexOne, Node vertexTwo, DirectionEnum directionOne, DirectionEnum directionTwo) {
        vertexOne.addAdjacentNode(directionOne, vertexTwo);
        vertexTwo.addAdjacentNode(directionTwo, vertexOne);
    }

    /**
     * Removes an edge between two vertices
     * @param vertexOne The first vertex
     * @param vertexTwo The second vertex
     * @param directionOne The direction of the second vertex relative to the first vertex
     * @param directionTwo The direction of the first vertex relative to the second vertex
     */
    public void removeEdge (Node vertexOne, Node vertexTwo, DirectionEnum directionOne, DirectionEnum directionTwo) {
        vertexOne.getAdjacentNodes().remove(directionTwo);
        vertexTwo.getAdjacentNodes().remove(directionOne);
    }

}
