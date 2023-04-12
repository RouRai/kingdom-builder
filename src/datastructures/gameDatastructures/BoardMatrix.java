package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Node;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used to store a matrix of the Nodes of the boards. This is used in order to check
 * for simple adjacency and is used in order to retrieve the boards in a manner that preserves direction.
 * This can be used in order to create nodes based on the matrix to be displayed.
 */
public class BoardMatrix {

    private final Node[][] boardMatrix;

    public BoardMatrix (Node[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public BoardMatrix () {
        boardMatrix = new Node[20][20];
    }

    public Node[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardNode (int row, int column, Node node) {
        boolean rowsInBounds = row > -1 && row < boardMatrix.length;
        boolean columnsInBounds = column > -1 && column < boardMatrix[row].length;

        if(!(rowsInBounds && columnsInBounds)) {
            return;
        }

        boardMatrix[row][column] = node;
    }
    
}
