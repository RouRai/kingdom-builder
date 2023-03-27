package logic.gameLogic.objectiveScoring;

import logic.gameLogic.Board;
import logic.gameLogic.Player;

public interface Objective {

    int scoreObjective (Player player, Board board);
}
