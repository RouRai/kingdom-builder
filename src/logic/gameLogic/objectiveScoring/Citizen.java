package logic.gameLogic.objectiveScoring;

import logic.gameLogic.Board;
import logic.gameLogic.Player;

public class Citizen implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        return 0;
    }
}
