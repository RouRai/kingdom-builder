package logic.tiles;

import logic.gameLogic.Player;
import logic.constantFolder.TerrainEnum;

public class TerrainTile extends Tile<TerrainEnum> {
    private Player owner;

    public TerrainTile (TerrainEnum type) {
        super(type);
    }

    public Player getOwner () {
        return owner;
    }

    public void setOwner (Player settler) {
        owner = settler;
    }
}
