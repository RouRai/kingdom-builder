package graphics.panels;

import custom.TranslucentButton;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CardPanel extends JPanel {
   private final CardLayout cardLayout;
   private BufferedImage background;
   private final BufferedImage[] objectiveCards;
   private final TranslucentButton resume, menu;

   public CardPanel(CardLayout cardLayout, Game game) {
      this.cardLayout = cardLayout;
      resume = new TranslucentButton();
      menu = new TranslucentButton();
      objectiveCards = new BufferedImage[3];
      for (int i = 0; i < 3; i++)
         objectiveCards[i] = Constants.getCharCards()[game.getObjectiveNumbers()[i]];
      setUpLeft();
      setUpRight();
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coordinate (" + e.getX() + ", " + e.getY() + ")");
         }
      });
      try {
         background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/cards.png")));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }
   }
   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      //Base Calls
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      super.paintComponent(g2);
      // 1 -- BACKGROUND
      g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);
      int w = 333;
      for (int i = 0; i<3; i++) {
         if (i == 2)
            g2.drawImage(objectiveCards[i], 240 + i * (w + 47), 195, w, 480, null);
         else
            g2.drawImage(objectiveCards[i], 240 + i * (w + 53), 195, w, 480, null);
      }

      resume.setBounds(510,720,240,100);
      menu.setBounds(790,720,240,100);
   }
   private void setUpLeft(){
      add(resume);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      resume.addActionListener(e -> {
         System.out.println("resume");
         cardLayout.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      });
   }
   private void setUpRight(){
      add(menu);
      menu.addActionListener(e -> {
         System.out.println("menu");
         cardLayout.show(Constants.PANEL_CONT, Constants.MENU_PANEL);
      });
   }

}
