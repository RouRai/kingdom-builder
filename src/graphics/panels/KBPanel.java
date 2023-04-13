package graphics.panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import custom.TranslucentButton;
import logic.cards.TerrainCard;
import logic.cards.TerrainDeck;
import logic.constantFolder.Constants;
import files.FileCheckerBoard;
import logic.gameLogic.Board;
import logic.gameLogic.Player;
import logic.tiles.ActionTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
public class KBPanel extends JPanel implements ActionListener {
   //Images

   private BufferedImage background, highlight;
   private TranslucentButton menuButton, finishButton;
   private TranslucentButton[] objectivesButton;
   private ButtonQuadrant[] boards;
   private Graphics2D g2;
   private ArrayList <BufferedImage> boardImages;
   private ArrayList <Board> actualBoards;
   private CardLayout cardLay;
   private Constants constantClass;
   private final String fontStr = "Lucida Calligraphy";
   private ArrayList<Player> players;
   private TerrainDeck terrainDeck;
   private ArrayList<TerrainCard> terrainCards;
   private FileCheckerBoard b1;

   private Boolean fileCheckDot_Switch = false;


   public KBPanel (CardLayout cl){
      //setLayout(null);
      cardLay = cl;
      constantClass = new Constants();
      players = new ArrayList<>();
      terrainDeck = new TerrainDeck();
      terrainCards = terrainDeck.getCards();
      for(int i = 0; i < 4; i++){
         players.add(new Player(i + 1));
         players.get(i).setCard(terrainCards.get(0));
         terrainCards.remove(0);
      }
      //Boards setup - see ButtonQuadrant class for more details
      boards = new ButtonQuadrant[4];
      int[] boardStartX = {10,423,10,423};
      int[] boardStartY = {6,6,365,365};
      boardImages = new ArrayList<>();
      actualBoards = new ArrayList<>();
      for(int i = 0; i < 4; i++){
         int rand = 0;
         do {
            rand = (int) (Math.random() * (2 * Constants.getBoards().length));
         }while(Constants.getBoards()[rand % 8] == null);
         if(rand < Constants.getBoards().length){
            boardImages.add(Constants.getBoards()[rand % 8]);
            Constants.getBoards()[rand % 8] = null;
            Constants.getFlippedBoards()[rand % 8] = null;
         } else {
            boardImages.add(Constants.getFlippedBoards()[rand % 8]);
            Constants.getBoards()[rand % 8] = null;
            Constants.getFlippedBoards()[rand % 8] = null;
         }
      }
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
      for(int i = 0; i < 3; i++){
         objectivesButton[i] = new TranslucentButton();
         add(objectivesButton[i]);
         objectivesButton[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
         });
      }
      // 1 -- BACKGROUND - BOTTOM LAYER
      try{
         background = ImageIO.read(getClass().getResource("/images/backgroundImages/game play2.png"));
         highlight = ImageIO.read(getClass().getResource("/images/graphicsExtra/Hex.png"));
      } catch (Exception ex) {
         System.out.println("----------------------------------------- Image Error -----------------------------------------");
      }
      setUpMiscellaneous ();
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
         objectivesButton[p].setBounds(330 + p * 150, 735, 120, 100);
      }
   }
   public void drawLeftPanel(){
      for (int i = 0; i < 3; i++)
         g2.drawImage(constantClass.getCharCards()[0], 325+ i * 150, 735, 130, 240, null);
      for (int i = 0; i < 4; i++){
         ButtonQuadrant b = boards[i];
         double x = b.startX;
         double y = b.startY;
         g2.drawImage(boardImages.get(i),(int)x+2, (int)y-1,435, 369, null);
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
      g2.drawImage(players.get(0).getCard().image(), 1335, 530, 130, 200, null);

      //settlement
      //settlement icon
      g2.drawImage(constantClass.getSettlements()[players.get(0).getPlayerNumber() - 1], 1330, 410, 120, 100, null);
      //settlement number
      g2.setFont(new Font(fontStr, Font.PLAIN, 35));
      if(players.get(0).getPlayerNumber() == 4){
         g2.setColor(Color.BLACK);
      }
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
               board[r][c].drawSettlement(g2);

               // this condition checks the file - JUST LEAVE IT HERE
              if (fileCheckDot_Switch) drawDotChecker(r, c, board);
            }
            y += 35.5;
         }
      }
   }
   public TerrainCard getCard(){
      if(terrainDeck.isEmpty()){
         terrainDeck = new TerrainDeck();
         terrainCards = terrainDeck.getCards();
         return getCard();
      }
      TerrainCard temp = terrainCards.get(0);
      terrainCards.remove(0);
      return temp;
   }
   public void endTurn(){
      Player temp = players.get(0);
      temp.setHasPlacedSettlements(false);
      temp.setUsingActionTile(false);
      temp.setPlacingSettlements(false);
      temp.setCard(null);
      temp.setNumSettlementsPlaced(0);
      players.remove(0);
      players.add(temp);
      players.get(0).setCard(getCard());
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
            checkRegularSettlementPlacement(players.get(0), temp);
            repaint();
         }
      });
   }

   private void checkRegularSettlementPlacement (Player player, HexagonButton temp) {
      if (player.isHasPlacedSettlements() || temp.getSettlement() != null || player.getSettlementsRemaining() == 0) {
         return;
      }
      if(!player.isPlacingSettlements()){
         player.setPlacingSettlements(true);
      }
      player.setNumSettlementsPlaced(player.getNumSettlementsPlaced() + 1);
      player.getSettlement();
      temp.setSettlement(constantClass.getSettlements()[players.get(0).getPlayerNumber() - 1]);
      if(player.getNumSettlementsPlaced() == 3) {
         player.setHasPlacedSettlements(true);
         player.setPlacingSettlements(false);
      }
   }

   //method that checks if current player can end their turn;
   private boolean canEndTurn(){
      return players.get(0).isHasPlacedSettlements();
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(menuButton)){
         cardLay.show(Constants.PANEL_CONT, Constants.MENU_PANEL);
      } else if(e.getSource().equals(finishButton)){
         if(canEndTurn()) {
            if (players.get(0).getPlayerNumber() == 4) {
               //make sure to check this later
               checkEndGame();
            }
            endTurn();
         }
      }
      repaint();
   }
   //make sure to check this later
   public void checkEndGame(){
      if(players.get(0).getSettlementsRemaining() == 0 || players.get(1).getSettlementsRemaining() == 0 || players.get(2).getSettlementsRemaining() == 0 || players.get(3).getSettlementsRemaining() == 0){
         cardLay.show(Constants.PANEL_CONT, Constants.END_PANEL);
      }
   }

   /**
    * adds a mouse listener which returns the specific coordinates of a click
    * checks the correctness of a text file according to its color on the screen
    *    change the number "n" if you want to check a specific file
    */
   public void setUpMiscellaneous(){

      String [] simpName = {"beach", "boat", "farm", "paddock", "house", "oracle", "tower", "tavern"};
      // type in the board you want to check corresponding to the string array above
      int n = 7;
      b1 = new FileCheckerBoard(simpName[n],n);
      // for coordinates
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
         }});
   }
   /**
    * draws the dots for a file checker
    * **/
   public void drawDotChecker(int r, int c, HexagonButton [][] board){
      if (b1.tiles[r][c]!= null){
         switch (b1.tiles[r][c]){
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