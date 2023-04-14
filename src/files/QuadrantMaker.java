package files;

import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Player;
import logic.tiles.TerrainTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class QuadrantMaker {
   private final int boardNumber;
   private final String [][] tiles;
   private TerrainEnum[][] enumTiles;


   public QuadrantMaker(int boardNumber) {
      // do not touch this is for graphics
      File myObj = new File(Objects.requireNonNull(Constants.class.getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile());
      this.boardNumber = boardNumber;
      tiles = new String[10][10];
      enumTiles = new TerrainEnum[10][10];
      addStrings(boardNames[boardNumber % 8]);
      setUpEnumMatrix(boardNumber);
   }

   public void addStrings (String boardName) {
      try {
         File myObj = new File(Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardName + "")).getFile());
         Scanner myReader = new Scanner(myObj);
         int row = 0;
         while (myReader.hasNext()) {
            String data = myReader.nextLine();
            String [] line = data.split(" ");
            int column = 0;
            for (String symbol: line){
               String temporaryString = switch (symbol) {
                  case "d" -> "DESERT";
                  case "g" -> "GRASS";
                  case "f" -> "FOREST";
                  case "fl" -> "FLOWER_FIELD";
                  case "w" -> "WATER";
                  case "v" -> "CANYON";
                  case "m" -> "MOUNTAIN";
                  case "c" -> "CITY";
                  case "a" -> null;
                  default -> "*";
               };
               tiles[row][column] = temporaryString;
               column++;
            }
            row++;
         }
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
   }

   /**
    * Sets up the enum matrix given a board number.
    * @param boardNumber The number of a corresponding board.
    */
   public void setUpEnumMatrix(int boardNumber) {
      try {
         String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile();
         File myObj = new File(fileURL);
         Scanner myReader = new Scanner(myObj);
         createTerrainMatrix(enumTiles, myReader);
         if (boardNumber >= Constants.getBoards().length) {
            flipTerrainMatrix();
         }
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }catch(IndexOutOfBoundsException e) {
         System.out.println("The board number given is out of bounds.");
         e.printStackTrace();
      }
   }

   /**
    * Creates the TerrainEnum Matrix given an empty TerrainEnum matrix and a Scanner to be used.
    * @param terrainMatrix The empty matrix to be modified to store the board.
    * @param fileReader The Scanner object to be used in order to read the file.
    */
   private void createTerrainMatrix (TerrainEnum[][] terrainMatrix, Scanner fileReader) {
      int rows = 0;
      while (fileReader.hasNext()) {
         String data = fileReader.nextLine();
         String[] symbols = data.split(" ");
         int columns = 0;
         for (String symbol: symbols){
            terrainMatrix[rows][columns] = getTerrainTypeFromSymbol(symbol);
            columns++;
         }
         rows++;
      }
   }

   /**
    * Flips the enumTiles matrix instance variable for flipped boards.
    */
   private void flipTerrainMatrix(){
      System.out.println(Arrays.deepToString(enumTiles));
      TerrainEnum[][] flippedBoard = new TerrainEnum[10][10];
      for (int row = flippedBoard.length - 1; row > -1; row--) {
         for (int column = flippedBoard[row].length - 1; column > -1; column--) {
            flippedBoard[row][column] = enumTiles[flippedBoard.length - row - 1][flippedBoard[row].length - column - 1];
         }
      }
      enumTiles = flippedBoard;
      System.out.println();
      System.out.println(Arrays.deepToString(enumTiles));
   }

   /**
    * Returns the corresponding TerrainEnum to a symbol.
    * @param symbol The symbol given in a text file.
    * @return TerrainEnum that corresponds to the symbol.
    */
   private TerrainEnum getTerrainTypeFromSymbol (String symbol) {
      return switch (symbol) {
         case "d" -> TerrainEnum.DESERT;
         case "g" -> TerrainEnum.GRASS;
         case "f" -> TerrainEnum.FOREST;
         case "fl" -> TerrainEnum.FLOWER;
         case "w" -> TerrainEnum.WATER;
         case "v" -> TerrainEnum.CANYON;
         case "m" -> TerrainEnum.MOUNTAIN;
         case "c" -> TerrainEnum.CITY;
         default -> null;
      };
   }


   /**
    * Returns the tiles a player can use.
    * @param player The player who is to interact with these objects.
    * @return Tiles the players can interact with.
    */
   public ArrayList<TerrainTile> playerCanUseStrings (Player player) {
      // Returns arraylist of tiles player can use
      return null;
   }

   public TerrainEnum[][] getEnumTiles () {
      return enumTiles;
   }


   public int getBoardNumber() {
      return boardNumber;
   }

   public String[][] getTiles() {
      return tiles;
   }
}
