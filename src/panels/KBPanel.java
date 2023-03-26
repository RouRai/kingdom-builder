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
import java.util.ArrayList;

public class KBPanel extends JPanel implements ActionListener {
   //Images
   public BufferedImage[] boards, flippedBoards, actionTiles, charCards, landCards, settlements;
   public BufferedImage background, highlight;
   private CardLayout cl;
   private HexagonButton[][] board, board2, board3, board4;

   public KBPanel (CardLayout c){
      //setLayout(null);
      cl = c;
      boards = new BufferedImage [8];
      flippedBoards = new BufferedImage [16];
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
            setUpHexes(board[r][co]);
            board2[r][co] = new HexagonButton();
            board3[r][co] = new HexagonButton();
            board4[r][co] = new HexagonButton();

            setUpHexes(board2[r][co]);
            setUpHexes(board3[r][co]);
            setUpHexes(board4[r][co]);
         }
      }
      try{
         // 1 -- BACKGROUND - BOTTOM LAYER
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play.png"));
         highlight = ImageIO.read(getClass().getResource("/images/graphicsExtra/Hex.png"));
         // 2 -- BOARDS
         boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach.png"));
         boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat.png"));
         boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm.png"));
         boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse.png"));
         boards[4] = ImageIO.read(getClass().getResource("/images/boards/house.png"));
         boards[5] = ImageIO.read(getClass().getResource("/images/boards/stone.png"));
         boards[6] = ImageIO.read(getClass().getResource("/images/boards/tower.png"));
         boards[7] = ImageIO.read(getClass().getResource("/images/boards/Tavern.png"));
         // 2.5 -- FLIPPED BOARDS
         boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach_flipped.png"));
         boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat_flipped.png"));
         boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm_flipped.png"));
         boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse_flipped.png"));
         boards[4] = ImageIO.read(getClass().getResource("/images/boards/house_flipped.png"));
         boards[5] = ImageIO.read(getClass().getResource("/images/boards/oracle_flipped.png"));
         boards[6] = ImageIO.read(getClass().getResource("/images/boards/tower_flipped.png"));
         boards[7] = ImageIO.read(getClass().getResource("/images/boards/tavern_flipped.png"));
         // 3 -- SETTLEMENTS
         settlements[0] = ImageIO.read(getClass().getResource("/images/settlementIcons/Black_Settlement - Copy.png"));
         settlements[1] = ImageIO.read(getClass().getResource("/images/settlementIcons/Blue_Settlement - Copy.png"));
         settlements[2] = ImageIO.read(getClass().getResource("/images/settlementIcons/Red_Settlement - Copy.png"));
         settlements[3] = ImageIO.read(getClass().getResource("/images/settlementIcons/White_Settlement - Copy.png"));
         // 4 - ACTIONTILES
         actionTiles[0] = ImageIO.read(getClass().getResource("/images/actionTiles/barn_Tile.png"));
         actionTiles[1] = ImageIO.read(getClass().getResource("/images/actionTiles/farm_Tile.png"));
         actionTiles[2] = ImageIO.read(getClass().getResource("/images/actionTiles/harbor_Tile.png"));
         actionTiles[3] = ImageIO.read(getClass().getResource("/images/actionTiles/oasis_Tile.png"));
         actionTiles[4] = ImageIO.read(getClass().getResource("/images/actionTiles/oracle_Tile.png"));
         actionTiles[5] = ImageIO.read(getClass().getResource("/images/actionTiles/paddock_Tile.png"));
         actionTiles[6] = ImageIO.read(getClass().getResource("/images/actionTiles/tavern_Tile.png"));
         actionTiles[7] = ImageIO.read(getClass().getResource("/images/actionTiles/tower_Tile.png"));
         // 4 - charCards
         charCards[0] = ImageIO.read(getClass().getResource("/images/characterCards/Citizen.png"));
         charCards[1] = ImageIO.read(getClass().getResource("/images/characterCards/Discoverers.png"));
         charCards[2] = ImageIO.read(getClass().getResource("/images/characterCards/Farmers.png"));
         charCards[3] = ImageIO.read(getClass().getResource("/images/characterCards/Fishermen.png"));
         charCards[4] = ImageIO.read(getClass().getResource("/images/characterCards/Knights.png"));
         charCards[5] = ImageIO.read(getClass().getResource("/images/characterCards/Marchants.png"));
         charCards[6] = ImageIO.read(getClass().getResource("/images/characterCards/Miners.png"));
         charCards[7] = ImageIO.read(getClass().getResource("/images/characterCards/Workers.png"));
         charCards[8] = ImageIO.read(getClass().getResource("/images/characterCards/Lord.png"));
         charCards[9] = ImageIO.read(getClass().getResource("/images/characterCards/Hermits.png"));
         //5- landCards
         landCards[0] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Back.png"));
         landCards[1] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Canyon.png"));
         landCards[2] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Desert.png"));
         landCards[3] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Flower.png"));
         landCards[4] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Forest.png"));
         landCards[5] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Meadow.png"));
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
   public void setUpHexes(HexagonButton temp) {
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Hex Button clicked " + temp + "  ");
            //panel.updateAll(allFactory,factoryFloor,bag, allPlayer, roundscore,endgame,strt, output,end);
            //REPAINT EVERYTHING
         }
      });
   }

   @Override
   public void actionPerformed(ActionEvent e) {

   }
}