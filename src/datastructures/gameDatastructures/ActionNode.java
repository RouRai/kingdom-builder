package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Node;
import logic.constantFolder.ActionEnum;
import logic.tiles.ActionTile;

public class ActionNode extends Node {

    private final ActionTile tile;

    public ActionNode (ActionEnum type) {
        tile = new ActionTile(type);
    }

    /**
     * Returns what type of action tile this is using one of the Action Enums
     * @return Type of action tile
     */
    public ActionEnum getType () {
        return tile.getType();
    }

    /**
     * Returns the tile object stored inside the node
     * @return Tile associated with this node.
     */
    public ActionTile getTile () {
        return tile;
    }


}
