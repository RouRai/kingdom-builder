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


public class StartPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private BufferedImage bg;
    private BufferedImage loadingBackground;
    private TranslucentButton gameButton;
    private BufferedImage startBackground;
    private boolean showLoading;
    public StartPanel(CardLayout c){
        //background image
        try{
            showLoading = false;
        startBackground = ImageIO.read(getClass().getResource("/images/backgroundImages/startPage.png"));
        loadingBackground = ImageIO.read(getClass().getResource("/images/backgroundImages/MinecraftLoading.png"));
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
        if(showLoading){
            g.drawImage(loadingBackground, 0,0, Constants.WIDTH, Constants.HEIGHT, null);
        } else{
            g.drawImage(startBackground, 0,0, Constants.WIDTH, Constants.HEIGHT, null);
        }
        gameButton.setBounds(90,380,510,150);
        //Start Button
    }
    private void setUpButtons(){
        gameButton = new TranslucentButton();
        add(gameButton);
        gameButton.addActionListener(e -> {
            //KBWindow.setup();
            cl.show(Constants.PANEL_CONT, Constants.LOADING_PANEL);
            Thread setupThread = new Thread(() -> {
                SwingUtilities.invokeLater(() -> {
                    KBWindow.setup();
                });
            });
            setupThread.start();
        });

    }
    private void setUpHexes(HexagonButton J){
        add(J);
        J.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {

    }
}
