package graphics.frames;

import graphics.panels.*;
import logic.constantFolder.Constants;
import logic.gameLogic.Board;
import logic.gameLogic.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class KBWindow extends JFrame {

   private static CardLayout cl;
   private static HashMap<String,JPanel> panels;
   private static Game game;
   public KBWindow (){
      super("Kingdom Builder");
      pack();
      cl = new CardLayout();
      panels = new HashMap<>();
      System.out.println();

      game = new Game(getboardNumbers(), getobjectiveNumbers());
      
      init();
      add(Constants.PANEL_CONT);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(Constants.WIDTH,Constants.HEIGHT);
      setVisible(true);
   }

   private int[] getboardNumbers() {
      HashSet<Integer> boardNumbers = new HashSet<Integer>();
      for(int i = 0; i < 4; i++) {
         int rand;
         do {
            rand = (int) (Math.random() * (2 * Constants.getBoards().length));
            boardNumbers.add(rand);
         } while (boardNumbers.size() <= i+1);
      }
      int [] arr = new int[4];
      for(int i = 0; i < 4; i++) {
         arr[i] = (int) boardNumbers.toArray()[i];
         System.out.println(arr[i]);
      }
      return arr;
   }
   private int[] getobjectiveNumbers() {
      HashSet<Integer> boardNumbers = new HashSet<Integer>();
      for(int i = 0; i < 3; i++){
         int rand;
         do {
            rand = (int) (Math.random() * (Constants.getCharCards().length));
            boardNumbers.add(rand);
         } while (boardNumbers.size() <= i+1);
      }
      int [] arr = new int[3];
      for(int i = 0; i < 3; i++) {
         arr[i] = (int) boardNumbers.toArray()[i];
         System.out.println(arr[i]);
      }
      return arr;
   }

   private void init(){
      panels.put(Constants.START_PANEL, new StartPanel(cl));
      panels.put(Constants.MENU_PANEL, new MenuPanel(cl,game));
      panels.put(Constants.CARD_PANEL, new CardPanel(cl,game));
      Constants.PANEL_CONT.setLayout(cl);
      Constants.PANEL_CONT.add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
      Constants.PANEL_CONT.add(panels.get(Constants.MENU_PANEL), Constants.MENU_PANEL);
      Constants.PANEL_CONT.add(panels.get(Constants.CARD_PANEL), Constants.CARD_PANEL);
      //remove the part below later
      panels.put(Constants.GAME_PANEL, new KBPanel(cl,game));
      Constants.PANEL_CONT.add(panels.get(Constants.GAME_PANEL), Constants.GAME_PANEL);
      panels.put(Constants.END_PANEL, new EndPanel(cl,game));
      Constants.PANEL_CONT.add(panels.get(Constants.END_PANEL), Constants.END_PANEL);
      cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
   }
   public static void setup(){
      panels.put(Constants.GAME_PANEL, new KBPanel(cl,game));
      Constants.PANEL_CONT.add(panels.get(Constants.GAME_PANEL), Constants.GAME_PANEL);
      panels.put(Constants.END_PANEL, new EndPanel(cl,game));
      Constants.PANEL_CONT.add(panels.get(Constants.END_PANEL), Constants.END_PANEL);
      cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
   }
   public static void terminate(){
      Constants.PANEL_CONT.remove(panels.get(Constants.END_PANEL));
      panels.remove(Constants.END_PANEL);
      Constants.PANEL_CONT.remove(panels.get(Constants.GAME_PANEL));
      panels.remove(Constants.GAME_PANEL);
   }
}