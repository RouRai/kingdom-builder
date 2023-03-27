package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;
import logic.cards.ObjectiveCard;

import java.util.ArrayList;

public class Scoring {

    private ArrayList<Player> players;
    private BoardGraph board;
    private ArrayList<ObjectiveCard> objectives;

    public Scoring (ArrayList<Player> players, BoardGraph board, ArrayList<ObjectiveCard> objectives) {
        this.players = players;
        this.board = board;
        this.objectives = objectives;
    }

    /**
     * Calculates the score of each player using the objectives given and updates each player score
     */
    private void calculateScores () {
        // Calculate the scores of players in the list
    }

    /**
     * Scores each player individually and updates their score
     * @param player Player whose score is to be updated
     */
    private void scorePlayer (Player player) {
        //calculate score of specific player and store it
    }
}
