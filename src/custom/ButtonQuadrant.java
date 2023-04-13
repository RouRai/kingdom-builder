package custom;

import java.awt.image.BufferedImage;

/**
 * This class functions as a Graphics version of a game board holder of: hex buttons and the location attributes
 * it makes accessing the info easier
 */

public class ButtonQuadrant {
   private HexagonButton[][] board;
   private String[][] boardText;
   public int quadNumber;
   public double startX, startY;
   public BufferedImage image;

   public ButtonQuadrant(int id, HexagonButton[][] b, int x, int y) {
      board = new HexagonButton[10][10];
      quadNumber = id;
      board = b;
      startX = x;
      startY = y;
      for (int r = 0; r < 10; r++) {
         for (int co = 0; co < 10; co++) {
            board[r][co] = b[r][co];
         }
      }
   }

   public HexagonButton[][] getBoard() {
      return board;
   }

}