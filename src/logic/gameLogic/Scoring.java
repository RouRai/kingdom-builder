package logic.gameLogic;

import datastructures.gameDatastructures.BoardGraph;

import java.util.ArrayList;

public class Scoring {

    private ArrayList<Player> players;
    private BoardGraph board;

    public Scoring (ArrayList<Player> players, BoardGraph board) {
        this.players = players;
        this.board = board;
    }

    private void calculateScores () {
        // Calculate the scores of players in the list
    }

    private void scorePlayer (Player player) {
        //calculate score of specific player and store it
    }
}
