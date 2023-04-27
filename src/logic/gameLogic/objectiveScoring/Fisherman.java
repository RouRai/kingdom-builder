package logic.gameLogic.objectiveScoring;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

import java.util.ArrayList;

public class Fisherman implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        int score = 0;
        ArrayList<Settlement> settlements = player.getSettlements();
        for(Settlement settle: settlements){
            score += checkAdjacency(settle);
        }
        return score;
    }
    private int checkAdjacency(Settlement settle){
        for(TerrainNode terrainNode: settle.getLocation().getAdjacentNodes().values()){
            if (terrainNode.getType() == TerrainEnum.WATER) {
                return 1;
            }
        }
    }
}
