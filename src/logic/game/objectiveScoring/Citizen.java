package logic.game.objectiveScoring;

import logic.game.Board;
import logic.game.Player;

public class Citizen implements Objective {
    @Override
    public int scoreObjective(Player player, Board board) {
        return 0;
    }
}
