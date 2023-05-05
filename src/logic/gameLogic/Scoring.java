package logic.gameLogic;

import logic.cards.ObjectiveCard;
import logic.objectiveScoring.Objective;
import logic.objectiveScoring.objectives.*;

import java.util.ArrayList;

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
            //System.out.println(scores.toString());
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
}
