package panels;

import custom.HexagonButton;
import custom.TranslucentButton;
import game.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private BufferedImage bg;
    private TranslucentButton gameButton;
    private HexagonButton test;
    private BufferedImage startBackground;
    private HexagonButton[][] board;
    public StartPanel(CardLayout c){
        //background image
        try{
        startBackground = ImageIO.read(getClass().getResource("/images/backgroundImages/startPage.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error");
        }
        // cardlayout
        cl = c;
        //setting up buttons
        setUpButtons();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // background
        g.drawImage(startBackground, 0,0, Constants.WIDTH, Constants.HEIGHT, null);
        gameButton.setBounds(90,380,510,150);
        //Start Button
    }
    private void setUpButtons(){
        gameButton = new TranslucentButton(40,300,300,40);
        add(gameButton);
        gameButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(gameButton)){
            KBWindow.setup();
        }
    }
}
