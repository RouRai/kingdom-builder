package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;

public class Graph{

    private final Node origin;

    public Graph(Node origin) {
        this.origin = origin;
    }

    public Node getOrigin() {
        return origin;
    }

    public void addEdge (Node vertexOne, Node vertexTwo, DirectionEnum directionOne, DirectionEnum directionTwo) {
        vertexOne.addAdjacentNode(directionTwo, vertexTwo);
        vertexTwo.addAdjacentNode(directionOne, vertexOne);
    }

    public void removeEdge (Node vertexOne, Node vertexTwo, DirectionEnum directionOne, DirectionEnum directionTwo) {
        vertexOne.getAdjacentNodes().remove(directionTwo);
        vertexTwo.getAdjacentNodes().remove(directionOne);
    }

}
