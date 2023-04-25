package files.mainMakers;

import files.QuadrantMaker;
import logic.constantFolder.ActionEnum;
import logic.tiles.ActionTile;

public class ActionMaker extends QuadrantMaker<ActionTile> {

    public ActionMaker(int boardNumber) {
        super(boardNumber);
    }

    public void flipMatrix() {
        ActionTile[][] destination = new ActionTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    public ActionTile getTypeFromSymbol(String symbol) {
        return switch (boardNumber % 8) {
            case 0 -> new ActionTile(ActionEnum.OASIS);
            case 1 -> new ActionTile(ActionEnum.HARBOR);
            case 2 -> new ActionTile(ActionEnum.FARM);
            case 3 -> new ActionTile(ActionEnum.PADDOCK);
            case 4 -> new ActionTile(ActionEnum.BARN);
            case 5 -> new ActionTile(ActionEnum.ORACLE);
            case 6 -> new ActionTile(ActionEnum.TOWER);
            case 7 -> new ActionTile(ActionEnum.TAVERN);
            default -> null;
        };
    }
}
