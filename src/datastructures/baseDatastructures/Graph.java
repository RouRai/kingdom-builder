package datastructures.baseDatastructures;

public class Graph<T extends Node<T>, S extends Node<S>>{

    private final T origin;

    public Graph(T origin) {
        this.origin = origin;
    }

    public T getOrigin() {
        return origin;
    }

}
