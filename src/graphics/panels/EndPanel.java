package graphics.panels;

import custom.TranslucentButton;
import logic.constantFolder.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EndPanel extends JPanel {
    private CardLayout cl;
    private BufferedImage background;
    private TranslucentButton startPage, resume;
    private Graphics2D g2;
    private Constants constantClass;
    private BufferedImage bg;
    public EndPanel(CardLayout c){

        cl = c;
        constantClass = new Constants();
        try{
            // 1 -- BACKGROUND - BOTTOM LAYER
            background = ImageIO.read(getClass().getResource("/images/backgroundImages/EndGame.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error -----------------------------------------");
        }
    }
    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        //Base Calls
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        // 1 -- BACKGROUND
        g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);
    }
}
