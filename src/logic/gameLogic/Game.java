package logic.gameLogic;

import custom.ActionProcessButton;
import custom.ButtonQuadrant;
import custom.HexagonButton;
import datastructures.gameDatastructures.boardNodes.ActionNode;
import datastructures.gameDatastructures.boardNodes.CityNode;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import files.mainMakers.ActionMaker;
import files.mainMakers.CityMaker;
import files.mainMakers.TerrainMaker;
import logic.cards.TerrainCard;
import logic.cards.TerrainDeck;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;
import logic.placeables.Settlement;
import logic.tiles.TerrainTile;
import logic.tiles.actionAdjacencies.ActionAdjacency;
import logic.tiles.actionAdjacencies.movesSettlement.Barn;
import logic.tiles.actionAdjacencies.movesSettlement.Harbor;
import logic.tiles.actionAdjacencies.placeSettlements.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
   public ArrayList<Player> allPlayers;
   private TerrainDeck terrainDeck;
   private ArrayList<TerrainCard> terrainCards;
   private final int[] boardNumbers,objectiveNumbers;
   private ButtonQuadrant[] buttonBoard;
   public Board board;
   public int turns;
   public Game (int [] boardNumbers, int[] objectiveNumbers){
      turns = 0;
      terrainDeck = new TerrainDeck();
      terrainCards = terrainDeck.getTerrainDeck();
      this.objectiveNumbers = objectiveNumbers;
      this.boardNumbers = boardNumbers;
      generateBoard(boardNumbers);
      setUpPlayers();
   }
   public void endTurn(){
      turns ++;
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
      return (allPlayers.get(0).hasPlacedSettlements() || allPlayers.get(0).getSettlementsRemaining() == 0) && !allPlayers.get(0).isUsingActionTile();
   }
   public boolean checkEndGame(){
      return allPlayers.get(0).getSettlementsRemaining() == 0 || allPlayers.get(1).getSettlementsRemaining() == 0 || allPlayers.get(2).getSettlementsRemaining() == 0 || allPlayers.get(3).getSettlementsRemaining() == 0;
   }
   private void determineActionType(Player player, HexagonButton button, ActionProcessButton action, HexagonButton chosenHex){
      if(action.getType() == ActionEnum.HARBOR || action.getType() == ActionEnum.BARN || action.getType() == ActionEnum.PADDOCK){
         doMovementAction(player, button, action, chosenHex);
      } else {
         doPlacementAction(player, button, action);
      }
   }
   private void doMovementAction(Player player, HexagonButton button, ActionProcessButton action, HexagonButton chosenHex){
      if(chosenHex == null){
         return;
      }
      BufferedImage settlementImage = chosenHex.getSettlement();
      Settlement tempSettlement = board.getTerrainBoard().getBoardMatrix()[chosenHex.getTrueRow()][chosenHex.getTrueCol()].getTile().getSettlement();
      button.setSettlementImage(settlementImage);
      board.getTerrainBoard().getBoardMatrix()[button.getTrueRow()][button.getTrueCol()].getTile().setOwner(player, tempSettlement);
      tempSettlement.setQuadrantNumber(button.getquadNum());
      tempSettlement.setRow(button.getRow());
      tempSettlement.setColumn(button.getCol());
      tempSettlement.setLocation(board.getTerrainBoard().getBoardMatrix()[tempSettlement.getTrueRow()][tempSettlement.getTrueColumn()]);
      chosenHex.setSettlementImage(null);
      board.getTerrainBoard().getBoardMatrix()[chosenHex.getTrueRow()][chosenHex.getTrueCol()].getTile().removeOwner();
      player.setUsingActionTile(false);
      board.analyzeAdjacencyToActionTile(player, chosenHex.getTrueRow(), chosenHex.getTrueCol());
   }
   private void doPlacementAction(Player player, HexagonButton button, ActionProcessButton action){
      if(button.getSettlement() == null) {
         placeSettlement(player, button);
         player.setUsingActionTile(false);
      }
   }
   /**
    * Logic method by Sri which checks the conditions of each regular settlement placement...
    * @param player current player
    * @param button hexagon button which was clicked
    */
   public void checkRegularSettlementPlacement (Player player, HexagonButton button, ActionProcessButton action, HexagonButton chosenHex) {

      //checking for all adjacent cities
     // board.getTerrainBoard().getBoardMatrix();

      if(player.isUsingActionTile()){
         determineActionType(player, button, action, chosenHex);
         return;
      }
      if(player.hasPlacedSettlements())
         return;
      //we set the boolean is placing reg settlement true b/c we know its true now
      if(!player.isPlacingRegSettlements()) {
         player.setPlacingRegSettlements(true);
      }

      //when the current player have already settled somewhere
      if (player.getNumSettlementsPlaced() < 3 && button.getSettlement() == null){
         placeSettlement(player, button);
         player.setNumSettlementsPlaced(player.getNumSettlementsPlaced() + 1);
      }
      //giving the tile / button TO the current player's settlement

      //player regular settlement is over
      if(player.getNumSettlementsPlaced() == 3) {
         player.setHasPlacedSettlements(true);
         player.setPlacingRegSettlements(false);
      }
   }

   public void checkActionTilePlacement(Player player, HexagonButton button) {
      if(!player.getActionTiles().containsKey(button.getTileType()))
         return;
      //when the current player have already settled somewhere
      if (player.getNumSettlementsPlaced() < 1 && button.getSettlement() == null){
         placeSettlement(player, button);

      }
      System.out.println();

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

   public ButtonQuadrant[] getButtonBoard() {
      return buttonBoard;
   }

   public void setButtonBoard(ButtonQuadrant[] buttonBoard) {
      this.buttonBoard = buttonBoard;
   }

   public Board getBoard() {
      return board;
   }
   public TerrainNode[][] getTerrainMatrix(){return getBoard().getTerrainBoard().getBoardMatrix();}
   public ActionNode[][] getActionMatrix(){return getBoard().getActionBoard().getBoardMatrix();}
   public CityNode[][] getCityMatrix(){return getBoard().getCityBoard().getBoardMatrix();}
   public ArrayList<TerrainNode> getLegalRegularPlaces(){
      return board.regularCanUseTiles(allPlayers.get(0), allPlayers.get(0).getCard());
   }
   public ArrayList<TerrainNode> getLegalActionPlaces(ActionEnum node){
      ActionAdjacency checker = null;
      if (node == ActionEnum.ORACLE) {
         checker = new Oracle(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.OASIS){
         checker = new Oasis(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.FARM){
         checker = new Farm(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.TOWER){
         checker = new Tower(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.TAVERN){
         checker = new Tavern(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.BARN){
         checker = new Barn(getBoard(), getCurrentPlayer());
      } else if(node == ActionEnum.HARBOR){
         checker = new Harbor(getBoard(), getCurrentPlayer());
      }
      if (checker != null)
          return checker.getValidNodes();
      else return null;
   }

   public int[] getObjectiveNumbers() {
      return objectiveNumbers;
   }

   public int[] getBoardNumbers() {
      return boardNumbers;
   }

   private void generateBoard (int[] boardNumbers) {
      ArrayList<TerrainMaker> terrainQuadrants = new ArrayList<>();
      ArrayList<ActionMaker> actionQuadrants = new ArrayList<>();
      ArrayList<CityMaker> cityQuadrants = new ArrayList<>();
      generateEachQuadrant(boardNumbers, terrainQuadrants, actionQuadrants, cityQuadrants);
      board = new Board (terrainQuadrants, cityQuadrants, actionQuadrants);
   }

   private void generateEachQuadrant (int[] boardNumbers, ArrayList<TerrainMaker> terrainQuadrants, ArrayList<ActionMaker> actionQuadrants, ArrayList<CityMaker> cityQuadrants) {
      for(int boardNumber : boardNumbers) {
         terrainQuadrants.add(new TerrainMaker(boardNumber));
         actionQuadrants.add(new ActionMaker(boardNumber));
         cityQuadrants.add(new CityMaker(boardNumber));
      }
   }

   private void setUpPlayers () {
      allPlayers = new ArrayList<>();
      for (int i = 0; i< 4; i++){
         Player temp = new Player (i+1);
         temp.setCard(getCard());
         allPlayers.add(temp);
         temp.setActionProcess(board);
      }
      allPlayers.get(0).setCard(getCard());
   }

   private void placeSettlement (Player player, HexagonButton button) {
      button.setSettlementImage(Constants.getSettlements()[player.getPlayerNumber() - 1]);
      Settlement tempSettlement = player.getSettlement(button.getquadNum(), button.getRow(), button.getCol());
      tempSettlement.setLocation(board.getTerrainBoard().getBoardMatrix()[tempSettlement.getTrueRow()][tempSettlement.getTrueColumn()]);
      TerrainTile temp = (TerrainTile)button.getTileType();
      temp.setOwner(getCurrentPlayer(), tempSettlement);
      getBoard().getTerrainBoard().getBoardMatrix()[tempSettlement.getTrueRow()][tempSettlement.getTrueColumn()].getTile().setOwner(player,tempSettlement);
      getBoard().checkAdjacencyToActionTile(player, tempSettlement.getTrueRow(), tempSettlement.getTrueColumn());
   }

}
