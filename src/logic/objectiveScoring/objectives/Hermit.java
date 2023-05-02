package logic.objectiveScoring.objectives;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.objectiveScoring.Objective;

public class Hermit implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        return 0;
    }
}
