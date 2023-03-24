package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Node;
import logic.constantFolder.ActionEnum;
import logic.tiles.ActionTile;

public class ActionNode extends Node<ActionNode> {

    private final ActionEnum type;

    private final ActionTile tile;

    public ActionNode (ActionEnum type) {
        this.type = type;
        tile = new ActionTile(type);
    }

    public ActionEnum getType () {
        return type;
    }

    public ActionTile getTile () {
        return tile;
    }


}
