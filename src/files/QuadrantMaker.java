package files;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.tiles.ActionTile;
import logic.tiles.CityTile;
import logic.tiles.TerrainTile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class QuadrantMaker {
   private final int boardNumber;
   private final String [][] tiles;
   private TerrainTile[][] terrainTiles;
   private ActionTile[][] actionTiles;
   private CityTile[][] cityTiles;



   public QuadrantMaker(int boardNumber) {
      // do not touch this is for graphics
      //File myObj = new File(Objects.requireNonNull(Constants.class.getResource("/files/textFiles/" + boardNames[boardNumber % 8] + "")).getFile());
      this.boardNumber = boardNumber;
      tiles = new String[10][10];
      terrainTiles = new TerrainTile[10][10];
      setUpEnumMatrix(boardNumber);
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
         createTerrainMatrix(terrainTiles, myReader);
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
   private void createTerrainMatrix (TerrainTile[][] terrainMatrix, Scanner fileReader) {
      int rows = 0;
      while (fileReader.hasNext()) {
         String data = fileReader.nextLine();
         String[] symbols = data.split(" ");
         int columns = 0;
         for (String symbol: symbols){
            setTypeFromSymbol(symbol, rows, columns);
            columns++;
         }
         rows++;
      }
   }

   /**
    * Flips the enumTiles matrix instance variable for flipped boards.
    */
   private void flipTerrainMatrix(){
      TerrainTile[][] flippedBoard = new TerrainTile[10][10];
      for (int row = flippedBoard.length - 1; row > -1; row--) {
         for (int column = flippedBoard[row].length - 1; column > -1; column--) {
            flippedBoard[row][column] = terrainTiles[flippedBoard.length - row - 1][flippedBoard[row].length - column - 1];
         }
      }
      terrainTiles = flippedBoard;
   }

   /**
    * Returns the corresponding TerrainEnum to a symbol.
    * @param symbol The symbol given in a text file.
    * @return TerrainEnum that corresponds to the symbol.
    */
   private Enum<?> getTerrainTypeFromSymbol (String symbol) {
      return switch (symbol) {
         case "d" -> TerrainEnum.DESERT;
         case "g" -> TerrainEnum.GRASS;
         case "f" -> TerrainEnum.FOREST;
         case "fl" -> TerrainEnum.FLOWER;
         case "w" -> TerrainEnum.WATER;
         case "v" -> TerrainEnum.CANYON;
         case "m" -> TerrainEnum.MOUNTAIN;
         case "c" -> TerrainEnum.CITY;
         case "ac" -> getActionTypeFromSymbol();
         default -> null;
      };
   }

   private void setTypeFromSymbol (String symbol, int row, int column) {
      switch (symbol) {
         case "d" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.DESERT);
         case "g" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.GRASS);
         case "f" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.FOREST);
         case "fl" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.FLOWER);
         case "w" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.WATER);
         case "v" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.CANYON);
         case "m" -> terrainTiles[row][column] = new TerrainTile(TerrainEnum.MOUNTAIN);
         case "c" -> cityTiles[row][column] = new CityTile();
         case "ac" -> setActionTypeFromSymbol(row, column);
      };
   }
   private ActionEnum getActionTypeFromSymbol(){
      return switch (boardNumber % 8) {
         case 0 -> ActionEnum.OASIS;
         case 1 -> ActionEnum.HARBOR;
         case 2 -> ActionEnum.FARM;
         case 3 -> ActionEnum.PADDOCK;
         case 4 -> ActionEnum.BARN;
         case 5 -> ActionEnum.ORACLE;
         case 6 -> ActionEnum.TOWER;
         case 7 -> ActionEnum.TAVERN;
         default -> null;
      };
   }

   private void setActionTypeFromSymbol (int row, int column) {
      switch (boardNumber % 8) {
         case 0 -> actionTiles[row][column] = new ActionTile(ActionEnum.OASIS);
         case 1 -> actionTiles[row][column] = new ActionTile(ActionEnum.HARBOR);
         case 2 -> actionTiles[row][column] = new ActionTile(ActionEnum.FARM);
         case 3 -> actionTiles[row][column] = new ActionTile(ActionEnum.PADDOCK);
         case 4 -> actionTiles[row][column] = new ActionTile(ActionEnum.BARN);
         case 5 -> actionTiles[row][column] = new ActionTile(ActionEnum.ORACLE);
         case 6 -> actionTiles[row][column] = new ActionTile(ActionEnum.TOWER);
         case 7 -> actionTiles[row][column] = new ActionTile(ActionEnum.TAVERN);
      };
   }

   public TerrainTile[][] getTerrainTiles () {
      return terrainTiles;
   }


   public int getBoardNumber() {
      return boardNumber;
   }

   public String[][] getTiles() {
      return tiles;
   }
}
