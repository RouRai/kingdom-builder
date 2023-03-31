package panels;

import custom.ButtonQuadrant;
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

   private BufferedImage background, highlight;
   private ButtonQuadrant[] boards;
   private Graphics2D g2;
   private ArrayList <BufferedImage> boardImages;
   private CardLayout cardLay;
   private HexagonButton[][] board, board2, board3, board4;

   public KBPanel (CardLayout cl){
      //setLayout(null);
      cardLay = cl;

      //Boards setup - see ButtonQuadrant class for more details
      boards = new ButtonQuadrant[4];
      int[] boardStartX = {10,423,10,423};
      int[] boardStartY = {6,6,365,365};
      boardImages = new ArrayList<>();

      for (int q = 0; q < 4; q++) {
         HexagonButton[][] tempBoard = new HexagonButton[10][10];
         for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
               tempBoard[r][c] = new HexagonButton(q, r, c);
               setUpHexes(tempBoard[r][c]);
            }
         }
         boards[q] = new ButtonQuadrant(q,tempBoard, boardStartX[q],boardStartY[q]);
      }
      try{
         // 1 -- BACKGROUND - BOTTOM LAYER
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play.png"));
         highlight = ImageIO.read(getClass().getResource("/images/graphicsExtra/Hex.png"));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }

   }
   public void paintComponent(Graphics g)
   {
      g2 = (Graphics2D)g;
      //Base Calls
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      super.paintComponent(g2);
      // 1 -- BACKGROUND
      g2.drawImage(background,0, 0, Constants.WIDTH, Constants.HEIGHT-8, null);
      g2.setBackground(Color.BLACK);
     //g2.drawImage(Constants.getBoards()[0], 0, 0,400, 400, null);

      // 2- drawBoards
     drawBoards();

   }

   /**
    * draws the outline for each Hexbutton with Button Quadrant
    */
   public void drawBoards(){
      for (ButtonQuadrant b: boards) {
         double x = b.startX;
         double y = b.startY;
         HexagonButton [][] board = b.getBoard();
         for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
               if (r % 2 == 0) {
                  board[r][c].setColor(Color.RED);
                  board[r][c].setBounds((int) (x + c * 41.2), (int) y, 46, 46);
               } else {
                  board[r][c].setColor(Color.yellow);
                  board[r][c].setBounds((int) (x + 21 + c * 41.3), (int) y, 46, 46);
               }
               g2.drawImage(highlight, board[r][c].getX() - 40, board[r][c].getY() - 35, 120, 120, null);
               //board[r][c].setIcon(highlight);
               //gameButton.setIcon(icon);
            }
            y += 35.5;
         }
      }
   }
   /**
    * @param temp hexbutton
    *             send in a hex button to set up its action listener function
    */
   public void setUpHexes(HexagonButton temp) {
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Hex Button clicked " + temp + "  ");
         }
      });
   }

   @Override
   public void actionPerformed(ActionEvent e) {

   }
}