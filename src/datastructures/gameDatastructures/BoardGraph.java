package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Graph;
import datastructures.baseDatastructures.Node;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;

import java.io.File;
import java.util.ArrayList;

public class BoardGraph extends Graph {

    public BoardGraph(ArrayList<File> boardTextFiles) {
        super(null);
    }

    /**
     * Adds a node to the graph given it is a Terrain Node or an Action Node.
     * @param node Node to be added to the board
     * @param board Board that contains the rest of the nodes in order to gather adjacent nodes to that node
     */
    public void addNode (Node node, Board board) {}
}
