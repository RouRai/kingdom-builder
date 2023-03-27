package datastructures.baseDatastructures;

public class Graph<T extends Node, S extends Node>{

    private final T origin;

    public Graph(T origin) {
        this.origin = origin;
    }

    public T getOrigin() {
        return origin;
    }

    public void addEdge (Node vertexOne, Node vertexTwo) {
        vertexOne.getAdjacentNodes().add(vertexTwo);
        vertexTwo.getAdjacentNodes().add(vertexOne);
    }

    public void removeEdge (Node vertexOne, Node vertexTwo) {
        vertexOne.getAdjacentNodes().remove(vertexTwo);
        vertexTwo.getAdjacentNodes().remove(vertexOne);
    }

}
