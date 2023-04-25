package datastructures.gameDatastructures.boardNodes;

import datastructures.baseDatastructures.Node;
import logic.constantFolder.ActionEnum;
import logic.tiles.ActionTile;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store Action Tiles, which grant the user abilities that can be used in game
 * in order to move settlements or place more. This holds an ActionTile object that holds the attributes
 * common to all action tiles. The tile and its type can also be retrieved.
 */
public class ActionNode extends Node<ActionNode> {

    private final ActionTile tile;

    public ActionNode (ActionEnum type, int row, int column) {
        super(row, column);
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
