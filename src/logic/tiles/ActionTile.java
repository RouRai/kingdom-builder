package logic.tiles;

import logic.constantFolder.ActionEnum;

public class ActionTile extends Tile<ActionEnum> {
    private int count;

    public ActionTile (ActionEnum type) {
        super(type);
        count = 2;
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
