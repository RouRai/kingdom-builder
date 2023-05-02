package logic.objectiveScoring.objectives;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.objectiveScoring.Objective;
import logic.placeables.Settlement;

import java.util.ArrayList;
import java.util.HashMap;

public class Knight implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        HashMap<Integer, Integer> knightRows = new HashMap<>();
        setUpKnightRows(knightRows, player);
        int longestRow = Integer.MIN_VALUE;
        for(int length: knightRows.keySet()){
            longestRow = Math.max(longestRow, length);
        }
        return 2 * longestRow;
    }

    private void setUpKnightRows (HashMap<Integer, Integer> knightRows, Player player) {
        ArrayList<Settlement> playerSettlements= player.getSettlements();
        for(Settlement settlement: playerSettlements){
            if(!knightRows.containsKey(settlement.getTrueRow())){
                knightRows.put(settlement.getTrueRow(), 1);
                continue;
            }
            knightRows.put(settlement.getTrueRow(), knightRows.get(settlement.getTrueRow()) + 1);
        }
    }
}
