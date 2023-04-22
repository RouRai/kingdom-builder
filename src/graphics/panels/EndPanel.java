package graphics.panels;

import custom.TranslucentButton;
import files.QuadrantMaker;
import logic.constantFolder.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EndPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private BufferedImage background;
    private TranslucentButton menuButton;
    private Graphics2D g2;
    private Constants constantClass;
    private BufferedImage bg;
    private final String fontStr = "Lucida Calligraphy";
    public EndPanel(CardLayout c){

        cl = c;
        constantClass = new Constants();

        menuButton = new TranslucentButton();
        add(menuButton);
        menuButton.addActionListener(this);

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

        menuButton.setBounds(785, 770, 70, 65);
    }
    public void drawSettlements(){
        int space_between_Players = 123;
        for (int i = 0; i<4; i++){
            //settlement icon
            g2.drawImage(constantClass.getSettlements()[i], 1020+i * space_between_Players, 150, 100, 80, null);
            /*settlement number
            if(players.get(i + 1).getPlayerNumber() == 4){
                g2.setColor(Color.BLACK);
            }
            g2.drawString("" + players.get(i + 1).getSettlementsRemaining(), 1125, 90 + i * space_between_Players);*/
        }

    }
    public void drawWinnerString(){
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(fontStr, Font.PLAIN, 28));
        g2.drawString("1", 550,810);
    }
    public void drawScoreComponents(){
        int space_between_Players = 123;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(fontStr, Font.PLAIN, 40));

        int startX = 1040;
        int startY = 290;

        int r = 0;
        //city scores
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY);
        //card 1 scores
        r++;
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY+r * space_between_Players);
        //card 2 scores
        r++;
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY+r * space_between_Players);
        //card 3 scores
        r++;
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY+r * space_between_Players);

        //Total scores
        r++;
        for (int i = 0; i<4; i++)
            g2.drawString(String.format("%3d", 0), startX+i * space_between_Players, startY+r * space_between_Players);


    }
    public void setUpMiscellaneous(){
        try{
            // 1 -- BACKGROUND - BOTTOM LAYER
            background = ImageIO.read(getClass().getResource("/images/backgroundImages/EndGame.png"));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(menuButton))
            cl.show(Constants.PANEL_CONT, Constants.MENU_PANEL);

    }
}
