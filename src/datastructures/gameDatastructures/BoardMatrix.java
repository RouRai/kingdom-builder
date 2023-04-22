package datastructures.gameDatastructures;

import files.QuadrantMaker;
import logic.constantFolder.DirectionEnum;
import logic.tiles.ActionTile;
import logic.tiles.CityTile;
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

    private final TerrainNode[][] terrainBoardMatrix;
    private final CityNode[][] cityBoardMatrix;
    private final ActionNode[][] actionBoardMatrix;

    public BoardMatrix (String[] boardNames) {
        terrainBoardMatrix = new TerrainNode[20][20];
        cityBoardMatrix = new CityNode[20][20];
        actionBoardMatrix = new ActionNode[20][20];
        setBoardNodes(boardNames);
        connectTerrainNodes();
    }

    public BoardMatrix (ArrayList<QuadrantMaker> terrainQuadrants) {
        terrainBoardMatrix = new TerrainNode[20][20];
        cityBoardMatrix = new CityNode[20][20];
        actionBoardMatrix = new ActionNode[20][20];
        combineQuadrants(terrainQuadrants);
        connectTerrainNodes();
    }

    /**
     * Returns the board matrix with all nodes connected.
     * @return boardMatrix
     */
    public TerrainNode[][] getTerrainBoardMatrix() {
        return terrainBoardMatrix;
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
            System.arraycopy(quadrant[row - startRow], 0, terrainBoardMatrix[row], startColumn, quadrant.length);
        }
    }

    private ArrayList<TerrainNode[][]> getTerrainNodeMatrices (ArrayList<QuadrantMaker> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = new ArrayList<>();
        for(QuadrantMaker quadrant : quadrants) {
            nodeQuadrants.add(getTerrainNodeMatrix(quadrant.getTerrainTiles()));
        }
        return nodeQuadrants;
    }

    private ArrayList<CityNode[][]> getCityNodeMatrices (ArrayList<QuadrantMaker> quadrants) {
        ArrayList<CityNode[][]> nodeQuadrants = new ArrayList<>();
        for(QuadrantMaker quadrant : quadrants) {
            nodeQuadrants.add(getCityNodeMatrix(quadrant.getCityTiles()));
        }
        return nodeQuadrants;
    }

    private ArrayList<ActionNode[][]> getActionNodeMatrices (ArrayList<QuadrantMaker> quadrants) {
        ArrayList<ActionNode[][]> nodeQuadrants = new ArrayList<>();
        for(QuadrantMaker quadrant : quadrants) {
            nodeQuadrants.add(getActionNodeMatrix(quadrant.getActionTiles()));
        }
        return nodeQuadrants;
    }

    private TerrainNode[][] getTerrainNodeMatrix (TerrainTile[][] terrainEnumMatrix) {
        TerrainNode[][] nodeMatrix = new TerrainNode[10][10];
        for (int row = 0; row < terrainEnumMatrix.length; row++) {
            for(int column = 0; column < terrainEnumMatrix[row].length; column++) {
                if(terrainEnumMatrix[row][column] != null) {
                    nodeMatrix[row][column] = new TerrainNode(terrainEnumMatrix[row][column].getType());
                }
            }
        }
        return nodeMatrix;
    }

    private CityNode[][] getCityNodeMatrix (CityTile[][] cityEnumMatrix) {
        CityNode[][] nodeMatrix = new CityNode[10][10];
        for (int row = 0; row < cityEnumMatrix.length; row++) {
            for(int column = 0; column < cityEnumMatrix[row].length; column++) {
                if (cityEnumMatrix[row][column] != null) {
                    nodeMatrix[row][column] = new CityNode();
                }
            }
        }
        return nodeMatrix;
    }

    private ActionNode[][] getActionNodeMatrix (ActionTile[][] actionEnumMatrix) {
        ActionNode[][] nodeMatrix = new ActionNode[10][10];
        for (int row = 0; row < actionEnumMatrix.length; row++) {
            for(int column = 0; column < actionEnumMatrix[row].length; column++) {
                if(actionEnumMatrix[row][column] != null) {
                    nodeMatrix[row][column] = new ActionNode(actionEnumMatrix[row][column].getType());
                }
            }
        }
        return nodeMatrix;
    }

    private void connectTerrainNodes () {
        for(int row = 0; row < terrainBoardMatrix.length; row++) {
            for (int column = 0; column < terrainBoardMatrix[row].length; column++) {
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
            if(terrainBoardMatrix[row][column - 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, terrainBoardMatrix[row][column - 1]);
        }
        if (coordinatesInBounds(row, column + 1)) {
            if(terrainBoardMatrix[row][column + 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, terrainBoardMatrix[row][column + 1]);
        }
        if (coordinatesInBounds(row - 1, column)) {
            if(terrainBoardMatrix[row - 1][column] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, terrainBoardMatrix[row - 1][column]);
        }
        if (coordinatesInBounds(row + 1, column)) {
            if(terrainBoardMatrix[row + 1][column] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, terrainBoardMatrix[row + 1][column]);
        }
        if (coordinatesInBounds(row - 1, column + 1)) {
            if(terrainBoardMatrix[row - 1][column + 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, terrainBoardMatrix[row - 1][column + 1]);
        }
        if (coordinatesInBounds(row + 1, column + 1)) {
            if(terrainBoardMatrix[row + 1][column + 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, terrainBoardMatrix[row + 1][column + 1]);
        }
    }

    private void evenRowNodeAssignment (int row, int column) {
        if (coordinatesInBounds(row, column - 1)) {
            if(terrainBoardMatrix[row][column - 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.LEFT, terrainBoardMatrix[row][column - 1]);
        }
        if (coordinatesInBounds(row, column + 1)) {
            if(terrainBoardMatrix[row][column + 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.RIGHT, terrainBoardMatrix[row][column + 1]);
        }
        if (coordinatesInBounds(row - 1, column)) {
            if(terrainBoardMatrix[row - 1][column] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_RIGHT, terrainBoardMatrix[row - 1][column]);
        }
        if (coordinatesInBounds(row + 1, column)) {
            if(terrainBoardMatrix[row + 1][column] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_RIGHT, terrainBoardMatrix[row + 1][column]);
        }
        if (coordinatesInBounds(row + 1, column - 1)) {
            if(terrainBoardMatrix[row + 1][column - 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.BOTTOM_LEFT, terrainBoardMatrix[row + 1][column - 1]);
        }
        if (coordinatesInBounds(row - 1, column - 1)) {
            if(terrainBoardMatrix[row - 1][column - 1] != null && terrainBoardMatrix[row][column] != null)
                terrainBoardMatrix[row][column].getAdjacentNodes().put(DirectionEnum.TOP_LEFT, terrainBoardMatrix[row - 1][column - 1]);
        }
    }

    private boolean coordinatesInBounds (int row, int column) {
        return !(row < 0 || row >= 20 || column < 0 || column >= 20);
    }
    
}
