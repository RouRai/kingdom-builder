package logic.tiles;

import logic.game.Player;
import logic.constantFolder.TerrainEnum;

public class TerrainTile {

    private final TerrainEnum type;
    private Player owner;

    public TerrainTile (TerrainEnum type) {
        this.type = type;
    }

    public TerrainEnum getType () {
        return type;
    }

    public Player getOwner () {
        return owner;
    }

    public void setOwner (Player settler) {
        owner = settler;
    }
}
