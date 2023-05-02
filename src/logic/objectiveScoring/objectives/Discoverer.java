package logic.objectiveScoring.objectives;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.objectiveScoring.Objective;
import logic.placeables.Settlement;

import java.util.ArrayList;
import java.util.HashSet;

public class Discoverer implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        HashSet<Integer> rows = new HashSet<>();
        ArrayList<Settlement> settlements = player.getSettlements();
        for(Settlement settle : settlements){
            rows.add(settle.getTrueRow());
        }
        return rows.size();
    }
}
