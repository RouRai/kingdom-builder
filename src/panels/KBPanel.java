package panels;

import hexxes.hexmech;

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
      boards = new BufferedImage [16];
      settlements = new BufferedImage [4];
      landCards = new BufferedImage [5];
      charCards = new BufferedImage [10];
      actionTiles = new BufferedImage[8];
      try{

         // 1 -- BACKGROUND - BOTTOM LAYER
         background = ImageIO.read(getClass().getResource("/images/background/1 - overall position.png"));

         boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach.png"));
         boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat.png"));
         boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm.png"));
         boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse.png"));
         boards[4] = ImageIO.read(getClass().getResource("/images/boards/house.png"));
         boards[5] = ImageIO.read(getClass().getResource("/images/boards/stone.png"));

         // 2 -- BOARDS,

         // 3 -- SETTLEMENTS & MOVABLE PARTS (IE. TEXT, 

         // 4 - TOP BORDERS & EXTRAS - TOP LAYER


      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }

   }
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      super.paintComponent(g2);

      // 1 -- BACKGROUND
      //g2.drawImage(background,0, 0,1920, 1050, null);
      g2.setBackground(Color.BLACK);
      g2.fillRect(0,0,2000,1000);
      g2.drawImage(boards[0], 0, 0,400, 400, null);


      g2.setColor(new Color (0,0,0,50));


      /*
      //draw grid
      for (int i=0;i<BSIZE;i++) {
         for (int j=0;j<BSIZE;j++) {
            hexmech.drawHex(i,j,g2);
         }
      }
      // UH AM TESTING
      //fill in hexes
      for (int i=0;i<BSIZE;i++) {
         for (int j=0;j<BSIZE;j++) {
            //if (board[i][j] < 0) hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
            //if (board[i][j] > 0) hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);
            hexmech.fillHex(i,j,board[i][j],g2);
         }
      }*/
   }

}