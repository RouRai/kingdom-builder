package panels;

import custom.HexagonButton;
import game.Constants;
//import hexxes.hexmech;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class KBPanel extends JPanel implements ActionListener {

   //Images
   public BufferedImage[] boards, actionTiles, charCards, landCards, settlements;
   public BufferedImage background, highlight;
   private CardLayout cl;
   private HexagonButton[][] board, board2, board3, board4;
   public KBPanel (CardLayout c){
      //setLayout(null);
      cl = c;
      boards = new BufferedImage [16];
      settlements = new BufferedImage [4];
      landCards = new BufferedImage [5];
      charCards = new BufferedImage [10];
      actionTiles = new BufferedImage[8];
      board = new HexagonButton[10][10];
      board2 = new HexagonButton[10][10];
      board3 = new HexagonButton[10][10];
      board4 = new HexagonButton[10][10];
      for(int r = 0; r < 10; r++){
         for(int co = 0; co < 10; co++){
            board[r][co] = new HexagonButton();
            board2[r][co] = new HexagonButton();
            board3[r][co] = new HexagonButton();
            board4[r][co] = new HexagonButton();
            setUpHexes(board[r][co]);
            setUpHexes(board2[r][co]);
            setUpHexes(board3[r][co]);
            setUpHexes(board4[r][co]);
         }
      }
      try{
         // 1 -- BACKGROUND - BOTTOM LAYER
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play.png"));
         highlight = ImageIO.read(getClass().getResource("/images/graphicsExtra/Hex.png"));

         // 2 -- BOARDS,
         boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach.png"));
         boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat.png"));
         boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm.png"));
         boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse.png"));
         boards[4] = ImageIO.read(getClass().getResource("/images/boards/house.png"));
         boards[5] = ImageIO.read(getClass().getResource("/images/boards/stone.png"));
         // 3 -- SETTLEMENTS

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
      g2.drawImage(background,0, 0, Constants.WIDTH, Constants.HEIGHT-8, null);
      g2.setBackground(Color.BLACK);
      //g2.fillRect(0,0,2000,1000);
     // g2.drawImage(boards[0], 0, 0,400, 400, null);
      //top right board
      double x = 423;
      double y = 6;
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            if(r % 2 == 0){
               board[r][c].setColor(Color.RED);
               board[r][c].setBounds((int)(x + c * 41.2), (int)y, 46, 46);
            } else{
               board[r][c].setColor(Color.yellow);
               board[r][c].setBounds((int)(x + 21 + c * 41.3), (int)y, 46, 46);
            }
            g2.drawImage(highlight, board[r][c].getX() - 40, board[r][c].getY() - 35, 120, 120, null);
            //board[r][c].setIcon(highlight);
            //gameButton.setIcon(icon);
         }
         y+=35.5;
      }
      //top left board
      x = 10;
      y = 6;
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            if(r % 2 == 0){
               board2[r][c].setColor(Color.RED);
               board2[r][c].setBounds((int)(x + c * 41.2), (int)y, 46, 46);
            } else{
               board2[r][c].setColor(Color.yellow);
               board2[r][c].setBounds((int)(x + 21 + c * 41.3), (int)y, 46, 46);
            }
            g2.drawImage(highlight, board2[r][c].getX() - 40, board2[r][c].getY() - 35, 120, 120, null);
            //board[r][c].setIcon(highlight);
            //gameButton.setIcon(icon);
         }
         y+=35.5;
      }
      //bottom left board
      x = 10;
      y = 365;
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            if(r % 2 == 0){
               board3[r][c].setColor(Color.RED);
               board3[r][c].setBounds((int)(x + c * 41.2), (int)y, 46, 46);
            } else{
               board3[r][c].setColor(Color.yellow);
               board3[r][c].setBounds((int)(x + 21 + c * 41.3), (int)y, 46, 46);
            }
            g2.drawImage(highlight, board3[r][c].getX() - 40, board3[r][c].getY() - 35, 120, 120, null);
            //board[r][c].setIcon(highlight);
            //gameButton.setIcon(icon);
         }
         y+=35.5;
      }
      //bottom right board
      x = 423;
      y = 365;
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            if(r % 2 == 0){
               board4[r][c].setColor(Color.RED);
               board4[r][c].setBounds((int)(x + c * 41.2), (int)y, 46, 46);
            } else{
               board4[r][c].setColor(Color.yellow);
               board4[r][c].setBounds((int)(x + 21 + c * 41.3), (int)y, 46, 46);
            }
            g2.drawImage(highlight, board4[r][c].getX() - 40, board4[r][c].getY() - 35, 120, 120, null);
            //board[r][c].setIcon(highlight);
            //gameButton.setIcon(icon);
         }
         y+=35.5;
      }
      g2.setColor(new Color (0,0,0,50));


   }
   private void setUpHexes(HexagonButton J){
      add(J);
      J.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(board4[0][0])){
         cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      }
   }
}