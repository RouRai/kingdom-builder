package graphics.panels;

import custom.ActionProcessButton;
import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import datastructures.gameDatastructures.boardNodes.TerrainNode;
import logic.cards.TerrainCard;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;
import logic.gameLogic.Player;
import logic.tiles.ActionTile;
import logic.tiles.actionAdjacencies.ActionProcess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
public class KBPanel extends JPanel implements ActionListener{
   //Images

   private BufferedImage background, highlight, firstMarker;
   private ArrayList<TerrainNode> legalPlaces;
   private TranslucentButton menuButton, finishButton,endGameScreen;
   private TranslucentButton[] objectivesButton;
   private ActionProcessButton[] currentActions;
   private final ButtonQuadrant[] buttonBoards;
   private Graphics2D g2;
   private int [] boardNumbers, objectiveNumbers;
   private final ArrayList <BufferedImage> boardImages, objectiveCardImages;
   private final CardLayout cardLay;
   private final String fontStr = "Lucida Calligraphy";
   private Game game;
   private ActionProcessButton inUse;
   private Boolean clickedOnActionOnBoard = false;
   private HexagonButton actionClicked;
   private int currentAction;

   public KBPanel (CardLayout cl, Game g ){
      cardLay = cl;
      game = g;

      buttonBoards = new ButtonQuadrant[4];
      boardImages = new ArrayList<>();
      objectiveCardImages = new ArrayList<>();
      boardNumbers = g.getBoardNumbers();
      objectiveNumbers = g.getObjectiveNumbers();

      setUpBoardImages();

      int[] boardStartX = {10,423,10,423};
      int[] boardStartY = {6,6,365,365};
      assignToAllButtonQuadrants(boardStartX, boardStartY);

      menuButton = new TranslucentButton();
      add(menuButton);
      menuButton.addActionListener(this);

      finishButton = new TranslucentButton();
      add(finishButton);
      finishButton.addActionListener(this);

      endGameScreen = new TranslucentButton();
      add(endGameScreen);
      endGameScreen.addActionListener(this);

      setUpObjectiveButtons();

      setUpMiscellaneous ();
      setUpActionTileHexagonButtons();
   }
   private void setUpBoardImages(){
      for(int i = 0; i < 4; i++) {
         int rand = boardNumbers[i];
         int boardNum = rand % 8;
         if (rand < Constants.getBoards().length)
            boardImages.add(Constants.getBoards()[boardNum]);
         else
            boardImages.add(Constants.getFlippedBoards()[boardNum]);
      }
   }

   /**
    * Sets up both the images and buttons for the objective buttons
    */
   private void setUpObjectiveButtons() {
      objectivesButton = new TranslucentButton[3];
      for(int i = 0; i < 3; i++){
         int rand = objectiveNumbers[i];
         objectiveCardImages.add(Constants.getCharCards()[rand]);
         Constants.getCharCards()[rand] = null;
         objectivesButton[i] = new TranslucentButton(i);
         setUpObjective(objectivesButton[i]);
      }
   }

   /**
    * Sets up the <code>HexagonButton</code> that let the player use actionTiles
    */
   private void setUpActionTileHexagonButtons() {
      currentActions = new ActionProcessButton[4];
      for(int i = 0; i < 4; i++){
         ActionProcessButton temp = new ActionProcessButton(boardNumbers[i]);
         setUpCurrentAction(temp);
         currentActions[i] = temp;
         for(int q = 0; q < 4; q++){
            game.getAllPlayers().get(q).addToHashMap(currentActions[i].getType());
         }
      }
   }
   public void paintComponent(Graphics g)
   {
      g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      super.paintComponent(g2);
      g2.drawImage(background,0, 0, Constants.WIDTH, Constants.HEIGHT-8, null);
      g2.setBackground(Color.BLACK);
      drawLeftPanel();
      drawHexButtons();
      drawCurrentPlayer();
      drawOtherPlayer();

      menuButton.setBounds(785, 770, 70, 65);
      finishButton.setBounds(1310, 745, 180, 65);
      endGameScreen.setBounds(1100, 745, 90, 65);
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
   public void drawLeftPanel(){
      for (int i = 0; i < 3; i++)
         g2.drawImage(objectiveCardImages.get(i), 325+ i * 150, 735, 130, 240, null);

      for (int i = 0; i < 4; i++){
         ButtonQuadrant b = buttonBoards[i];
         double x = b.startX;
         double y = b.startY;
         g2.drawImage(boardImages.get(i),(int)x+2, (int)y-1,435, 369, null);
      }
   }
   public void drawOtherPlayer(){
      int space_between_Players = 123;
      ArrayList <Player> players = game.getAllPlayers();
      for (int i =0; i < 3; i++) {
         //Player p = player example array [i];
         //number
         g2.setColor(Color.black);
         g2.setFont(new Font(fontStr, Font.PLAIN, 40));
         if(players.get(i+1).getPlayerNumber() == 1)
            g2.drawImage(firstMarker,913,i*space_between_Players-2,47,43,null);
         if (i%2 ==0)
            g2.drawString("" + game.getAllPlayers().get(i + 1).getPlayerNumber(), 1040, 85+space_between_Players*i);
         else
            g2.drawString("" + game.getAllPlayers().get(i + 1).getPlayerNumber(), 1040, 90+space_between_Players*i);
         //action tiles
         g2.setFont(new Font(fontStr, Font.PLAIN, 20));
         g2.setColor(Color.white);
         for (int j = 0; j < 4; j++) {
            int x = 1230;
            int y = 35;
            g2.drawImage(currentActions[j].getFront(), x-25+j *68, y-5 + i * space_between_Players, 60, 60, null);
            if (i%2 ==0)
               g2.drawString("" + game.getAllPlayers().get(i + 1).getActionTiles().get(currentActions[j].getType()), x+j *69,  y+80+i  * space_between_Players);
            else
               g2.drawString("" + game.getAllPlayers().get(i + 1).getActionTiles().get(currentActions[j].getType()), x+j *69,  y+80+i  * (space_between_Players+8));

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
   public void drawCurrentPlayer(){
      //Player p = null;
      //cardcount
      g2.setColor(Color.black);
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      int count = 25-(game.turns%25);
      g2.drawString(""+count,1256,725);

      //number
      g2.setColor(Color.black);
      g2.setFont(new Font(fontStr, Font.PLAIN, 50));
      g2.drawString("" + game.getCurrentPlayer().getPlayerNumber(),1185,460);
      if(game.getCurrentPlayer().getPlayerNumber() == 1)
         g2.drawImage(firstMarker,913,392,50,48,null);

      //action tiles
      g2.setFont(new Font(fontStr, Font.PLAIN, 20));
      g2.setColor(Color.white);
      int i = 0;
      while(i<4) {
         if (i % 2== 0) {
            g2.drawImage(currentActions[i].getFront(), 1005+ i * 2, 515+ i * 75, 80, 85 , null);
            g2.drawString("" + currentActions[i].getNumTiles(),980,560+ i * 75);
         }
         else{
            g2.drawImage(currentActions[i].getFront(), 959+ i, 515+ i * 75, 80, 85 , null);
            g2.drawString("" + currentActions[i].getNumTiles(),1055,560+ i * 75);
         }
         i++;
      }
      //action tile selected - this part shows the hint when using the action tile
      if (game.getCurrentPlayer().isUsingActionTile()) {
         g2.drawImage(inUse.getProcess(), 1135, 570, 150, 60, null);
         //legalPlaces = game.getLegalActionPlaces();
      }
      else if(clickedOnActionOnBoard && game.getBoard().getActionBoard().getBoardMatrix()[actionClicked.getTrueRow()][actionClicked.getTrueCol()] != null){
         g2.drawImage(Constants.getActionTiles()[boardNumbers[actionClicked.getquadNum()] % 8],1165,570,75,80,null);
         ActionTile tile  = game.getBoard().getActionBoard().getBoardMatrix()[actionClicked.getTrueRow()][actionClicked.getTrueCol()].getTile();
         g2.setFont(new Font(fontStr, Font.PLAIN, 18));
         g2.drawString("Has "+ tile.getCount() + " Tiles",1150,665);
         clickedOnActionOnBoard = false;

      }
      //landscape card drawn by the current player
      //System.out.println(game.getCurrentPlayer().getCard());
      TerrainCard tempc = game.getCurrentPlayer().getCard();
      BufferedImage temp = game.getCurrentPlayer().getCard().image();
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
   public void setUpActionHexes(HexagonButton temp) {
      if(temp == null)
         return;

      add(temp);
      temp.addActionListener(e -> {
         System.out.println("=========        Action Button clicked " + temp + "  ");
         clickedOnActionOnBoard = !clickedOnActionOnBoard;
         actionClicked = temp;
         repaint();

      });
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
                  int i = 0;
                  while(legalPlaces == null /*&& !game.getCurrentPlayer().hasPlacedSettlements()*/){
                      if(i > 3){
                         i = 0;
                         game.getCurrentPlayer().setCard(game.getCard());
                      }
                       legalPlaces = game.getLegalRegularPlaces();
                       i++;
                  }
                  if(game.getCurrentPlayer().isUsingActionTile()){
                     legalPlaces = game.getLegalActionPlaces(inUse.getType());
                  }
                  if(legalPlaces.contains(game.getBoard().getTerrainBoard().getBoardMatrix()[tempr][tempc])) {
                     if(!game.getCurrentPlayer().hasPlacedSettlements() || game.getCurrentPlayer().isUsingActionTile()){
                        board[r][c].drawHighlight(g2, highlight, game.getCurrentPlayer().getCard());
                     }
                  }
                  board[r][c].drawSettlement(g2);
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
      if(temp == null)
         return;

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
          if(quad == 1 || quad == 3)
              tempc = temp.getCol() + 10;

         if(legalPlaces.contains(game.getBoard().getTerrainBoard().getBoardMatrix()[tempr][tempc])){
            game.checkRegularSettlementPlacement(game.getCurrentPlayer(), temp, inUse);
         }

         if (game.getCurrentPlayer().getNumSettlementsPlaced()!=3){
            System.out.println("player has started regular settlement");
            //game.getCurrentPlayer().setUsingActionTile(false);
         }
         //action tile processes
         if (!game.getCurrentPlayer().isUsingActionTile()){
            inUse = null;
            //game.getCurrentPlayer().setPlacingRegSettlements(false);
         }
         game.setButtonBoard(buttonBoards);
         if(inUse == null)
            legalPlaces = game.getLegalRegularPlaces();
         reassignActionButtonNumTiles();
         repaint();
      });
   }
   public void setUpObjective (TranslucentButton temp){
      add(temp);
      temp.addActionListener(e -> {
        System.out.println("Objective Button clicked " + temp + "  ");
         cardLay.show(Constants.PANEL_CONT, Constants.CARD_PANEL);
        repaint();
      });
   }
   public void setUpCurrentAction (ActionProcessButton temp){
      add(temp);
      temp.addActionListener(e -> {
         System.out.println("Current Action Tile Button clicked -" + temp + "  ");
         if(!game.getCurrentPlayer().isPlacingRegSettlements() && temp.getNumUses() > 0 && game.getLegalActionPlaces(temp.getType()) != null){
            currentAction = temp.getquadNum();
            game.getCurrentPlayer().setUsingActionTile(true);
            game.getCurrentPlayer().setPlacingRegSettlements(false);
            inUse = temp;
            temp.reduceNumUses();
           // game.checkActionTilePlacement(game.getCurrentPlayer(), temp);
         }

         repaint();
      });
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(menuButton))
         cardLay.show(Constants.PANEL_CONT, Constants.MENU_PANEL);

      else if(e.getSource().equals(finishButton)){
         if(game.canEndTurn()) {
            if (game.getCurrentPlayer().getPlayerNumber() == 4) {
               //make sure to check this later
               if (game.checkEndGame()){
                  //EndPanel.setGame(game);
                  cardLay.show(Constants.PANEL_CONT, Constants.END_PANEL);
               }
            }
            game.endTurn();
            reassignActionButtonNumTiles();
            for(int i = 0; i < 4; i++){
               currentActions[i].resetNumUses();
            }
            legalPlaces = game.getLegalRegularPlaces();
         }
      }
      else if(e.getSource().equals(endGameScreen)){
         cardLay.show(Constants.PANEL_CONT, Constants.END_PANEL);
      }
      repaint();
   }
   private void reassignActionButtonNumTiles(){
      for(int i = 0; i < 4; i++){
         currentActions[i].setNumTiles(game.getCurrentPlayer().getActionTiles().get(currentActions[i].getType()));
      }
   }
   /**
    * Assigns the <code>HexagonButton</code> for a single quadrant
    * @param quadrantButtons Empty <code>HexagonButton</code> matrix to assign buttons to the quadrant
    * @param quadrantNumber Number for quadrant
    */
   private void assignButtonsToQuadrant(HexagonButton[][] quadrantButtons, int quadrantNumber){
      for (int r = 0; r < 10; r++) {
         for (int c = 0; c < 10; c++) {
            int tempr = r;
            int tempc = c;
            if(quadrantNumber == 2 || quadrantNumber == 3)
               tempr += 10;

            if(quadrantNumber == 1 || quadrantNumber == 3)
               tempc += 10;
            if(game.getTerrainMatrix()[tempr][tempc] != null) {
               quadrantButtons[r][c] = new HexagonButton(quadrantNumber, r, c, game.getTerrainMatrix()[tempr][tempc].getTile());
               setUpBoardHexes(quadrantButtons[r][c]);
            }
            if (game.getActionMatrix()[tempr][tempc] != null){
               quadrantButtons[r][c] = new HexagonButton(quadrantNumber, r, c, game.getBoard().getActionBoard().getBoardMatrix()[tempr][tempc].getTile());
               setUpActionHexes(quadrantButtons[r][c]);
            }
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


   public void setUpMiscellaneous(){
      try{
         background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/gamePLAY.png")));
         highlight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/graphicsExtra/Hex.png")));
         firstMarker = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/1st player.png")));
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
   }
}