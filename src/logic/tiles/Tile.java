package logic.tiles;

public class Tile<T extends Enum> {

    private final T type;

    public Tile (T type) {
        this.type = type;
    }

    public T getType () {
        return type;
    }
}
