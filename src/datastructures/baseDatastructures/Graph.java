package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;

public class Graph<T extends Node, S extends Node>{

    private final T origin;

    public Graph(T origin) {
        this.origin = origin;
    }

    public T getOrigin() {
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
