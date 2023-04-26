package graphics.panels;

import custom.TranslucentButton;
import graphics.frames.KBWindow;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CardPanel extends JPanel {
   private CardLayout cl;
   private BufferedImage background;
   private BufferedImage[] objectiveCards;
   private TranslucentButton Resume, Menu;
   private Graphics2D g2;
   public CardPanel(CardLayout c, Game g ) {
      cl = c;
      Resume = new TranslucentButton();
      Menu = new TranslucentButton();
      objectiveCards = new BufferedImage[3];
      for (int i = 0; i < 3; i++)
         objectiveCards[i] = Constants.getCharCards()[g.getObjectiveNumbers()[i]];

      setUpLeft();
      setUpRight();
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coord (" + e.getX() + ", " + e.getY() + ")");
         }
      });
      try {
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/cards.png"));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }
   }
   public void paintComponent(Graphics g) {
      g2 = (Graphics2D) g;
      //Base Calls
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      super.paintComponent(g2);
      // 1 -- BACKGROUND
      g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);
      int w = 333;
      for (int i = 0; i<3; i++) {
         if (i == 2)
            g2.drawImage(objectiveCards[i], 240 + i * (w + 55), 195, w, 480, null);
         else
            g2.drawImage(objectiveCards[i], 240 + i * (w + 50), 195, w, 480, null);
      }

      Resume.setBounds(510,720,240,100);
      Menu.setBounds(790,720,240,100);
   }
   private void setUpLeft(){
      add(Resume);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      Resume.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("resume");
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
         }
      });
   }
   private void setUpRight(){
      add(Menu);
      Menu.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("menu");
            cl.show(Constants.PANEL_CONT, Constants.MENU_PANEL);
         }
      });
   }

}
