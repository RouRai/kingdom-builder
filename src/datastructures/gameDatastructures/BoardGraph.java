package datastructures.gameDatastructures;

import datastructures.baseDatastructures.Graph;
import datastructures.baseDatastructures.Node;
import logic.constantFolder.TerrainEnum;
import logic.game.Board;

import java.io.File;
import java.util.ArrayList;

public class BoardGraph extends Graph<TerrainNode, ActionNode> {

    public BoardGraph(ArrayList<File> boardTextFiles) {
        super(new TerrainNode(TerrainEnum.CANYON));
    }

    /**
     * Adds a node to the graph given it is a Terrain Node or an Action Node.
     * @param node Node to be added to the board
     * @param board Board that contains the rest of the nodes in order to gather adjacent nodes to that node
     */
    public void addNode (Node node, Board board) {
        if(node instanceof TerrainNode) {
            addTerrainNode((TerrainNode) node, board);
        }
        else {
           addActionNode((ActionNode) node, board);
        }
    }

    /**
     * This method adds a Node of type Terrain to the graph, given a board that contains its adjacent nodes.
     * @param node Type of node that is to be added to the graph.
     * @param board Board that contains the matrix that has all the types of nodes
     */
    private void addTerrainNode (TerrainNode node, Board board) {

    }

    /**
     This method adds a Node of type Action to the graph, given a board that contains its adjacent nodes.
     * @param node Type of node that is to be added to the graph.
     * @param board Board that contains the matrix that has all the types of nodes
     */
    private void addActionNode (ActionNode node, Board board) {

    }
}
