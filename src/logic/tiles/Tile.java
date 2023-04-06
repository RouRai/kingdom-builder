package logic.tiles;

import logic.constantFolder.ActionEnum;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store data relevant to all types of tiles.
 * @param <T>
 */

public class Tile<T extends Enum> {
    private final T type;

    public Tile (T type) {
        this.type = type;
    }

    public T getType () {
        return type;
    }
}
