package datastructures;

import datastructures.baseDatastructures.Node;
import datastructures.gameDatastructures.ActionNode;
import datastructures.gameDatastructures.BoardGraph;
import datastructures.gameDatastructures.TerrainNode;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.DirectionEnum;
import logic.constantFolder.TerrainEnum;

public class Main {
    public static void main(String[] args) {
        BoardGraph bg = new BoardGraph(null);
        Node firstTerrainHex = new TerrainNode(TerrainEnum.CANYON);
        Node secondTerrainHex = new ActionNode(ActionEnum.HARBOR);
        bg.addEdge(firstTerrainHex, secondTerrainHex, DirectionEnum.BOTTOM_LEFT, DirectionEnum.TOP_RIGHT);
        Node n = firstTerrainHex.getAdjacentNodes().get(DirectionEnum.BOTTOM_LEFT);
        Node m = secondTerrainHex.getAdjacentNodes().get(DirectionEnum.TOP_RIGHT);
        System.out.println(Main.getType(n));

        System.out.println(Main.getType(m));
    }

    private static Enum getType (Node n) {
        if(n instanceof ActionNode) {
            return ((ActionNode) n).getType();
        }
        return ((TerrainNode) n).getType();
    }
}
