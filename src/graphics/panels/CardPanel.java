package graphics.panels;

import custom.TranslucentButton;
import graphics.frames.KBWindow;
import logic.constantFolder.Constants;

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
   public CardPanel(CardLayout c) {
      cl = c;
      Resume = new TranslucentButton();
      Menu = new TranslucentButton();
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
      g2.drawImage(objectiveCards[0], 700, 22, 605, 800, null);

      Resume.setBounds(660,395,310,100);
      Menu.setBounds(1305,405,310,100);
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
