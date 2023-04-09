package logic.tiles;

import logic.gameLogic.Player;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class stores data relevant to specifically Terrain Tiles.
 */
public class TerrainTile extends Tile<TerrainEnum> {
    private Player owner;
    private Settlement settlement;

    public TerrainTile (TerrainEnum type) {
        super(type);
    }

    public Player getOwner () {
        return owner;
    }

    public void setOwner (Player settler) {
        owner = settler;
        settlement = new Settlement(settler.getPlayerNumber());
    }

    public void removeOwner () {
        owner = null;
        settlement = null;
    }
}
