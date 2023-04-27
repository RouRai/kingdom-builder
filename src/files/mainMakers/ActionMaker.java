package files.mainMakers;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;
import logic.tiles.ActionTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class ActionMaker{
    private final int boardNumber;
    private ActionTile[][] boardTiles;

    public ActionMaker(int boardNumber) {
        this.boardNumber = boardNumber;
        boardTiles = new ActionTile[10][10];
        setUpEnumMatrix(boardNumber);
    }

    public void setUpEnumMatrix(int boardNumber) {
        try {
            String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile();
            File myObj = new File(fileURL);
            Scanner myReader = new Scanner(myObj);
            createMatrix(myReader);
            if (boardNumber >= Constants.getBoards().length) {
                flipMatrix();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IndexOutOfBoundsException e) {
            System.out.println("The board number given is out of bounds.");
            e.printStackTrace();
        }
    }

    protected void createMatrix(Scanner fileReader) {
        int rows = 0;
        while (fileReader.hasNext()) {
            String data = fileReader.nextLine();
            String[] symbols = data.split(" ");
            int columns = 0;
            for (String symbol: symbols){
                if(symbol.equals("a"))
                    boardTiles[rows][columns] = getTypeFromSymbol();
                columns++;
            }
            rows++;
        }
    }

    protected void flipMatrix() {
        ActionTile[][] destination = new ActionTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    protected ActionTile getTypeFromSymbol() {
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

    public ActionTile[][] getBoardTiles() {
        return boardTiles;
    }
}
