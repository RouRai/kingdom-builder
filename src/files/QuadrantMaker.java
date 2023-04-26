package files;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.tiles.ActionTile;
import logic.tiles.CityTile;
import logic.tiles.TerrainTile;
import logic.tiles.Tile;
import java.lang.ClassCastException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static logic.constantFolder.Constants.boardNames;

public class QuadrantMaker<T extends Tile<?>> {
   protected final int boardNumber;
   protected T[][] boardTiles;



   public QuadrantMaker(int boardNumber) {
      this.boardNumber = boardNumber;
      boardTiles = (T[][]) new Tile[10][10];
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

   /**
    * Creates the TerrainEnum Matrix given an empty TerrainEnum matrix and a Scanner to be used.
    * @param fileReader The Scanner object to be used in order to read the file.
    */
   private void createMatrix(Scanner fileReader) {
      int rows = 0;
      while (fileReader.hasNext()) {
         String data = fileReader.nextLine();
         String[] symbols = data.split(" ");
         int columns = 0;
         for (String symbol: symbols){
            boardTiles[rows][columns] = getTypeFromSymbol(symbol, rows, columns);
            columns++;
         }
         rows++;
      }
   }

   /**
    * Flips the enumTiles matrix instance variable for flipped boards.
    */
   private void flipMatrix(){}

   private T getTypeFromSymbol (String symbol, int row, int column) {return null;}

   public T[][] getBoardTiles() {
      return boardTiles;
   }


   public int getBoardNumber() {
      return boardNumber;
   }
}
