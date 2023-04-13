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

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("tavern"); names.add("farm"); names.add("house"); names.add("oracle");
        BoardMatrix bm = new BoardMatrix(names);
        TerrainNode[][] boardMatrix = bm.getBoardMatrix();
        for (int r = 0; r < boardMatrix.length; r++) {
            for (int c = 0; c < boardMatrix[r].length; c++) {
                System.out.print(boardMatrix[r][c].getType() + " ");
            }
            System.out.println();
        }
    }
}
