package logic.game;

import datastructures.gameDatastructures.BoardGraph;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.TerrainEnum;
import logic.gameLogic.Player;
import logic.tiles.ActionTile;
import logic.tiles.TerrainTile;
import logic.tiles.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCheckerBoard {


   public String simpName;
   public int boardNum;
   private String enumName;
   public String [][] tiles;


   public FileCheckerBoard (String simpName, int i) {
      // do not touch this is for graphics
      tiles = new String[10][10];
      addStrings(simpName);
      boardNum = i;
   }

   public void addStrings (String s) {
      try {
         File myObj = new File(getClass().getResource("/textFiles/"+s+"").getFile());
         Scanner myReader = new Scanner(myObj);
         int r = 0;
         while (myReader.hasNext()) {
            String data = myReader.nextLine();
            String [] arr = data.split(" ");
            int c = 0;
            for (String str: arr){
               //System.out.print("("+r+","+c+") "+str + "\t");
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
            //System.out.println();
            r++;
         }
         //System.out.println("-----------");
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
   }


   public ArrayList<TerrainTile> playerCanUseStrings (Player player) {
      // Returns arraylist of tiles player can use
      return null;
   }
}
