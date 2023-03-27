package custom;

public class ButtonQuadrant {
   HexagonButton[][] board;
   public int quadNumber;
   ButtonQuadrant (int id, HexagonButton[][] b){
      quadNumber = id;
      board = b;
      for(int r = 0; r < 10; r++){
         for (int co = 0; co < 10; co++) {
            HexagonButton hex = board[r][co];
            hex.setBoardLocation(id, r, co);

         }}


   }

}
