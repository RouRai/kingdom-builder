package graphics.panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import datastructures.gameDatastructures.TerrainNode;
import logic.constantFolder.Constants;
import files.QuadrantMaker;
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
import java.util.Objects;
import java.util.Set;
import javax.swing.JPanel;
public class KBPanel extends JPanel implements ActionListener{
   //Images

   private BufferedImage background, highlight;
   private ArrayList<TerrainNode> legalPlaces;
   private final TranslucentButton menuButton, finishButton;
   private TranslucentButton[] objectivesButton;
   private BufferedImage [] objectiveCardImages;
   private HexagonButton[] currentActions;
   private final ButtonQuadrant[] buttonBoards;
   private Graphics2D g2;
   private final ArrayList<Integer> boardIDNumbers;
   private final ArrayList <BufferedImage> boardImages;
   private final TerrainTile[][][] boardText;
   private final CardLayout cardLay;
   private final String fontStr = "Lucida Calligraphy";
   private final Game game;


   public KBPanel (CardLayout cl){
      cardLay = cl;

      buttonBoards = new ButtonQuadrant[4];
      boardImages = new ArrayList<>();
      boardIDNumbers = new ArrayList<>();
      boardText = new TerrainTile [4][10][10];
      int [] boardNumbers = new int [4];
      for(int i = 0; i < 4; i++){
         boardNumbers[i] = setUpBoardImages();
         setUpBoardValues(boardText, boardNumbers[i], i);
      }

      setUpActionTileHexagonButtons();
      int[] boardStartX = {10,423,10,423};
      int[] boardStartY = {6,6,365,365};
      assignToAllButtonQuadrants(boardStartX, boardStartY);

      menuButton = new TranslucentButton();
      add(menuButton);
      menuButton.addActionListener(this);

      finishButton = new TranslucentButton();
      add(finishButton);
      finishButton.addActionListener(this);

      setUpObjectiveButtons();

      game = new Game (boardNumbers);

      setUpMiscellaneous ();
   }

   /**
    * Sets up both the images and buttons for the objective buttons
    */
   private void setUpObjectiveButtons() {
      objectiveCardImages = new BufferedImage[3];
      objectivesButton = new TranslucentButton[3];
      for(int i = 0; i < 3; i++){
         int rand;
         do {
            rand = (int) (Math.random() * (Constants.getCharCards().length));
         }while(Constants.getCharCards()[rand] == null);
         objectiveCardImages[i] = Constants.getCharCards()[rand];
         Constants.getCharCards()[rand] = null;
         objectivesButton[i] = new TranslucentButton(i);
         setUpObjective(objectivesButton[i]);
      }
   }

   /**
    * Sets up the <code>HexagonButton</code> that let the player use actionTiles
    */
   private void setUpActionTileHexagonButtons() {
      currentActions = new HexagonButton[4];
      for(int i = 0; i < 4; i++){
         HexagonButton temp = new HexagonButton(i,-1,-1,null);
         setUpCurrentAction(temp);
         currentActions[i] = temp;
      }
   }

   /**
    * Sets up the board values in <code>TerrainNode</code>
    * @param boardText Where the values for the boards will be set
    * @param boardNumber board number out of 8
    * @param i index
    */
   private void setUpBoardValues(TerrainTile[][][] boardText, int boardNumber, int i) {
      QuadrantMaker temp = new QuadrantMaker(boardNumber);
      boardText[i] = temp.getTerrainTiles();
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
            currentActions[i].setBounds(959+ i, 515+ i * 75, 80, 85);
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
            g2.drawImage(Constants.getActionTiles()[boardIDNumbers.get(j)], x-25+j *68, y-5 + i * space_between_Players, 60, 60, null);
            if (i%2 ==0)
               g2.drawString("0", x+j *65,  y+80+i  * space_between_Players);
            else
               g2.drawString("0", x+j *65,  y+80+i  * (space_between_Players+8));

         }
         //settlement
         //settlement icon
         g2.drawImage(Constants.getSettlements()[players.get(i + 1).getPlayerNumber() - 1], 1100, 40 +i * space_between_Players, 90, 70, null);
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
      Set<ActionTile> tiles = game.getCurrentPlayer().getActionTiles().keySet();
      int i = 0;
      while(i<4) {
         if (i % 2== 0) {
            g2.drawImage(Constants.getActionTiles()[boardIDNumbers.get(i)], 1005+ i * 2, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",980,560+ i * 75);
         }
         else{
            g2.drawImage(Constants.getActionTiles()[boardIDNumbers.get(i)], 959+ i, 515+ i * 75, 80, 85 , null);
            g2.drawString("0",1055,560+ i * 75);
         }
         i++;
      }
      //action tile selected - this part shows the hint when using the action tile
      if (game.getCurrentPlayer().isUsingActionTile()){
         g2.drawImage(Constants.getActionProcess()[1], 1135, 645, 150, 60, null);
      }
      //landscape card drawn by the current player
      g2.drawImage(game.getCurrentPlayer().getCard().image(), 1335, 530, 130, 200, null);

      //settlement icon - based on color
      g2.drawImage(Constants.getSettlements()[game.getCurrentPlayer().getPlayerNumber() - 1], 1330, 410, 120, 100, null);
      //settlement number
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      if(game.getCurrentPlayer().getPlayerNumber() == 4){
         g2.setColor(Color.BLACK);
      }
      g2.drawString("" + game.getCurrentPlayer().getSettlementsRemaining(),1365,480);
   }

   /**
    * draws the outline for each <code>HexButton</code> & the settlement (if applicable) with Button Quadrant
    */
   public void drawHexButtons(){
      int quad = 0;
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
                  int tempr = r;
                  int tempc = c;
                  if(quad == 2 || quad == 3){
                     tempr = r + 10;
                  }
                  if(quad == 1 || quad == 3){
                     tempc = c + 10;
                  }
                  //if(board[r][c].tile)
                   while(legalPlaces == null && !game.getCurrentPlayer().hasPlacedSettlements()){
                       legalPlaces = game.getLegalPlaces();
                   }
                  if(legalPlaces.contains(game.getBoard().getBoard().getTerrainBoardMatrix()[tempr][tempc]) && !game.getCurrentPlayer().hasPlacedSettlements()) {
                     board[r][c].drawHighlight(g2, highlight, game.getCurrentPlayer().getCard());
                  }
                  board[r][c].drawSettlement(g2);

                  // this condition checks the file - JUST LEAVE IT HERE
               }
            }
            y += 35.5;
         }
         quad++;
      }
   }

   /**
    * @param temp <Code>HexButton</Code>
    *             send in a hex button to set up its action listener function - for every hex button clicked
    *             the console will print out the quad number, the row, and the column of the hex
    */
   public void setUpBoardHexes(HexagonButton temp) {
      if(temp == null){
         return;
      }
      add(temp);
      // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
      temp.addActionListener(e -> {
         System.out.println("Hex Button clicked " + temp + "  ");
          int quad = temp.getquadNum();
          int tempr = temp.getRow();
          int tempc = temp.getCol();
          if(quad == 2 || quad == 3){
              tempr = temp.getRow() + 10;
          }
          if(quad == 1 || quad == 3){
              tempc = temp.getCol() + 10;
          }
         if(legalPlaces.contains(game.getBoard().getBoard().getTerrainBoardMatrix()[tempr][tempc]))
             game.checkRegularSettlementPlacement(game.getCurrentPlayer(), temp);

         if (game.getCurrentPlayer().getNumSettlementsPlaced()!=3){
            System.out.println("player has started regular settlement");
            //game.checkRegularSettlementPlacement(game.getCurrentPlayer(), , game.getCurrentPlayer().getCard());
            //setRegularAdjacent(game.getCurrentPlayer(), temp);
         }
         legalPlaces = game.getLegalPlaces();
         repaint();
      });
   }
   public void setUpObjective (TranslucentButton temp){
      add(temp);
      temp.addActionListener(e -> {
        System.out.println("Objective Button clicked " + temp + "  ");
        repaint();
      });
   }
   public void setUpCurrentAction (HexagonButton temp){
      add(temp);
      temp.addActionListener(e -> {
         System.out.println("Current Action Tile Button clicked -" + temp + "  ");
         game.getCurrentPlayer().setUsingActionTile(true);
         game.getCurrentPlayer().setPlacingRegSettlements(false);
         repaint();
      });
   }



   /**
    * method that checks if current player can end their turn;
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
            legalPlaces = game.getLegalPlaces();
         }
      }
      repaint();
   }

   /**
    * Assigns the <code>HexagonButton</code> for a single quadrant
    * @param quadrantButtons Empty <code>HexagonButton</code> matrix to assign buttons to the quadrant
    * @param quadrantNumber Number for quadrant
    */
   private void assignButtonsToQuadrant(HexagonButton[][] quadrantButtons, int quadrantNumber){
      for (int r = 0; r < 10; r++) {
         for (int c = 0; c < 10; c++) {
            if(boardText[quadrantNumber][r][c] != null){
               quadrantButtons[r][c] = new HexagonButton(quadrantNumber, r, c, boardText[quadrantNumber][r][c]);
            }
            setUpBoardHexes(quadrantButtons[r][c]);
         }
      }
   }

   /**
    * Assigns the <code>HexagonButton</code> for all quadrants
    * @param boardStartX Array of the starting x-positions for all quadrants
    * @param boardStartY Array of the starting y-positions for all quadrants
    */

   private void assignToAllButtonQuadrants(int[] boardStartX, int[] boardStartY){
      for (int q = 0; q < 4; q++) {
         HexagonButton[][] tempBoard = new HexagonButton[10][10];
         assignButtonsToQuadrant(tempBoard, q);
         buttonBoards[q] = new ButtonQuadrant(q,tempBoard, boardStartX[q],boardStartY[q]);
      }
   }

   /**
    * Sets up the images for a board
    * @return the board number out of 16 to set up the values of the board
    */
   private int setUpBoardImages(){
      int rand;
      do {
         rand = (int) (Math.random() * (2 * Constants.getBoards().length));
      } while(Constants.getBoards()[rand % 8] == null);
      int boardNum = rand % 8;
      boardIDNumbers.add(boardNum);
      if(rand < Constants.getBoards().length){
         boardImages.add(Constants.getBoards()[boardNum]);
      }
      else {
         boardImages.add(Constants.getFlippedBoards()[boardNum]);
      }
      Constants.getBoards()[boardNum] = null;
      Constants.getFlippedBoards()[boardNum] = null;
      return rand;
   }

   /**
    * adds a mouse listener which returns the specific coordinates of a click
    * checks the correctness of a text file according to its color on the screen
    *    change the number "n" if you want to check a specific file
    */
   public void setUpMiscellaneous(){
      try{
         background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/game play2.png")));
         highlight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/graphicsExtra/Hex.png")));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }

      // type in the board you want to check corresponding to the string array above
      // for coordinates
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coordinate (" +e.getX()+ ", " +e.getY()+ ")");
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
}