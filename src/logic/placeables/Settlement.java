package logic.placeables;

import datastructures.gameDatastructures.TerrainNode;

public class Settlement {

    private final int color;
    private final int row, column, quadrantNumber;
    private int trueRow, trueColumn;
    private TerrainNode location;

    public Settlement (int color, int row, int column, int quadrantNumber) {
        this.color = color;
        this.row = row;
        this.column = column;
        this.quadrantNumber = quadrantNumber;
        trueRow = row;
        trueColumn = column;
        if(quadrantNumber == 2 || quadrantNumber == 3){
            trueRow = row +10;
        }
        if(quadrantNumber == 3 || quadrantNumber == 1){
            trueColumn = column + 10;
        }
    }

    public int getColor() {
        return color;
    }

    public void setLocation (TerrainNode location) {
        this.location = location;
    }

    public TerrainNode getLocation () {
        return location;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public int getTrueRow(){
        return trueRow;
    }
    public int getTrueColumn(){
        return  trueColumn;
    }

    public int getQuadrantNumber() {
        return quadrantNumber;
    }

    @Override
    public String toString() {
        return "Settlement{" +
              "color=" + color +
              ", row=" + row +
              ", column=" + column +
              ", quadrantNumber=" + quadrantNumber +
              ", trueRow=" + trueRow +
              ", trueColumn=" + trueColumn +
              ", location=" + location +
              '}';
    }
}
