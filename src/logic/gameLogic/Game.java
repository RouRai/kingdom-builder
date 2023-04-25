package logic.gameLogic;

import custom.HexagonButton;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import files.QuadrantMaker;
import logic.cards.*;
import logic.constantFolder.Constants;
import logic.placeables.Settlement;

import java.util.ArrayList;

public class Game {
   public ArrayList<Player> allPlayers;
   private TerrainDeck terrainDeck;
   private ArrayList<TerrainCard> terrainCards;

   public Board board;
   private final Constants constantClass;
   public Game (int [] boardNumbers){
      //basics
               constantClass = new Constants();
      //Cards
            //Terrain Card Deck
               terrainDeck = new TerrainDeck();
               terrainCards = terrainDeck.getTerrainDeck();

      //Boards
            //Quadrant maker
               ArrayList<QuadrantMaker> boardMaker = new ArrayList<>();
               for(int i = 0; i < 4; i++) {
                  QuadrantMaker tempQM = new QuadrantMaker(boardNumbers[i]);
                  boardMaker.add(tempQM);
               }
               board = new Board (boardMaker);

      //Players
               allPlayers = new ArrayList<>();
               for (int i = 0; i< 4; i++){
                  Player temp = new Player (i+1);
                  allPlayers.add(temp);
               }
               allPlayers.get(0).setCard(getCard());
   }
   /**
    * @return gets the next card
    * question: do we need to shuffle the deck when we create it?
    */
   public void endTurn(){
      Player temp = allPlayers.get(0);
      temp.setHasPlacedSettlements(false);
      temp.setUsingActionTile(false);
      temp.setPlacingRegSettlements(false);
      temp.setCard(null);
      temp.setNumSettlementsPlaced(0);
      allPlayers.remove(0);
      allPlayers.add(temp);
      allPlayers.get(0).setCard(getCard());
   }
   /** make sure to check this later
    *
    */
   public boolean canEndTurn(){
      return allPlayers.get(0).hasPlacedSettlements() || allPlayers.get(0).getSettlementsRemaining() == 0;
   }
   public boolean checkEndGame(){
      return allPlayers.get(0).getSettlementsRemaining() == 0 || allPlayers.get(1).getSettlementsRemaining() == 0 || allPlayers.get(2).getSettlementsRemaining() == 0 || allPlayers.get(3).getSettlementsRemaining() == 0;
   }
   /**
    * Logic method by Sri which checks the conditions of each regular settlement placement...
    * @param player
    * @param button
    */
   public void checkRegularSettlementPlacement (Player player, HexagonButton button) {
      if(player.hasPlacedSettlements()){
         return;
      }

      if (player.isUsingActionTile()) {
         checkActionTilePlacement(player,button);
         return;
      }
      //regular
         /*if (player.hasPlacedSettlements() || button.getSettlementImage() != null || player.getSettlementsRemaining() == 0 || !button.getTileType().equals(player.getCard().type()))
            return;*/
         /*if (button.canClick) {
            System.out.println("-------------can click");
            return;
         }*/

         //we set the boolean is placing reg settlement true b/c we know its true now
         if(!player.isPlacingRegSettlements())
            player.setPlacingRegSettlements(true);

         //when the current player have already settled somewhere
         if (player.getNumSettlementsPlaced()<3){
            //if (button.canClick)
            button.setSettlementImage(constantClass.getSettlements()[player.getPlayerNumber() - 1]);
            Settlement temps = player.getSettlement(button.getquadNum(), button.getRow(), button.getCol());
            temps.setLocation(board.getBoard().getBoardMatrix()[temps.getTrueRow()][temps.getTrueColumn()]);
            button.setSettlement(temps);
            player.setNumSettlementsPlaced(player.getNumSettlementsPlaced() + 1);
         }

      //giving the tile / button TO the current player's settlement

      //player regular settlement is over
      if(player.getNumSettlementsPlaced() == 3) {
         player.setHasPlacedSettlements(true);
         player.setPlacingRegSettlements(false);
      }
   }
   //skjfgkhjdsfhgkdsjfgouijsed
   private void setRegularAdjacent(Player player, HexagonButton button){
      // right, right bottom, left bottom, left, left top, right top
      int [] rowOp = {0,1,1,0,-1,-1};
      int [] colOp = {1,1,0,-1,0,1};

      int q = button.getquadNum();
      int r = button.getRow();
      int c = button.getCol();
      Boolean []arr = new Boolean[6];
      for (int i = 0; i<6; i++){
         Boolean bool = false;
         int checkedR = r+rowOp[i];
         int checkedC = c+colOp[i];
            if (q > 1)
               checkedR+= 10;
            if (q == 1 || q == 3)
               checkedC+= 10;

         if (checkedR < 20 && checkedR >=0 && checkedC < 20 && checkedC >=0){
            bool = player.getCard().equals(board.getBoard().getBoardMatrix()[checkedR][checkedC]);
         }
         arr[i]= bool;
      }
      button.setAdjacents(arr);
   }


   public void checkActionTilePlacement(Player player, HexagonButton button) {
   }

   public TerrainCard getCard(){
      if(terrainCards.isEmpty()){
         terrainDeck = new TerrainDeck();
         terrainCards = terrainDeck.getTerrainDeck();
         return getCard();
      }
      TerrainCard temp = terrainCards.get(0);
      terrainCards.remove(0);
      return temp;
   }
   public Player getCurrentPlayer(){
      return allPlayers.get(0);
   }

   public ArrayList<Player> getAllPlayers() {
      return allPlayers;
   }

   public ArrayList<TerrainCard> getTerDeck() {
      return terrainCards;
   }

   public Board getBoard() {
      return board;
   }
   public ArrayList<TerrainNode> getLegalPlaces(){
      return board.regularCanUseTiles(allPlayers.get(0), allPlayers.get(0).getCard());
   }
}
