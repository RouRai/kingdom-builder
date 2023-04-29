package logic.gameLogic.objectiveScoring;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

import java.util.ArrayList;
import java.util.HashMap;

public class Knight implements Objective{
    @Override
    public int scoreObjective(Player player, Board board) {
        HashMap<Integer, Integer> knightRows = new HashMap<>();
        ArrayList<Settlement> playerSettlements= player.getSettlements();
        for(Settlement settlement: playerSettlements){
            if(!knightRows.containsKey(settlement.getTrueRow())){
                knightRows.put(settlement.getTrueRow(), 1);
            } else{
                knightRows.put(settlement.getTrueRow(), knightRows.get(settlement.getTrueRow()) + 1);
            }
        }
        int longestRow = Integer.MIN_VALUE;
        for(int length: knightRows.keySet()){
            if(longestRow < length){
                longestRow = length;
            }
        }
        return 2 * longestRow;
    }
}
