package logic.gameLogic.objectiveScoring;

import logic.gameLogic.Board;
import logic.gameLogic.Player;

public interface Objective {

    /**
     * Scores and updates the player using the algorithm for a specific objective.
     * @param player The player whose score is to be updated.
     * @param board The game board
     */
    int scoreObjective (Player player, Board board);
}
