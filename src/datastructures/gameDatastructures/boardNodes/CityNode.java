package datastructures.gameDatastructures.boardNodes;

import datastructures.baseDatastructures.Node;
import logic.gameLogic.Player;
import logic.tiles.CityTile;

import java.util.HashMap;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to represent City Hexes. These are used in order to verify if a player should
 * receive points from this city, and if so, verify that they are adjacent and that they have obtained
 * their points.
 */

public class CityNode extends Node<CityNode> {

    private final HashMap<Player, Boolean> gotPoints;

    public CityNode (int row, int column) {
        super(row, column);
        gotPoints = new HashMap<>();
    }

    public boolean playerIsAdjacent (Player player) {
        return gotPoints.get(player);
    }
}
