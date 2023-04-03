package custom;

import java.awt.image.BufferedImage;

/**
 * This class functions as a Graphics version of a game board holder of: hex buttons and the location attributes
 * it makes accessing the info easier
 */

public class ButtonQuadrant {
   private HexagonButton[][] board;
   public int quadNumber;
   public double startX, startY;
   public BufferedImage image;

   public ButtonQuadrant(int id, HexagonButton[][] board, int x, int y) {
      board = new HexagonButton[10][10];
      quadNumber = id;
      this.board = board;
      startX = x;
      startY = y;
      cloneBoard(board);
   }

   public HexagonButton[][] getBoard() {
      return board;
   }

   private void cloneBoard (HexagonButton[][] board) {
      for (int r = 0; r < board.length; r++) {
         System.arraycopy(board[r], 0, this.board[r], 0, board[r].length);
      }
   }
}
