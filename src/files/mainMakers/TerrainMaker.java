package files.mainMakers;

import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.tiles.TerrainTile;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class TerrainMaker {
    private final int boardNumber;
    private TerrainTile[][] boardTiles;
    private Constants constants;

    public TerrainMaker(int boardNumber) {
        constants = new Constants();
        this.boardNumber = boardNumber;
        boardTiles = new TerrainTile[10][10];
        setUpEnumMatrix(boardNumber);
    }

    public void setUpEnumMatrix(int boardNumber) {
        try {
            /*String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile();*/
            /*InputStream stream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("/files/textFiles/" + boardNames[boardNumber % 8] + ""));
            System.out.println(stream);
            InputStreamReader myObj = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader myReader = new BufferedReader(myObj);*/
            InputStream stream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("files/textFiles/" + boardNames[boardNumber % 8] + ""));
            InputStreamReader isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader myReader = new BufferedReader(isr);
            //File myObj = new File(fileURL);
            //FileReader myReader = new FileReader(myObj);
            //BufferedReader myReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("/files/textFiles/" + boardNames[boardNumber % 8] + "")));
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
        }catch (Exception e){

        }

    }

    protected void flipMatrix() {
        TerrainTile[][] destination = new TerrainTile[10][10];
        for (int row = destination.length - 1; row > -1; row--) {
            for (int column = destination[row].length - 1; column > -1; column--) {
                destination[row][column] = boardTiles[destination.length - row - 1][destination[row].length - column - 1];
            }
        }
        boardTiles = destination;
    }

    protected TerrainTile getTypeFromSymbol(String symbol) {
        return switch (symbol) {
            case "d" -> new TerrainTile(TerrainEnum.DESERT);
            case "g" -> new TerrainTile(TerrainEnum.GRASS);
            case "f" -> new TerrainTile(TerrainEnum.FOREST);
            case "fl" -> new TerrainTile(TerrainEnum.FLOWER);
            case "w" -> new TerrainTile(TerrainEnum.WATER);
            case "v" -> new TerrainTile(TerrainEnum.CANYON);
            case "m" -> new TerrainTile(TerrainEnum.MOUNTAIN);
            default -> null;
        };
    }

    public TerrainTile[][] getBoardTiles() {
        return boardTiles;
    }
}
