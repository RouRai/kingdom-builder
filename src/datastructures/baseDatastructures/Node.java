package datastructures.baseDatastructures;

import logic.constantFolder.DirectionEnum;

import java.util.HashMap;


/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 * Forms a basic node that can carry data of its adjacent nodes. This type of node relies on direction
 * in order to be able to tell where each adjacent node is. These directions are represented through constants
 * in the Direction Enum, found in the directory "logic/constantFolder/DirectionEnum". This Node also does not
 * hold its own data, and requires for it to be extended through another class in order to do so.
 */
public class Node<T extends Node<?>> {

    private final HashMap<DirectionEnum, T> adjacentNodes;
    private final int row, column;
    private int trueRow, trueColumn;

    public Node (int row, int column) {
        adjacentNodes = new HashMap<>();
        this.row = row;
        this.column = column;
    }

    /**
     * @return Adjacency list of this specific node
     */
    public HashMap<DirectionEnum, T> getAdjacentNodes () {
        return adjacentNodes;
    }

    /**
     * Adds a new node to the adjacency list that can be accessed.
     * @param newAdjacentNode The node that is to be added to the adjacency list
     * @param direction The direction in which the node is to be added to the adjacency Hash Map.
     */
    public void addAdjacentNode (DirectionEnum direction, T newAdjacentNode) {
        adjacentNodes.put(direction, newAdjacentNode);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getTrueRow() {
        return trueRow;
    }

    public int getTrueColumn() {
        return trueColumn;
    }

    public void setTrueRow(int trueRow) {
        this.trueRow = trueRow;
    }

    public void setTrueColumn(int trueColumn) {
        this.trueColumn = trueColumn;
    }
}
