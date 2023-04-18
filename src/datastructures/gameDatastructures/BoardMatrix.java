package datastructures.gameDatastructures;

import files.QuadrantMaker;
import logic.constantFolder.DirectionEnum;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used to store a matrix of the Nodes of the boards. This is used in order to check
 * for simple adjacency and is used in order to retrieve the boards in a manner that preserves direction.
 * This can be used in order to create nodes based on the matrix to be displayed.
 */
public class BoardMatrix {

    private final TerrainNode[][] boardMatrix;

    public BoardMatrix (String[] boardNames) {
        boardMatrix = new TerrainNode[20][20];
        setBoardNodes(boardNames);
        connectTerrainNodes();
    }

    public BoardMatrix (ArrayList<QuadrantMaker> quadrants) {
        boardMatrix = new TerrainNode[20][20];
        combineQuadrants(quadrants);
        connectTerrainNodes();
    }

    /**
     * Returns the board matrix with all nodes connected.
     * @return boardMatrix
     */
    public TerrainNode[][] getBoardMatrix() {
        return boardMatrix;
    }

    /**
     * Sets the board's nodes without connecting them.
     * @param boardNames names of the boards to be used.
     */
    private void setBoardNodes (String[] boardNames) {
        ArrayList<QuadrantMaker> quadrants = new ArrayList<>();
        for(int i = 0; i < boardNames.length; i++) {
            quadrants.add(new QuadrantMaker(i));
        }
        combineQuadrants(quadrants);
    }

    /**
     * Creates each quadrant and sets up each quadrant in the boardMatrix, combining them.
     * @param quadrants Quadrants to be combined.
     */
    private void combineQuadrants (ArrayList<QuadrantMaker> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = getTerrainNodeMatrices(quadrants);
        setOneQuadrant(nodeQuadrants.get(0), 0, 0);
        setOneQuadrant(nodeQuadrants.get(1), 0, 10);
        setOneQuadrant(nodeQuadrants.get(2), 10, 0);
        setOneQuadrant(nodeQuadrants.get(3), 10, 10);
    }

    /**
     * Sets up a single quadrant in the boardMatrix.
     * @param quadrant Quadrant to be copied into the boardMatrix.
     * @param startRow The row at which the quadrant starts copying.
     * @param startColumn The column at which the quadrant starts copying.
     */
    private void setOneQuadrant (TerrainNode[][] quadrant, int startRow, int startColumn) {
        for(int row = startRow; row < (startRow + quadrant.length); row++) {
            System.arraycopy(quadrant[row - startRow], 0, boardMatrix[row], startColumn, quadrant.length);
        }
    }

    private ArrayList<TerrainNode[][]> getTerrainNodeMatrices (ArrayList<QuadrantMaker> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = new ArrayList<>();
        for(QuadrantMaker quadrant : quadrants) {
            nodeQuadrants.add(getTerrainNodeMatrix(quadrant.getTerrainTiles()));
        }
        return nodeQuadrants;
    }

    private TerrainNode[][] getTerrainNodeMatrix (TerrainTile[][] terrainEnumMatrix) {
        TerrainNode[][] nodeMatrix = new TerrainNode[10][10];
        for (int row = 0; row < terrainEnumMatrix.length; row++) {
            for(int column = 0; column < terrainEnumMatrix[row].length; column++) {
                nodeMatrix[row][column] = new TerrainNode(terrainEnumMatrix[row][column].getType());
            }
        }
        return nodeMatrix;
    }

    private void connectTerrainNodes () {
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
        if (coordinatesInBounds(row, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, boardMatrix[row][column - 1]);
        }
        if (coordinatesInBounds(row, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, boardMatrix[row][column + 1]);
        }
        if (coordinatesInBounds(row - 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, boardMatrix[row - 1][column]);
        }
        if (coordinatesInBounds(row + 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, boardMatrix[row + 1][column]);
        }
        if (coordinatesInBounds(row - 1, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, boardMatrix[row - 1][column + 1]);
        }
        if (coordinatesInBounds(row + 1, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, boardMatrix[row + 1][column + 1]);
        }
    }

    private void evenRowNodeAssignment (int row, int column) {
        if (coordinatesInBounds(row, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, boardMatrix[row][column - 1]);
        }
        if (coordinatesInBounds(row, column + 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, boardMatrix[row][column + 1]);
        }
        if (coordinatesInBounds(row - 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, boardMatrix[row - 1][column]);
        }
        if (coordinatesInBounds(row + 1, column)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, boardMatrix[row + 1][column]);
        }
        if (coordinatesInBounds(row + 1, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, boardMatrix[row + 1][column - 1]);
        }
        if (coordinatesInBounds(row - 1, column - 1)) {
            boardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, boardMatrix[row - 1][column - 1]);
        }
    }

    private boolean coordinatesInBounds (int row, int column) {
        return !(row < 0 || row >= 20 || column < 0 || column >= 20);
    }
    
}
