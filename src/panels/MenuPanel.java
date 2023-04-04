package panels;

import custom.TranslucentButton;
import game.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {
    private CardLayout cl;
    private BufferedImage background;
    private TranslucentButton startPage, resume;
    private Graphics2D g2;
    private Constants constantClass;
    private BufferedImage bg;
    public MenuPanel(CardLayout c){
        cl = c;
        constantClass = new Constants();        // for coordinates
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
            }});
        try{
            // 1 -- BACKGROUND - BOTTOM LAYER
            background = ImageIO.read(getClass().getResource("/images/backgroundImages/menuBackground.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error -----------------------------------------");
        }
        setUpButtons();
    }
    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        //Base Calls
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        // 1 -- BACKGROUND
        g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);

        startPage.setBounds(90,380,510,150);
        resume.setBounds(500,380,510,150);
    }
    private void setUpButtons(){
        startPage = new TranslucentButton();
        add(startPage);
        // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        startPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //button action here
            }
        });

        resume = new TranslucentButton();
        add(resume);
        // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //button action here
            }
        });

    }

}
