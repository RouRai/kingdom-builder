package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Node;

public class BoardMatrix {

    private final Node[][] boardMatrix;

    public BoardMatrix (Node[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
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
