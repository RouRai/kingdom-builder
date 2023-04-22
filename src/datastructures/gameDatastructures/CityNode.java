package datastructures.gameDatastructures;

import logic.gameLogic.Player;

import java.util.HashMap;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to represent City Hexes. These are used in order to verify if a player should
 * receive points from this city, and if so, verify that they are adjacent and that they have obtained
 * their points.
 */

public class CityNode {

    private final HashMap<Player, Boolean> gotPoints;

    public CityNode () {
        gotPoints = new HashMap<>();
    }

    public boolean playerIsAdjacent (Player p) {
        return gotPoints.get(p);
    }
}
