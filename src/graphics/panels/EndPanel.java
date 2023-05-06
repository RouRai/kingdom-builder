package graphics.panels;

import custom.ButtonQuadrant;
import custom.HexagonButton;
import logic.cards.ObjectiveCard;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;
import logic.gameLogic.Player;
import logic.gameLogic.Scoring;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class EndPanel extends JPanel{
    private final CardLayout cardLayout;
    private static Game game;
    private Scoring score;
    private BufferedImage background, firstMarker;
    private ArrayList<Player> players;
    private Graphics2D g2;
    private Constants constantClass;
    private final String fontStr = "Lucida Calligraphy";
    public EndPanel(CardLayout c, Game g){

        cardLayout = c;
        constantClass = new Constants();
        game = g;
        setUpMiscellaneous();

    }
    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);

        g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);

        drawSettlements();
        drawScoreComponents();
        drawWinnerString();
        drawLeftPanel();
    }
    public void drawLeftPanel(){
        for (int i = 0; i < 4; i++){
            ButtonQuadrant b = game.getButtonBoard()[i];
            double x = b.startX;
            double y = b.startY;
            if (game.getBoardNumbers()[i]>=8)
                g2.drawImage(Constants.getFlippedBoards()[game.getBoardNumbers()[i]%8],(int)x+2, (int)y-1,435, 369, null);
            else
                g2.drawImage(Constants.getBoards()[game.getBoardNumbers()[i]],(int)x+2, (int)y-1,435, 369, null);
            }

        for (ButtonQuadrant b: game.getButtonBoard()) {
            double x = b.startX;
            double y = b.startY;
            HexagonButton[][] board = b.getBoard();
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    //CONDITION IF HEX IS ENABLED IN MATRIX.
                    if (board[r][c] != null) {
                        if (r % 2 == 0)
                            board[r][c].setBounds((int) (x + c * 41.2), (int) y, 46, 46);
                        else
                            board[r][c].setBounds((int) (x + 21 + c * 41.3), (int) y, 46, 46);

                        board[r][c].drawSettlement(g2);
                    }
                }
                y += 35.5;
            }
        }
    }
    public void drawSettlements(){
        int space_between_Players = 123;
        g2.setFont(new Font(fontStr, Font.PLAIN, 40));

        if(game.getCurrentPlayer().getPlayerNumber() == 1)
            g2.drawImage(firstMarker,970,15,50,48,null);

        for (int i = 0; i<4; i++){
            //settlement icon
            g2.drawImage(Constants.getSettlements()[i], 1020+i * space_between_Players, 150, 100, 80, null);
            if(i == 3)
                g2.setColor(Color.BLACK);
            else
                g2.setColor(Color.WHITE);
            // players.get(i + 1).getSettlementsRemaining()
            g2.drawString(""+game.getAllPlayers().get(i ).getSettlementsRemaining(), 1050+i * space_between_Players, 210);
        }

    }
    public void drawWinnerString(){
        int maxPoints = 0; // initialize variable to track maximum points
        int maxPlayer = Integer.MIN_VALUE; // initialize variable to track index of player with maximum points

        for (int i = 0; i < game.getAllPlayers().size(); i++) {
            int score = 0;
            for(int k = 0; k < game.getAllPlayers().get(i).getScores().size(); k++){
                score += game.getAllPlayers().get(i).getScores().get(k);
            }
            if (score > maxPoints) {
                maxPoints = score; // update maximum points
                maxPlayer = i + 1; // update index of player with maximum points
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(fontStr, Font.PLAIN, 28));
        g2.drawString("" + maxPlayer, 550,810);
    }
    public void drawScoreComponents(){

        ArrayList<ObjectiveCard> cards = new ArrayList<>();
        for (int i: game.getObjectiveNumbers())
            cards.add(new ObjectiveCard(Constants.getObjectiveType(i)));
        score = new Scoring(game.getAllPlayers(),cards,game.board, game);

        int space_between_Players = 123;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(fontStr, Font.PLAIN, 40));

        for (int i = 0; i<3; i++)
            g2.drawImage(Constants.getCharCards()[game.getObjectiveNumbers()[i]], 930, 350+i * space_between_Players,80,105,null);

        int startX = 1030;
        int startY = 305;

        //city scores
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY);
        //card 1 scores
        for (int r = 0; r < 3; r++) {
            for (int i = 0; i < 4; i++)
                g2.drawString(String.format("%3d", game.getAllPlayers().get(i).getScores().get(r)), startX + i * space_between_Players, startY + (r+1) * space_between_Players);
        }
        //Total scores
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY+4 * space_between_Players);

    }
    public void setUpMiscellaneous(){
        try{
            // 1 -- BACKGROUND - BOTTOM LAYER
            background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/EndGame.png")));
            firstMarker = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/1st player.png")));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error -----------------------------------------");
        }

        String [] boardNames = {"beach", "boat", "farm", "paddock", "house", "oracle", "tower", "tavern"};
        // type in the board you want to check corresponding to the string array above
        int n = 7;
        // for coordinates
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
            }});
    }
    //@Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void setGame(Game g){
        game = g;
    }
}
