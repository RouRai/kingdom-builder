package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;

public class Graph{

    private final Node origin;

    public Graph(Node origin) {
        this.origin = origin;
    }

    /**
     * Returns the origin of the graph
     * @return origin
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * Adds an edge between two vertices given the direction each are to correlate with
     * @param vertexOne The first vertex
     * @param vertexTwo The second vertex
     * @param directionOne The direction of the second vertex relative to the first vertex
     * @param directionTwo The direction of the first vertex relative to the second vertex
     */
    public void addEdge (Node vertexOne, Node vertexTwo, DirectionEnum directionOne, DirectionEnum directionTwo) {
        vertexOne.addAdjacentNode(directionTwo, vertexTwo);
        vertexTwo.addAdjacentNode(directionOne, vertexOne);
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
