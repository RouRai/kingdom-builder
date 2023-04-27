package logic.gameLogic.objectiveScoring;

import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

public class Hermit implements Objective{
    @Override
    public int scoreObjective(Player player, Board board) {
        return 0;
    }
}
