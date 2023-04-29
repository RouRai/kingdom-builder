package logic.tiles;

import logic.constantFolder.ActionEnum;
import logic.gameLogic.Player;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store and track data relevant to Action Tiles throughout the game.
 */

public class ActionTile extends Tile<ActionEnum> {
    private int count;
    private ArrayList<Player> players;

    public ActionTile (ActionEnum type) {
        super(type);
        players = new ArrayList<>();
        count = 2;
    }

    public int getCount () {
        return count;
    }

    public void takeTile (Player p) {
        if (!players.contains(p)) {
            count--;
        }
        players.add(p);
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    
}
