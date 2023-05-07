package files.mainMakers;

import logic.constantFolder.Constants;
import logic.tiles.CityTile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class CityMaker {
    private final int boardNumber;
    private CityTile[][] boardTiles;
    private Constants constants;

    public CityMaker(int boardNumber) {
        constants = new Constants();
        this.boardNumber = boardNumber;
        boardTiles = new CityTile[10][10];
        setUpEnumMatrix(boardNumber);
    }

    public void setUpEnumMatrix(int boardNumber) {
        try {
            /*String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile();*/
            InputStreamReader myObj = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("files/textFiles/" + boardNames[boardNumber % 8] + "")));
            BufferedReader myReader = new BufferedReader(myObj);
            createMatrix(myReader);
            if (boardNumber >= Constants.getBoards().length) {
                flipMatrix();
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The board number given is out of bounds.");
            e.printStackTrace();
        }
    }

    protected void createMatrix(BufferedReader fileReader) {
        try{
            int rows = 0;
            while (fileReader.ready()) {
                String data = fileReader.readLine();
                String[] symbols = data.split(" ");
                int columns = 0;
                for (String symbol: symbols){
                    boardTiles[rows][columns] = getTypeFromSymbol(symbol);
                    columns++;
                }
                rows++;
            }
        } catch(Exception e){

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
