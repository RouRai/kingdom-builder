package logic.tiles;

import logic.constantFolder.ActionEnum;

public class ActionTile {

    private final ActionEnum type;

    public ActionTile (ActionEnum type) {
        this.type = type;
    }

    public ActionEnum getType() {
        return type;
    }
}
