package logic.tiles;

import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Player;

import java.util.HashSet;

public class CityTile extends Tile<TerrainEnum> {

    private HashSet<Player> adjacentPlayers;

    public CityTile() {
        super(TerrainEnum.CITY);
        adjacentPlayers = new HashSet<>();
    }

    public void addAdjacentPlayer (Player player) {
        adjacentPlayers.add(player);
    }

    public void removeAdjacentPlayer (Player player) {
        adjacentPlayers.remove(player);
    }

    public HashSet<Player> getAdjacentPlayers () {
        return adjacentPlayers;
    }

}
