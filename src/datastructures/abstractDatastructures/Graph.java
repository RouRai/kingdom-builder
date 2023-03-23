package datastructures.abstractDatastructures;

public class Graph<T extends Node<T>>{

    private final T origin;

    public Graph(T origin) {
        this.origin = origin;
    }

    public T getOrigin() {
        return origin;
    }
}
