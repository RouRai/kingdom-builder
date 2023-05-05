package logic.objectiveScoring.objectives;

import logic.gameLogic.Game;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

import java.util.ArrayList;
import java.util.HashMap;

public class Lord {

    public void scoreObjective(Game game) {
        for (int i = 0; i < 4; i++) {
            goThroughQuadrant(game.getAllPlayers(), i);
        }
    }

    private void goThroughQuadrant (ArrayList<Player> players, int quadrant) {
        HashMap<Player, Integer> scoresForQuadrant = new HashMap<>();
        for (Player player : players) {
            scoresForQuadrant.put(player, goThroughSettlementInQuadrant(player, quadrant));
        }
        Player currentMaxPlayer = getMaxPlayer(scoresForQuadrant);
        currentMaxPlayer.addPoints(12);
        scoresForQuadrant.remove(currentMaxPlayer);
        currentMaxPlayer = getMaxPlayer(scoresForQuadrant);
        currentMaxPlayer.addPoints(6);
    }

    private int goThroughSettlementInQuadrant (Player player, int quadrant) {
        int sum = 0;
        for (Settlement settlement : player.getSettlements()) {
            if (settlement.getQuadrantNumber() == quadrant) {
                sum++;
            }
        }
        return sum;
    }

    private Player getMaxPlayer (HashMap<Player, Integer> playerScoreForQuadrant) {
        Player maxPlayer = null;
        int max = Integer.MIN_VALUE;
        for (Player player : playerScoreForQuadrant.keySet()){
            if (Math.max(max, playerScoreForQuadrant.get(player)) != max) {
                maxPlayer = player;
                max = playerScoreForQuadrant.get(player);
            }
        }
        return maxPlayer;
    }
}
