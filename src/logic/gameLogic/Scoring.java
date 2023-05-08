package logic.gameLogic;

import datastructures.gameDatastructures.boardNodes.CityNode;
import logic.cards.ObjectiveCard;
import logic.objectiveScoring.Objective;
import logic.objectiveScoring.objectives.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This high-level class is mainly used to perform scoring based on a player's actions and update their
 * amount of points at the end of the game.
 */

public class Scoring {

    private final ArrayList<Player> players;
    private final ArrayList<ObjectiveCard> objectives;
    private final Board board;
    private final Game game;

    public Scoring (ArrayList<Player> players, ArrayList<ObjectiveCard> objectives, Board board, Game game) {
        this.players = players;
        this.objectives = objectives;
        this.board = board;
        this.game = game;
        calculateScores();
        analyzeCityScoring();
    }

    /**
     * Calculates the score of each player using the objectives given and updates each player score
     */
    private void calculateScores () {
        // Calculate the scores of players in the list
        for (Player p: players){
            ArrayList<Integer> scores = new ArrayList<>();
            for (ObjectiveCard card: objectives)
                scores.add(scoreCard(p, card));
            p.setScores(scores);
        }
    }

    /**
     * Scores each player individually and updates their score
     * @param player Player whose score is to be updated
     */
    private int scoreCard (Player player, ObjectiveCard card) {
        int score = 0;
        Objective objScorer = null;
        Lord lord = null;
        switch(card.type()){
            case LORD -> lord = new Lord();
            case FARMER ->objScorer = new Farmer();
            case MINER -> objScorer = new Miner();
            case KNIGHT -> objScorer = new Knight();
            case WORKER -> objScorer = new Worker();
            case FISHERMAN -> objScorer = new Fisherman();
            case DISCOVERER -> objScorer = new Discoverer();
        }
        if (objScorer != null)
            score = objScorer.scoreObjective(player, board);
        else if (lord != null)
            lord.scoreObjective(game);
        return score;
    }

    public ArrayList<ObjectiveCard> getObjectives() {
        return objectives;
    }

    private void analyzeCityScoring () {
        HashMap<Player, Integer> scores = new HashMap<>();
        for (Player player : players) {
            scores.put(player, 0);
        }

        for (CityNode[] row : board.getCityBoard().getBoardMatrix()) {
            for (CityNode cityNode : row) {
                if (cityNode == null)
                    continue;
                scoreSingleCity(cityNode, scores);
            }
        }

        for (Player player : players) {
            player.getScores().add(0, scores.get(player));
            ArrayList<Integer> newScores = new ArrayList<>();
            for (ObjectiveCard card: objectives)
                newScores.add(scoreCard(player, card));
            //player.setScores(scores);
            for(int i = 0; i < 3; i++){
                player.getScores().set(i + 1, newScores.get(i));
            }
        }
    }

    private void scoreSingleCity (CityNode cityNode, HashMap<Player, Integer> scores) {
        for (Player player : cityNode.getAdjacentPlayers()) {
            scores.put(player, scores.get(player) + 3);
        }
    }
}
