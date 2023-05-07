package graphics.panels;

import custom.HexagonButton;
import custom.TranslucentButton;
import graphics.frames.KBWindow;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class LoadingPanel extends JPanel {
    private CardLayout cl;
    private BufferedImage loadingBackground;
    public LoadingPanel(CardLayout c){
        //background image
        try{
            loadingBackground = ImageIO.read(getClass().getResource("/images/backgroundImages/MinecraftLoading.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error");
        }
        // cardlayout
        cl = c;
        //setting up buttons
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // background
        g.drawImage(loadingBackground, 0,0, Constants.WIDTH, Constants.HEIGHT, null);
        //g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        //Start Button
    }
}