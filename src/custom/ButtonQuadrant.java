package custom;


/**
 * This class functions as a Graphics version of a game board holder of: hex buttons and the location attributes
 * it makes accessing the info easier
 */

public class ButtonQuadrant {
   private final HexagonButton[][] board;
   public int quadNumber;
   public double startX, startY;


   public ButtonQuadrant(int id, HexagonButton[][] b, int x, int y) {
      //board = new HexagonButton[10][10];
      quadNumber = id;
      board = b;
      startX = x;
      startY = y;
      for (int r = 0; r < 10; r++) {
         System.arraycopy(b[r], 0, board[r], 0, 10);
      }
   }

   public HexagonButton[][] getBoard() {
      return board;
   }

}