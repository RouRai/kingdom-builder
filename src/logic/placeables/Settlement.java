package logic.placeables;

import datastructures.gameDatastructures.TerrainNode;

public class Settlement {

    private final int color;
    private final int row, column, quadrantNumber;
    private TerrainNode location;

    public Settlement (int color, int row, int column, int quadrantNumber) {
        this.color = color;
        this.row = row;
        this.column = column;
        this.quadrantNumber = quadrantNumber;
    }

    public int getColor() {
        return color;
    }

    private void setLocation (TerrainNode location) {
        this.location = location;
    }
}
