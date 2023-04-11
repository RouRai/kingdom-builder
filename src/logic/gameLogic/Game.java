package logic.game;

import logic.cards.*;
import logic.gameLogic.Board;
import logic.gameLogic.Player;

import java.util.ArrayList;

public class Game {
   public ArrayList<Player> allPlayers;
   public TerrainDeck terDeck;
   public ObjectiveDeck objDeck;
   public ArrayList <Board> allBoards;

   public Game (){
      terDeck = new TerrainDeck();
      objDeck = new ObjectiveDeck();
      
      //
      allBoards = new ArrayList<>();
      for (int i = 0; i< 4; i++){
         Board temp = new Board ();
         allBoards.add(temp);
      }      
      //
      allPlayers = new ArrayList<>();
      for (int i = 0; i< 4; i++){
         Player temp = new Player (i);
         allPlayers.add(temp);
      }
      
   }
}
