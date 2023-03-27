package logic.game.objectiveScoring;

import logic.game.Board;
import logic.game.Player;

public interface Objective {

    int scoreObjective (Player player, Board board);
}
