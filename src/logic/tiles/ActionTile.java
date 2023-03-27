package logic.tiles;

import logic.constantFolder.ActionEnum;

public class ActionTile {

    private final ActionEnum type;
    private int count;

    public ActionTile (ActionEnum type) {
        this.type = type;
        count = 2;
    }

    public ActionEnum getType() {
        return type;
    }

    public int getCount () {
        return count;
    }

    public void takeTile () {
        count--;
    }

    public void giveTile () {
        count++;
    }
}
