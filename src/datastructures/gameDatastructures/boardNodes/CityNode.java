package datastructures.gameDatastructures.boardNodes;

import datastructures.baseDatastructures.Node;
import logic.gameLogic.Player;

import java.util.HashSet;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to represent City Hexes. These are used in order to verify if a player should
 * receive points from this city, and if so, verify that they are adjacent and that they have obtained
 * their points.
 */

public class CityNode extends Node<CityNode> {

    public HashSet<Player> getAdjacentPlayers() {
        return adjacentPlayers;
    }

    private final HashSet<Player> adjacentPlayers;

    public CityNode (int row, int column) {
        super(row, column);
        adjacentPlayers = new HashSet<>();
    }

    public void addAdjacentPlayer (Player player) {
        adjacentPlayers.add(player);
    }
}
