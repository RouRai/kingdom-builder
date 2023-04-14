package files;

import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Player;
import logic.tiles.TerrainTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class QuadrantMaker {
   private final int boardNumber;
   private final String [][] tiles;
   private final TerrainEnum[][] enumTiles;


   public QuadrantMaker(int boardNumber) {
      // do not touch this is for graphics
      File myObj = new File(Objects.requireNonNull(Constants.class.getResource("/files/textFiles/" + boardNames[boardNumber] + "")).getFile());
      this.boardNumber = boardNumber;
      tiles = new String[10][10];
      enumTiles = new TerrainEnum[10][10];
      addStrings(boardNames[boardNumber]);
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

   public void setUpEnumMatrix(int boardNumber) {
      try {
         String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + boardNames[boardNumber] + "")).getFile();
         File myObj = new File(fileURL);
         Scanner myReader = new Scanner(myObj);
         //if(this.boardNumber < Constants.getBoards().length)
            createTerrainMatrix(enumTiles, myReader);
         //else
            //createFlippedTerrainMatrix(enumTiles, myReader);
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
   }

   private void createTerrainMatrix (TerrainEnum[][] terrainMatrix, Scanner myReader) {
      int rows = 0;
      while (myReader.hasNext()) {
         String data = myReader.nextLine();
         String[] arr = data.split(" ");
         int columns = 0;
         for (String symbol: arr){
            terrainMatrix[rows][columns] = getTerrainTypeFromSymbol(symbol);
            columns++;
         }
         rows++;
      }
   }
   private void createFlippedTerrainMatrix (TerrainEnum[][] terrainMatrix, Scanner myReader){
      //createTerrainMatrix(terrainMatrix, myReader);
      TerrainEnum [][] tempBoard = new TerrainEnum [10][10];
      createTerrainMatrix(tempBoard, myReader);
      System.out.println();
      for (int r = 9; r >=0; r--){
         for (int c = 9; c>=0;c--){
            //System.out.println(r +" "+ c + " - " +boardText[r][c]);
            terrainMatrix [9 - r][9 - c] = tempBoard[r][c];
         }
      }
   }
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
