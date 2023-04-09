package logic.tiles;

import game.Constants;
import logic.constantFolder.ActionEnum;

import java.awt.image.BufferedImage;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store and track data relevant to Action Tiles throughout the game.
 */

public class ActionTile extends Tile<ActionEnum> {
    private int count;
    private BufferedImage front, back;

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
}
