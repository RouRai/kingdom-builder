package logic.objectiveScoring.objectives;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.objectiveScoring.Objective;
import logic.placeables.Settlement;

import java.util.ArrayList;

public class Farmer implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        // bro
        ArrayList<Settlement> quad0 = new ArrayList<>();
        ArrayList<Settlement> quad1 = new ArrayList<>();
        ArrayList<Settlement> quad2 = new ArrayList<>();
        ArrayList<Settlement> quad3 = new ArrayList<>();
        for(Settlement settle: player.getSettlements()){
            switch (settle.getQuadrantNumber()) {
                case 0 -> {
                    quad0.add(settle);
                }
                case 1 -> {
                    quad1.add(settle);
                }
                case 2 -> {
                    quad2.add(settle);
                }
                case 3 -> {
                    quad3.add(settle);
                }
            }
        }
        return 3 * Math.min(Math.min(quad0.size(), quad1.size()), Math.min(quad2.size(), quad3.size()));
    }
}
