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
        System.out.println(Main.method(20, 0.5, 0.5));
    }

    private static double method (double n, double a1, double r) {
        return a1 * ((1 - Math.pow(r, n))/(1 - r));
    }
}
