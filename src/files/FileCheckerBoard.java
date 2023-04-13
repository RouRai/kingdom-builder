package files;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Player;
import logic.tiles.TerrainTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileCheckerBoard {


   public String simpName;
   public int boardNum;
   private String enumName;
   public String [][] tiles;
   private TerrainEnum[][] enumTiles;


   public FileCheckerBoard (String simpName, int i) {
      // do not touch this is for graphics
      tiles = new String[10][10];
      enumTiles = new TerrainEnum[10][10];
      addStrings(simpName);
      getEnumMatrix(simpName);
      boardNum = i;
   }

   public void addStrings (String s) {
      try {
         File myObj = new File(Objects.requireNonNull(getClass().getResource("/files/textFiles/" + s + "")).getFile());
         Scanner myReader = new Scanner(myObj);
         int r = 0;
         while (myReader.hasNext()) {
            String data = myReader.nextLine();
            String [] arr = data.split(" ");
            int c = 0;
            for (String str: arr){
               String temp = "*";
               if (str.equals("d")){
                  temp = "DESERT";}
               else if (str.equals("g")) {
                  temp = "GRASS";
               }
               else if (str.equals("f")){
                  temp = "FOREST";}
               else if (str.equals("fl")){
                  temp = "FLOWER_FIELD";}
               else if (str.equals("w")){
                  temp = "WATER";}
               else if (str.equals("v")){
                  temp = "CANYON";}
               else if (str.equals("m")){
                  temp = "MOUNTAIN";}
               else if (str.equals("c")){
                  temp = "CITY";}
               else if (str.equals("a")){temp = null;}
               tiles[r][c] = temp;
               c++;
            }
            r++;
         }
         //System.out.println("-----------");
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
   }

   public void getEnumMatrix (String fileName) {
      try {
         String fileURL = Objects.requireNonNull(getClass().getResource("/files/textFiles/" + fileName + "")).getFile();
         File myObj = new File(fileURL);
         Scanner myReader = new Scanner(myObj);
         createTerrainMatrix(enumTiles, myReader);
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


   public ArrayList<TerrainTile> playerCanUseStrings (Player player) {
      // Returns arraylist of tiles player can use
      return null;
   }

   public TerrainEnum[][] getEnumTiles () {
      return enumTiles;
   }
}
