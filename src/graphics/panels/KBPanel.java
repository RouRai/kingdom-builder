package graphics.panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import logic.constantFolder.Constants;
import files.QuadrantMaker;
import logic.gameLogic.Board;
import logic.gameLogic.Game;
import logic.gameLogic.Player;
import logic.tiles.ActionTile;
import logic.tiles.TerrainTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
public class KBPanel extends JPanel implements ActionListener{
   //Images

   private BufferedImage background, highlight;
   private TranslucentButton menuButton, finishButton;
   private TranslucentButton[] objectivesButton;
   private BufferedImage [] objectiveCardImages;
   private HexagonButton[] currentActions;
   private ButtonQuadrant[] buttonBoards;
   private Graphics2D g2;
   private ArrayList <BufferedImage> boardImages;
   private TerrainTile[][][] boardText;
   private CardLayout cardLay;
   private Constants constantClass;
   private final String fontStr = "Lucida Calligraphy";
   private QuadrantMaker b1;
   private Board board;
   private Game game;

   private Boolean fileCheckDot_Switch = false;


   public KBPanel (CardLayout cl){

      //1 - card layout
            cardLay = cl;
            constantClass = new Constants();

      /*2- Game Essentials
            players = new ArrayList<>();
            terrainDeck = new TerrainDeck();
            terrainCards = terrainDeck.getTerrainDeck();
            for(int i = 0; i < 4; i++)
               players.add(new Player(i + 1));
               */
       

      //3 - Board
            buttonBoards = new ButtonQuadrant[4];
            boardImages = new ArrayList<>();
            boardText = new TerrainTile [4][10][10];
            // generating random boards
            int [] boardNumbers = new int [4];
            ArrayList<QuadrantMaker> boardMaker = new ArrayList<>();
            for(int i = 0; i < 4; i++){
               TerrainTile [][] temp = new TerrainTile[10][10];
               int rand = 0;
               do {
                  rand = (int) (Math.random() * (2 * Constants.getBoards().length));
                  boardNumbers[i] = rand;
               }while(Constants.getBoards()[rand % 8] == null);
               boardMaker.add(new QuadrantMaker(rand));
               int boardNum = rand % 8;
               if(rand < Constants.getBoards().length){
                  boardImages.add(Constants.getBoards()[boardNum]);
                  Constants.getBoards()[boardNum] = null;
                  Constants.getFlippedBoards()[boardNum] = null;
                  temp = boardMaker.get(i).getTerrainTiles();
               } else {
                  boardImages.add(Constants.getFlippedBoards()[boardNum]);
                  Constants.getBoards()[boardNum] = null;
                  Constants.getFlippedBoards()[boardNum] = null;
                  temp = boardMaker.get(i).getTerrainTiles();
               }
               boardText[i] = temp;
            }

      //4 Buttons
         //1 - Current Player's Action Tiles Buttons
               currentActions = new HexagonButton[4];
               for(int i = 0; i < 4; i++){
                  HexagonButton temp = new HexagonButton(i,-1,-1,null);
                  setUpCurrentAction(temp);
                  currentActions[i] = temp;
               }
         //2 - Hexagon Buttons
               int[] boardStartX = {10,423,10,423};
               int[] boardStartY = {6,6,365,365};
               for (int q = 0; q < 4; q++) {
                  HexagonButton[][] tempBoard = new HexagonButton[10][10];
                  for (int r = 0; r < 10; r++) {
                     for (int c = 0; c < 10; c++) {
                        if(boardText[q][r][c] != null){
                           tempBoard[r][c] = new HexagonButton(q, r, c, boardText[q][r][c].getType());
                        }
                        setUpBoardHexes(tempBoard[r][c]);
                     }
                  }
                  buttonBoards[q] = new ButtonQuadrant(q,tempBoard, boardStartX[q],boardStartY[q]);
               }
         //3 - Menu Buttons
            menuButton = new TranslucentButton();
            add(menuButton);
            menuButton.addActionListener(this);

         //4 - Finish Button
            finishButton = new TranslucentButton();
            add(finishButton);
            finishButton.addActionListener(this);

         //5 - Objective Buttons
            objectiveCardImages = new BufferedImage[3];
            objectivesButton = new TranslucentButton[3];
            for(int i = 0; i < 3; i++){
               int rand = (int) (Math.random() * (Constants.getCharCards().length));
               objectiveCardImages[i] = Constants.getCharCards()[rand];
               objectivesButton[i] = new TranslucentButton(i);
               setUpObjective(objectivesButton[i]);
            }

         //6 - GAME
               game = new Game (boardNumbers);

         // OTHER STUFF
               setUpMiscellaneous ();
   }

   /**
    * main paint method which calls other paint methods & sets the coordinates of some utility buttons
    * @param g the <code>Graphics</code> object to protect
    */
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
         objectivesButton[p].setBounds(330 + p * 150, 735, 120, 100);
      }
      int i = 0;
      while(i<4) {
         if (i % 2== 0)
            currentActions[i].setBounds(1005+ i * 2, 515+ i * 75, 80, 85);
         else
            currentActions[i].setBounds(959+ i *1, 515+ i * 75, 80, 85);
         i++;
      }
   }

   /**draws the boards
    * display method which paints the four boards as stated in the boar images arraylist
    */
   public void drawLeftPanel(){
      for (int i = 0; i < 3; i++)
         g2.drawImage(objectiveCardImages[i], 325+ i * 150, 735, 130, 240, null);
      for (int i = 0; i < 4; i++){
         ButtonQuadrant b = buttonBoards[i];
         double x = b.startX;
         double y = b.startY;
         g2.drawImage(boardImages.get(i),(int)x+2, (int)y-1,435, 369, null);
      }
   }

   /** draws the other players in the top right
    *  no buttons or any interactions - purely display
    */
   public void drawOtherPlayer(){
      int space_between_Players = 123;
      ArrayList <Player> players = game.getAllPlayers();
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
         if(players.get(i + 1).getPlayerNumber() == 4){
            g2.setColor(Color.BLACK);
         }
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
      g2.drawString("" + game.getCurrentPlayer().getPlayerNumber(),1185,460);

      //action tiles
      g2.setFont(new Font(fontStr, Font.PLAIN, 20));
      g2.setColor(Color.white);
      Set tiles = game.getCurrentPlayer().getActionTiles().keySet();
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
      //action tile selected - this part shows the hint when using the action tile
      if (game.getCurrentPlayer().isUsingActionTile()){
         g2.drawImage(constantClass.getActionProcess()[1], 1135, 645, 150, 60, null);
      }
      //landscape card drawn by the current player
      g2.drawImage(game.getCurrentPlayer().getCard().image(), 1335, 530, 130, 200, null);

      //settlement icon - based on color
      g2.drawImage(constantClass.getSettlements()[game.getCurrentPlayer().getPlayerNumber() - 1], 1330, 410, 120, 100, null);
      //settlement number
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      if(game.getCurrentPlayer().getPlayerNumber() == 4){
         g2.setColor(Color.BLACK);
      }
      g2.drawString("" + game.getCurrentPlayer().getSettlementsRemaining(),1365,480);
   }

   /**
    * draws the outline for each Hexbutton & the settlement (if applicable) with Button Quadrant
    */
   public void drawHexButtons(){
      for (ButtonQuadrant b: buttonBoards) {
         double x = b.startX;
         double y = b.startY;
         HexagonButton [][] board = b.getBoard();
         for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
               //CONDITION IF HEX IS ENABLED IN MATRIX.
               if(board[r][c] != null){
                  if (r % 2 == 0) {
                     board[r][c].setBounds((int) (x + c * 41.2), (int) y, 46, 46);
                  } else {
                     board[r][c].setBounds((int) (x + 21 + c * 41.3), (int) y, 46, 46);
                  }
                  //if(board[r][c].tile)
                  if(!game.getCurrentPlayer().hasPlacedSettlements() && !game.getCurrentPlayer().isUsingActionTile() && !(game.getCurrentPlayer().getSettlementsRemaining() == 0)) {
                     board[r][c].drawHighlight(g2, highlight, game.getCurrentPlayer().getCard());
                  }
                  board[r][c].drawSettlement(g2);

                  // this condition checks the file - JUST LEAVE IT HERE
                  if (fileCheckDot_Switch) drawDotChecker(r, c, board);
               }
            }
            y += 35.5;
         }
      }
   }

   /**
    * @param temp hexbutton
    *             send in a hex button to set up its action listener function - for every hex button clicked
    *             the console will print out the quad number, the row, and the column of the hex
    */
   public void setUpBoardHexes(HexagonButton temp) {
      if(temp == null){
         return;
      }
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Hex Button clicked " + temp + "  ");
            game.checkRegularSettlementPlacement(game.getCurrentPlayer(), temp);

            if (game.getCurrentPlayer().getNumSettlementsPlaced()!=3){
               System.out.println("player has started regular settlement");

               //setRegularAdjacent(game.getCurrentPlayer(), temp);
            }

            repaint();
         }
      });
   }
   //git iogkjsdifghsdfgnijadfng
   private void setRegularAdjacent(Player player, HexagonButton button){
      // right, right bottom, left bottom, left, left top, right top
      int [] rowOp = {0,1,1,0,-1,-1};
      int [] colOp = {1,1,0,-1,0,1};

      int q = button.getquadNum();
      int r = button.getRow();
      int c = button.getCol();
      Boolean []arr = new Boolean[6];
      System.out.println(" Player has "+ player.getCard().type());
      for (int i = 0; i<6; i++){
         Boolean bool = false;
         int checkedR = r+rowOp[i];
         int checkedC = c+colOp[i];
         if (q > 1)
            checkedR+= 10;
         if (q == 1 || q == 3)
            checkedC+= 10;

            bool = (checkedR < 20 && checkedR >=0 && checkedC < 20 && checkedC >=0) ;
            Boolean type = player.getCard().type().toString().equals(game.getBoard().getBoard().getTerrainBoardMatrix()[checkedR][checkedC].getType().toString());
         System.out.println(player.getCard().type().toString() + " vs. "+ game.getBoard().getBoard().getTerrainBoardMatrix()[checkedR][checkedC].getType().toString());
         System.out.println(""+ " - ("+ checkedR + " , " + checkedC + ") to "+" "+bool + " " + type);
         if (bool && type){
            int R = checkedR;
            int C = checkedC;
            int Q = 0;
            if (checkedR >=10){
               R-=10;
               Q+=2;
            }
            if (checkedC >=10){
               C-=10;
               Q+=1;
            }
            buttonBoards[Q].getBoard()[R][C].canClick = true;
            System.out.println("setting" + Q + " - ("+ R + " , " + C + ") to "+ bool);
         }
         arr[i]= bool;
      }
      button.setAdjacents(arr);
   }
   public void setUpObjective (TranslucentButton temp){
      add(temp);
      temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              System.out.println("Objective Button clicked " + temp + "  ");
              repaint();
            }
      });
   }
   public void setUpCurrentAction (HexagonButton temp){
      add(temp);
      temp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Current Action Tile Button clicked -" + temp + "  ");
            game.getCurrentPlayer().setUsingActionTile(true);
            game.getCurrentPlayer().setPlacingRegSettlements(false);
            repaint();
         }
      });
   }



   /**method that checks if current player can end their turn;
    *
    * @return
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(menuButton)){
         cardLay.show(Constants.PANEL_CONT, Constants.MENU_PANEL);
      } else if(e.getSource().equals(finishButton)){
         if(game.canEndTurn()) {
            if (game.getCurrentPlayer().getPlayerNumber() == 4) {
               //make sure to check this later
               if (game.checkEndGame())
                  cardLay.show(Constants.PANEL_CONT, Constants.END_PANEL);
            }
            game.endTurn();
         }
      }
      repaint();
   }

   /**
    * adds a mouse listener which returns the specific coordinates of a click
    * checks the correctness of a text file according to its color on the screen
    *    change the number "n" if you want to check a specific file
    */
   public void setUpMiscellaneous(){
      try{
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play2.png"));
         highlight = ImageIO.read(getClass().getResource("/images/graphicsExtra/Hex.png"));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }

      String [] boardNames = {"beach", "boat", "farm", "paddock", "house", "oracle", "tower", "tavern"};
      // type in the board you want to check corresponding to the string array above
      int n = 7;
      b1 = new QuadrantMaker(n);
      // for coordinates
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
         }});

      //doesn't work
      addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
             if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                System.out.println("escape clicked");
             }
          }
       }
      );
   }

   /**
    * draws the dots for a file checker - not exactly necessary for the game, this method paints a
    * colored dot on each tile based on files that were read in. The dot checker in general is an
    * extra thing I made to check if i did the files correctly.
    * **/
   public void drawDotChecker(int r, int c, HexagonButton [][] board){
      if (b1.getTiles()[r][c]!= null){
         switch (b1.getTiles()[r][c]){
            case "CANYON": g2.setColor(new Color(100,67,81)); break;
            case "DESERT": g2.setColor(new Color(251,200,39));break;
            case "FLOWER_FIELD": g2.setColor(new Color(215,168,173));break;
            case "FOREST": g2.setColor(new Color(29,94,40));break;
            case "GRASS": g2.setColor(new Color(134,176,73));break;
            case "MOUNTAIN": g2.setColor(new Color(165,170,180));break;
            case "WATER": g2.setColor(new Color(91,139,182));break;
            case "CITY":g2.setColor(new Color(251,10,81));break;
         }}
      else
         g2.setColor(Color.WHITE);
      g2.fillOval(board[r][c].getX() +10,board[r][c].getY()+30,20,20);
   }
}