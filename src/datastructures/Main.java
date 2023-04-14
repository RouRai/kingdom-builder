package datastructures;

import datastructures.baseDatastructures.Node;
import datastructures.gameDatastructures.ActionNode;
import datastructures.gameDatastructures.BoardGraph;
import datastructures.gameDatastructures.BoardMatrix;
import datastructures.gameDatastructures.TerrainNode;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.DirectionEnum;
import logic.constantFolder.TerrainEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("tavern"); names.add("farm"); names.add("house"); names.add("oracle");
        BoardMatrix bm = new BoardMatrix(names);
        TerrainNode[][] boardMatrix = bm.getBoardMatrix();
        for (TerrainNode[] matrix : boardMatrix) {
            for (TerrainNode terrainNode : matrix) {
                System.out.print(terrainNode.getType() + " ");
            }
            System.out.println();
        }
        HashMap map = boardMatrix[0][0].getAdjacentNodes();
        TerrainNode node = (TerrainNode) map.get(DirectionEnum.RIGHT);
        System.out.println(node.getType());
        System.out.println(map.get(DirectionEnum.LEFT));
    }
}
