package datastructures.gameDatastructures;

import files.FileCheckerBoard;
import logic.constantFolder.TerrainEnum;
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

    public BoardMatrix (ArrayList<String> boardNames) {
        boardMatrix = new TerrainNode[20][20];
        setBoardNodes(boardNames);
    }

    public TerrainNode[][] getBoardMatrix() {
        return boardMatrix;
    }

    private void setBoardNodes (ArrayList<String> boardNames) {
        ArrayList<FileCheckerBoard> quadrants = new ArrayList<>();
        for(int i = 0; i < boardNames.size(); i++) {
            quadrants.add(new FileCheckerBoard(boardNames.get(i), i));
        }
        combineQuadrants(quadrants);
    }

    private void combineQuadrants (ArrayList<FileCheckerBoard> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = getTerrainNodeMatrices(quadrants);
        setOneQuadrant(nodeQuadrants.get(0), 0, 0);
        setOneQuadrant(nodeQuadrants.get(1), 0, 10);
        setOneQuadrant(nodeQuadrants.get(2), 10, 0);
        setOneQuadrant(nodeQuadrants.get(3), 10, 10);
    }

    private void setOneQuadrant (TerrainNode[][] quadrant, int startRow, int startColumn) {
        for(int row = startRow; row < (startRow + 10); row++) {
            System.arraycopy(quadrant[row - startRow], 0, boardMatrix[row], startColumn, startColumn + 10 - startColumn);
        }
    }

    private ArrayList<TerrainNode[][]> getTerrainNodeMatrices (ArrayList<FileCheckerBoard> quadrants) {
        ArrayList<TerrainNode[][]> nodeQuadrants = new ArrayList<>();
        for(FileCheckerBoard quadrant : quadrants) {
            nodeQuadrants.add(getTerrainNodeMatrix(quadrant.getEnumTiles()));
        }
        return nodeQuadrants;
    }

    private TerrainNode[][] getTerrainNodeMatrix (TerrainEnum[][] terrainEnumMatrix) {
        TerrainNode[][] nodeMatrix = new TerrainNode[10][10];
        for (int row = 0; row < terrainEnumMatrix.length; row++) {
            for(int column = 0; column < terrainEnumMatrix[row].length; column++) {
                nodeMatrix[row][column] = new TerrainNode(terrainEnumMatrix[row][column]);
            }
        }
        return nodeMatrix;
    }
    
}
