package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;
import logic.cards.ObjectiveCard;

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
    private final BoardGraph board;
    private final ArrayList<ObjectiveCard> objectives;

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public BoardGraph getBoard() {
        return board;
    }

    public ArrayList<ObjectiveCard> getObjectives() {
        return objectives;
    }
}
