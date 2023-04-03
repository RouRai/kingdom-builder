package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Graph;
import datastructures.baseDatastructures.Node;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;

import java.io.File;
import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store the graph of the board specifically. It contains methods
 * that are used in order to traverse this graph in an efficient manner and gather the data
 * used in order to verify and complete actions in Kingdom Builder.
 */
public class BoardGraph extends Graph {

    public BoardGraph(ArrayList<File> boardTextFiles) {
        super(null);
    }

    /**
     * Adds a node to the graph given the board and the specific node to add
     * @param node Node to add to the graph
     * @param board The entire game board
     */
    public void addNode (Node node, Board board) {}
}
