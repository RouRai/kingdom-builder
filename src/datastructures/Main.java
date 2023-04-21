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
        double sum = 0;
        for (int i = 1; i <= 21; i++) {
            sum += 40 * Math.pow(1.02, i-1);
        }
        System.out.println(sum);
    }
}
