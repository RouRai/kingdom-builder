package logic.gameLogic.objectiveScoring;

import datastructures.gameDatastructures.boardNodes.CityNode;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

public class Worker implements Objective{
    @Override
    public int scoreObjective(Player player, Board board) {
        int score = 0;
        for(Settlement settle : player.getSettlements()){
            score += checkAdjacencyToCityTile(settle.getLocation(), board);
        }
        return score;
    }
    private int checkAdjacencyToCityTile (TerrainNode terrainNode, Board board) {
        int[] rows = new int[6];
        int[] columns = new int[6];

        int index = 0;
        for (TerrainNode adjacentNode : terrainNode.getAdjacentNodes().values()) {
            rows[index] = adjacentNode.getTrueRow();
            columns[index] = adjacentNode.getTrueColumn();
            index++;
        }

        return analyzeAdjacentNodesForCityTile(rows, columns, board);
    }

    private int analyzeAdjacentNodesForCityTile (int[] rows, int[] columns, Board board) {
        int score = 0;
        for (int index = 0; index < rows.length; index++) {
            CityNode adjacentCityNode = board.getCityBoard().getBoardMatrix()[rows[index]][columns[index]];
            boolean terrainAdjacentToCityNode = adjacentCityNode != null;
            if (terrainAdjacentToCityNode) {
                score++;
            }
        }
        return score;
    }
}
