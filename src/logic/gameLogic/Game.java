package logic.gameLogic;

import custom.HexagonButton;
import datastructures.gameDatastructures.boardNodes.ActionNode;
import datastructures.gameDatastructures.boardNodes.CityNode;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import files.QuadrantMaker;
import files.mainMakers.ActionMaker;
import files.mainMakers.CityMaker;
import files.mainMakers.TerrainMaker;
import logic.cards.*;
import logic.constantFolder.Constants;
import logic.placeables.Settlement;
import logic.tiles.TerrainTile;

import java.util.ArrayList;

public class Game {
   public ArrayList<Player> allPlayers;
   private TerrainDeck terrainDeck;
   private ArrayList<TerrainCard> terrainCards;
   private int []boardNumbers,objectiveNumbers;

   public Board board;
   public Game (int []boardNumbers, int[] objectiveNumbers){
      //Cards
            //Terrain Card Deck
               terrainDeck = new TerrainDeck();
               terrainCards = terrainDeck.getTerrainDeck();

      this.objectiveNumbers = objectiveNumbers;
      this.boardNumbers = boardNumbers;
      //Boards
            //Quadrant maker
               ArrayList<TerrainMaker> terrainQuadrants = new ArrayList<>();
               ArrayList<ActionMaker> actionQuadrants = new ArrayList<>();
               ArrayList<CityMaker> cityQuadrants = new ArrayList<>();
               for(int boardNumber : boardNumbers) {
                  terrainQuadrants.add(new TerrainMaker(boardNumber));
                  actionQuadrants.add(new ActionMaker(boardNumber));
                  cityQuadrants.add(new CityMaker(boardNumber));
               }
               board = new Board (terrainQuadrants, cityQuadrants, actionQuadrants);

      //Players
               allPlayers = new ArrayList<>();
               for (int i = 0; i< 4; i++){
                  Player temp = new Player (i+1);
                  allPlayers.add(temp);
               }
               allPlayers.get(0).setCard(getCard());
   }
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
    * @param player current player
    * @param button hexagon button which was clicked
    */
   public void checkRegularSettlementPlacement (Player player, HexagonButton button) {

      //System.out.println(button + " --------------");
      if(player.hasPlacedSettlements()){
         return;
      }

      if (player.isUsingActionTile()) {
         checkActionTilePlacement(player,button);
         return;
      }

         //we set the boolean is placing reg settlement true b/c we know its true now
         if(!player.isPlacingRegSettlements())
            player.setPlacingRegSettlements(true);

         //when the current player have already settled somewhere
         if (player.getNumSettlementsPlaced()<3 && button.getSettlement() == null){
            //if (button.canClick)
            button.setSettlementImage(Constants.getSettlements()[player.getPlayerNumber() - 1]);
            Settlement tempSettlement = player.getSettlement(button.getquadNum(), button.getRow(), button.getCol());
            //System.out.println("what is the settlement? "+ tempSettlement.getLocation());
            tempSettlement.setLocation(board.getTerrainBoard().getBoardMatrix()[tempSettlement.getTrueRow()][tempSettlement.getTrueColumn()]);
            TerrainTile temp = (TerrainTile)button.getTileType();
            temp.setOwner(getCurrentPlayer(), tempSettlement);
            player.setNumSettlementsPlaced(player.getNumSettlementsPlaced() + 1);
            getBoard().getTerrainBoard().getBoardMatrix()[tempSettlement.getTrueRow()][tempSettlement.getTrueColumn()].getTile().setOwner(player,tempSettlement);
         }

      //giving the tile / button TO the current player's settlement

      //player regular settlement is over
      if(player.getNumSettlementsPlaced() == 3) {
         player.setHasPlacedSettlements(true);
         player.setPlacingRegSettlements(false);
      }
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


   public Board getBoard() {
      return board;
   }
   public TerrainNode[][] getTerrainMaxtrix (){return getBoard().getTerrainBoard().getBoardMatrix();}
   public ActionNode[][] getActionMaxtrix (){return getBoard().getActionBoard().getBoardMatrix();}
   public CityNode[][] getCityMaxtrix (){return getBoard().getCityBoard().getBoardMatrix();}
   public ArrayList<TerrainNode> getLegalPlaces(){
      return board.regularCanUseTiles(allPlayers.get(0), allPlayers.get(0).getCard());
   }

   public int[] getObjectiveNumbers() {
      return objectiveNumbers;
   }

   public int[] getBoardNumbers() {
      return boardNumbers;
   }
}
