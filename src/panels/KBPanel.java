package panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import game.Constants;
import logic.gameLogic.Player;
import logic.tiles.ActionTile;
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
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class KBPanel extends JPanel implements ActionListener {
   //Images

   private BufferedImage background, highlight;
   private TranslucentButton menuButton, finishButton;
   private TranslucentButton[] objectivesButton;
   private ButtonQuadrant[] boards;
   private Graphics2D g2;
   private ArrayList <BufferedImage> boardImages;
   private CardLayout cardLay;
   private Constants constantClass;
   private final String fontStr = "Lucida Calligraphy";
   private ArrayList<Player> players;

   public KBPanel (CardLayout cl){
      //setLayout(null);
      cardLay = cl;
      constantClass = new Constants();
      players = new ArrayList<>();
      for(int i = 0; i < 4; i++){
         players.add(new Player(i + 1));
      }

      // for coordinates
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
         }});
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
      menuButton = new TranslucentButton();
      finishButton = new TranslucentButton();
      add(menuButton);
      add(finishButton);
      menuButton.addActionListener(this);
      finishButton.addActionListener(this);
      objectivesButton = new TranslucentButton[3];
      for(TranslucentButton b: objectivesButton){
         b = new TranslucentButton();
         add(b);
         b.addActionListener(this);
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
      //3- drawing current player attributes
      drawCurrentPlayer();
      drawOtherPlayer();
      //4- drawing


      //Functionality buttons
      menuButton.setBounds(785, 770, 70, 65);
      finishButton.setBounds(1310, 745, 180, 65);
      for(int p = 0; p < 3; p++){
         objectivesButton[p].setBounds(700 + p * 200, 700, 200, 100);
      }
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
            g2.drawString("" + players.get(i + 1).getPlayerNumber(), 1040, 85+space_between_Players*i);
         else
            g2.drawString("" + players.get(i + 1).getPlayerNumber(), 1040, 90+space_between_Players*i);
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
         g2.drawImage(constantClass.getSettlements()[players.get(i + 1).getPlayerNumber() - 1], 1100, 40 +i * space_between_Players, 90, 70, null);
         //settlement number
         g2.setFont(new Font(fontStr, Font.PLAIN, 30));
         g2.drawString("" + players.get(i + 1).getSettlementsRemaining(), 1125, 90 + i * space_between_Players);
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
      g2.drawString("" + players.get(0).getPlayerNumber(),1185,460);
      //action tiles
      g2.setFont(new Font(fontStr, Font.PLAIN, 20));
      g2.setColor(Color.white);
      Set tiles = players.get(0).getActionTiles().keySet();
      Iterator<ActionTile> iter = tiles.iterator();
      int i = 0;
      while(iter.hasNext()) {
         ActionTile temp = iter.next();
         if (i % 2== 0) {
            g2.drawImage(constantClass.getActionTiles()[0], 1005+ i * 2, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",980,560+ i * 75);
         }
         else{
            g2.drawImage(constantClass.getActionTiles()[0], 959+ i *1, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",1055,560+ i * 75);
         }
         i++;
      }
      //action tile selected
      if (true){
         g2.drawImage(constantClass.getActionProcess()[1], 1135, 645, 150, 60, null);
      }
      //landscape card
      g2.drawImage(constantClass.getLandCards()[0], 1335, 530, 130, 200, null);

      //settlement
      //settlement icon
      g2.drawImage(constantClass.getSettlements()[players.get(0).getPlayerNumber() - 1], 1330, 410, 120, 100, null);
      //settlement number
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      g2.drawString("" + players.get(0).getSettlementsRemaining(),1365,480);
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
               board[r][c].drawHighlight(g2, highlight);
               //gameButton.setIcon(icon);
            }
            y += 35.5;
         }
      }
   }
   public void endTurn(){
      Player temp = players.get(0);
      players.remove(0);
      players.add(temp);
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
   //method that checks if current player can end their turn;
   private boolean canEndTurn(){
      return true;
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(menuButton)){
         cardLay.show(Constants.PANEL_CONT, Constants.MENU_PANEL);
      } else if(e.getSource().equals(finishButton)){
         if(canEndTurn())
            endTurn();
      }
      repaint();
   }
}