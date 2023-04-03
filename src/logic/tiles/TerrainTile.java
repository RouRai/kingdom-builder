package logic.tiles;

import logic.gameLogic.Player;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;

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
        settlement = new Settlement(settler.getPlayerColor());
    }

    public void removeOwner () {
        owner = null;
        settlement = null;
    }
}
