package files.mainMakers;

import files.QuadrantMaker;
import logic.constantFolder.Constants;
import logic.tiles.ActionTile;
import logic.tiles.CityTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class CityMaker {
    private final int boardNumber;
    private CityTile[][] boardTiles;

    public CityMaker(int boardNumber) {
        this.boardNumber = boardNumber;
        boardTiles = new CityTile[10][10];
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
                boardTiles[rows][columns] = getTypeFromSymbol(symbol);
                columns++;
            }
            rows++;
        }
    }

    protected void flipMatrix() {
        CityTile[][] destination = new CityTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    protected CityTile getTypeFromSymbol(String symbol) {
        if (symbol.equals("c")) {
            return new CityTile();
        }
        return null;
    }

    public CityTile[][] getBoardTiles() {
        return boardTiles;
    }
}
