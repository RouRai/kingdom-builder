package graphics.panels;

import custom.TranslucentButton;
import graphics.frames.KBWindow;
import logic.constantFolder.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class StartPanel extends JPanel implements ActionListener {
    private TranslucentButton gameButton;
    private BufferedImage startBackground;

    public StartPanel(){
        //background image
        try{
        startBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/backgroundImages/startPage.png")));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error");
        }
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
        gameButton = new TranslucentButton();
        add(gameButton);
        // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        gameButton.addActionListener(e -> KBWindow.setup());

    }

    public void actionPerformed(ActionEvent e) {

    }
}
