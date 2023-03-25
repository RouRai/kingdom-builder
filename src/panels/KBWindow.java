package panels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import game.Constants;
import game.Constants.*;

public class KBWindow extends JFrame {

   private Container win;
   private KBPanel pane;
   private static CardLayout cl;
   private static HashMap<String,JPanel> panels;
   KBWindow (){
      super("Kingdom Builder");
      pack();
      //win = getContentPane();
      //win.setLayout(null);
      cl = new CardLayout();
      panels = new HashMap<>();
      init();
      add(Constants.PANEL_CONT);
      //pane = new KBPanel ();
      //pane.setLayout(null);
      //pane.setBounds(0,0,1920, 1080);
      setResizable(false);
      //pane.setBorder(BorderFactory.createLineBorder(Color.black));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(Constants.WIDTH,Constants.HEIGHT);
      setVisible(true);
      //this.setTitle("Kingdom Builder.");
      //win.add(pane);
   }
   private void init(){
      panels.put(Constants.START_PANEL, new StartPanel(cl));
      panels.put(Constants.MENU_PANEL, new MenuPanel(cl));
      Constants.PANEL_CONT.setLayout(cl);
      Constants.PANEL_CONT.add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
      Constants.PANEL_CONT.add(panels.get(Constants.MENU_PANEL), Constants.MENU_PANEL);
      cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
   }
   public static void setup(){
      panels.put(Constants.GAME_PANEL, new KBPanel(cl));
      Constants.PANEL_CONT.add(panels.get(Constants.GAME_PANEL), Constants.GAME_PANEL);
      panels.put(Constants.END_PANEL, new EndPanel(cl));
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