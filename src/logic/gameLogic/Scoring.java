package logic.gameLogic;

import logic.cards.ObjectiveCard;
import logic.constantFolder.ObjectiveEnum;
import logic.placeables.Settlement;

import java.util.ArrayList;
import java.util.HashSet;

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

    public Scoring (ArrayList<Player> players, ArrayList<ObjectiveCard> objectives, Board board) {
        this.players = players;
        this.objectives = objectives;
        this.board = board;
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
    private int scoreCard (Player player, ObjectiveCard card) {
        int score = 0;
        if (card.type() == ObjectiveEnum.FISHERMAN){

        }
        else if (card.type() ==  ObjectiveEnum.CITIZEN){

        }
        return score;
    }
    // checking the number of connected
    private HashSet<Settlement> numConnected (Player player, Settlement origin){

        return null;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<ObjectiveCard> getObjectives() {
        return objectives;
    }
}
