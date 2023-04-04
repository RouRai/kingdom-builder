package panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import game.Constants;
//import hexxes.hexmech;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class KBPanel extends JPanel implements ActionListener {
   //Images

   private BufferedImage background, highlight;
   private TranslucentButton menuButton, finishButton;
   private TranslucentButton [] objectivesButton;
   private ButtonQuadrant[] boards;
   private Graphics2D g2;
   private ArrayList <BufferedImage> boardImages;
   private CardLayout cardLay;
   private Constants constantClass;
   private final String fontStr = "Lucida Calligraphy";

   public KBPanel (CardLayout cl){
      //setLayout(null);
      cardLay = cl;
      constantClass = new Constants();
      menuButton = new TranslucentButton();
      setUpMenu(menuButton);
      finishButton = new TranslucentButton();
      setUpFinish(finishButton);
      objectivesButton = new TranslucentButton[3];
         for (int q = 0; q < 3; q++) {
            objectivesButton [q]= new TranslucentButton();
         }
      setUpObjective(objectivesButton);

      // for coordinates
      addMouseListener(new MouseAdapter() { @Override public void mousePressed(MouseEvent e) { System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");}});
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
               setUpBoardHexes(tempBoard[r][c]);
            }
         }
         boards[q] = new ButtonQuadrant(q,tempBoard, boardStartX[q],boardStartY[q]);
      }

      // 1 -- BACKGROUND - BOTTOM LAYER
      try{

         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play2.png"));
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
      super.paintComponent(g2);
      // 1 -- BACKGROUND
      g2.drawImage(background,0, 0, Constants.WIDTH, Constants.HEIGHT-8, null);
      g2.setBackground(Color.BLACK);
      //2- drawing hex buttons
      drawLeftPanel();
      drawHexButtons();
      drawMenu();
      drawFinish();
      drawObjectives();
      //3- drawing current player attributes
      drawCurrentPlayer();
      drawOtherPlayer();
      //4- drawing


      //Functionality buttons

   }
   public void drawLeftPanel(){
      for (int i = 0; i < 3; i++)
         g2.drawImage(constantClass.getCharCards()[0], 325+ i * 150, 735, 130, 240, null);
      for (int i = 0; i < 4; i++){
         ButtonQuadrant b = boards[i];
         double x = b.startX;
         double y = b.startY;
         g2.drawImage(constantClass.getBoards()[i],(int)x+2, (int)y-1,435, 369, null);
      }
   }
   public void drawOtherPlayer(){
      int space_between_Players = 123;
      for (int i =0; i < 3; i++) {
         //Player p = player example array [i];
         //number
         g2.setColor(Color.black);
         g2.setFont(new Font(fontStr, Font.PLAIN, 40));
         if (i%2 ==0)
            g2.drawString("1", 1040, 85+space_between_Players*i);
         else
            g2.drawString("1", 1040, 90+space_between_Players*i);
         //action tiles
         g2.setFont(new Font(fontStr, Font.PLAIN, 20));
         g2.setColor(Color.white);
         for (int j = 0; j < 4; j++) {
            int x = 1230;
            int y = 35;
            g2.drawImage(constantClass.getActionTiles()[j], x-25+j *68, y-5 + i * space_between_Players, 60, 60, null);
            if (i%2 ==0)
               g2.drawString("0", x+j *65,  y+80+i  * space_between_Players);
            else
               g2.drawString("0", x+j *65,  y+80+i  * (space_between_Players+8));

         }
         //settlement
         //settlement icon
         g2.drawImage(constantClass.getSettlements()[i], 1100, 40 +i * space_between_Players, 90, 70, null);
         //settlement number
         g2.setFont(new Font(fontStr, Font.PLAIN, 30));
         g2.drawString("40", 1125, 90 + i * space_between_Players);
      }
   }

   /**
    * draws all graphics and images of the current player
    */
   public void drawCurrentPlayer(){
      //Player p = null;
      //number
      g2.setColor(Color.black);
      g2.setFont(new Font(fontStr, Font.PLAIN, 50));
      g2.drawString("1",1185,460);
      //action tiles
      g2.setFont(new Font(fontStr, Font.PLAIN, 20));
      g2.setColor(Color.white);
      for (int i = 0; i < 4; i++) {
         if (i % 2== 0) {
            g2.drawImage(constantClass.getActionTiles()[0], 1005+ i * 2, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",980,560+ i * 75);
         }
         else{
            g2.drawImage(constantClass.getActionTiles()[0], 959+ i *1, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",1055,560+ i * 75);
         }
      }
      //action tile selected
      if (true){
         g2.drawImage(constantClass.getActionProcess()[1], 1135, 645, 150, 60, null);
      }
      //landscape card
      g2.drawImage(constantClass.getLandCards()[0], 1335, 530, 130, 200, null);

      //settlement
      //settlement icon
      g2.drawImage(constantClass.getSettlements()[0], 1330, 410, 120, 100, null);
      //settlement number
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      g2.drawString("40",1365,480);
   }

   public void drawMenu (){ menuButton.setBounds(785,765,70,60);}
   public void drawFinish (){ finishButton.setBounds(1320,750,170,60);}
   public void drawObjectives (){
      for (int i = 0; i< 3; i++)
         objectivesButton[i].setBounds(325+ i * 150, 735, 130, 240);
   }
   /**
    * draws the outline for each Hexbutton with Button Quadrant
    */
   public void drawHexButtons(){
      for (ButtonQuadrant b: boards) {
         double x = b.startX;
         double y = b.startY;
         HexagonButton [][] board = b.getBoard();
         for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
               //CONDITION IF HEX IS ENABLED IN MATRIX.
               if (r % 2 == 0) {
                  //board[r][c].setColor(Color.RED);
                  board[r][c].setBounds((int) (x + c * 41.2), (int) y, 46, 46);
               } else {
                  //board[r][c].setColor(Color.yellow);
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
    *             send in a hex button to set up its action listener function - for every he button clicked
    *             the console will print out the quad number, the row, and the column of the hex
    */
   public void setUpBoardHexes(HexagonButton temp) {
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Hex Button clicked " + temp + "  ");

         }

      });
   }
   public void setUpMenu(TranslucentButton temp) {
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Menu clicked ");

         }
      });
   }
   public void setUpFinish(TranslucentButton temp) {
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Finish clicked ");

         }
      });
   }
   public void setUpObjective(TranslucentButton[] arr) {
      for (int i = 0; i< 3; i++) {
         TranslucentButton temp = arr[i];
         add(temp);

         // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
         temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("char clicked ");

            }
         });
      }
   }


   @Override
   public void actionPerformed(ActionEvent e) {

   }
}