package logic;

import datastructures.gameDatastructures.TerrainNode;
import files.QuadrantMaker;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.placeables.Settlement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ArrayList<QuadrantMaker> list = new ArrayList<>();
        list.add(new QuadrantMaker(11)); list.add(new QuadrantMaker(12));
        list.add(new QuadrantMaker(13)); list.add(new QuadrantMaker(14));
        Board board = new Board(list);
        TerrainNode[][] boardMatrix = board.getBoard().getBoardMatrix();
        for (TerrainNode[] row : boardMatrix) {
            for (TerrainNode node : row) {
                System.out.print(node.getType() + " ");
            }
            System.out.println();
        }
        boardMatrix[2][9].getTile().setSettlement(new Settlement(0, 2, 9, 0));
        ArrayList<TerrainNode> currList = board.regularCanUseTiles(new Player(0), new TerrainCard(TerrainEnum.CANYON, null));
        for (TerrainNode node : currList) {
            System.out.println(node.getType());
        }
    }
}
