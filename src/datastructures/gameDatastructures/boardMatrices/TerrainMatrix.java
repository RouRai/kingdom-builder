package datastructures.gameDatastructures.boardMatrices;

import datastructures.gameDatastructures.boardNodes.TerrainNode;
import files.mainMakers.TerrainMaker;
import logic.constantFolder.DirectionEnum;
import logic.tiles.TerrainTile;

import java.util.ArrayList;

public class TerrainMatrix {
    private final TerrainNode[][] boardMatrix;

    public TerrainMatrix (ArrayList<TerrainMaker> quadrants) {
        boardMatrix = new TerrainNode[20][20];
        combineQuadrants(quadrants);
        connectNodes();
    }

    private void combineQuadrants (ArrayList<TerrainMaker> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = getNodeMatrices(quadrants);
        setOneQuadrant(nodeQuadrants.get(0), 0, 0);
        setOneQuadrant(nodeQuadrants.get(1), 0, 10);
        setOneQuadrant(nodeQuadrants.get(2), 10, 0);
        setOneQuadrant(nodeQuadrants.get(3), 10, 10);
    }

    private void setOneQuadrant (TerrainNode[][] quadrant, int startRow, int startColumn) {
        for(int row = startRow; row < (startRow + quadrant.length); row++) {
            System.arraycopy(quadrant[row - startRow], 0, boardMatrix[row], startColumn, quadrant.length);
        }
    }

    private ArrayList<TerrainNode[][]> getNodeMatrices(ArrayList<TerrainMaker> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = new ArrayList<>();
        for(TerrainMaker quadrant : quadrants) {
            nodeQuadrants.add(getNodeMatrix(quadrant.getBoardTiles()));
        }
        return nodeQuadrants;
    }

    private TerrainNode[][] getNodeMatrix(TerrainTile[][] terrainEnumMatrix) {
        TerrainNode[][] nodeMatrix = new TerrainNode[10][10];
        for (int row = 0; row < terrainEnumMatrix.length; row++) {
            for(int column = 0; column < terrainEnumMatrix[row].length; column++) {
                if(terrainEnumMatrix[row][column] != null) {
                    nodeMatrix[row][column] = new TerrainNode(terrainEnumMatrix[row][column].getType(), row, column);
                }
            }
        }
        return nodeMatrix;
    }

    private void connectNodes() {
        for(int row = 0; row < boardMatrix.length; row++) {
            for (int column = 0; column < boardMatrix[row].length; column++) {
                doRowAssignment(row, column);
            }
        }
    }

    private void doRowAssignment (int row, int column) {
        if (!coordinatesInBounds(row, column)) {
            return;
        }
        if (row % 2 == 0) {
            evenRowNodeAssignment(row, column);
            return;
        }
        oddRowNodeAssignment(row, column);
    }

    private void oddRowNodeAssignment (int row, int column) {
        if (boardMatrix[row][column] == null) return;

        if (meetsAdjacencyConditions(row, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, boardMatrix[row][column - 1]);
        }
        if (meetsAdjacencyConditions(row, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, boardMatrix[row][column + 1]);
        }
        if (meetsAdjacencyConditions(row - 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, boardMatrix[row - 1][column]);
        }
        if (meetsAdjacencyConditions(row + 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, boardMatrix[row + 1][column]);
        }
        if (meetsAdjacencyConditions(row - 1, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, boardMatrix[row - 1][column + 1]);
        }
        if (meetsAdjacencyConditions(row + 1, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, boardMatrix[row + 1][column + 1]);
        }
    }

    private void evenRowNodeAssignment (int row, int column) {
        if (boardMatrix[row][column] == null) return;

        if (meetsAdjacencyConditions(row, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, boardMatrix[row][column - 1]);
        }
        if (meetsAdjacencyConditions(row, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, boardMatrix[row][column + 1]);
        }
        if (meetsAdjacencyConditions(row - 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, boardMatrix[row - 1][column]);
        }
        if (meetsAdjacencyConditions(row + 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, boardMatrix[row + 1][column]);
        }
        if (meetsAdjacencyConditions(row + 1, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, boardMatrix[row + 1][column - 1]);
        }
        if (meetsAdjacencyConditions(row - 1, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, boardMatrix[row - 1][column - 1]);
        }
    }

    protected boolean meetsAdjacencyConditions (int row, int column) {
        return coordinatesInBounds(row, column) && boardMatrix[row][column] != null;
    }

    private boolean coordinatesInBounds (int row, int column) {
        return !(row < 0 || row >= 20 || column < 0 || column >= 20);
    }

    public TerrainNode[][] getBoardMatrix() {
        return boardMatrix;
    }
}
