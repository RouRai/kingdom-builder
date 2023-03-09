package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class KBPanel extends JPanel {

   //Images
   public BufferedImage[] boards, actionTiles, charCards, landCards, settlements;
   public BufferedImage background;

   KBPanel (){
    setLayout(null);

      settlements = new BufferedImage [4];
      landCards = new BufferedImage [5];
      charCards = new BufferedImage [10];
      actionTiles = new BufferedImage[8];
      try{
         background = ImageIO.read(getClass().getResource("/images/background/1 - overall position.png"));
         /*
         background = ImageIO.read(getClass().getResource("/images/FinalForMe.jpg"));
         boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach.png"));
         boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat.png"));
         boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm.png"));
         boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse.png"));
         boards[4] = ImageIO.read(getClass().getResource("/images/boards/house.png"));
         boards[5] = ImageIO.read(getClass().getResource("/images/boards/stone.png"));
         */
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }

   }
   public void paint (Graphics g){
      super.paintComponent(g);
      g.drawImage(background, 0, 0,getWidth(), getHeight(), null);
      System.out.println("hi");
   }

}