package logic.gameLogic;

import logic.cards.ObjectiveCard;
import logic.constantFolder.Constants;
import logic.constantFolder.ObjectiveEnum;
import logic.gameLogic.objectiveScoring.*;
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
        //System.out.println(card.type());
        switch(card.type()){
            //case LORD -> {objScorer = new Lord();}
            case FARMER ->{objScorer = new Farmer();}
            case MINER -> {objScorer = new Miner();}
            //case HERMIT -> {objScorer = new Hermit();}
            case KNIGHT -> {objScorer = new Knight();}
            case WORKER -> {objScorer = new Worker();}
            //case CITIZEN -> {objScorer = new Citizen();}
            //case MERCHANT -> {objScorer = new Merchant();}
            case FISHERMAN -> {objScorer = new Fisherman();}
            case DISCOVERER -> {objScorer = new Discoverer();}
        }
        if (objScorer != null)
            score = objScorer.scoreObjective(player, board);
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
