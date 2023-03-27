package panels;

import custom.HexagonButton;
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
    private JButton gameButton;
    public StartPanel(CardLayout c){
        cl = c;
        setUpButtons();
        try{
            bg = ImageIO.read(getClass().getResource("/images/graphicsExtra/startScreen.png"));
        } catch (Exception e){

        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gameButton.setOpaque(false);
        gameButton.setContentAreaFilled(false);
        gameButton.setBorderPainted(false);
        g.drawImage(bg, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        gameButton.setBounds(85, 380, 600, 150);
    }
    private void setUpButtons(){
        gameButton = new JButton();
        add(gameButton);
        gameButton.addActionListener(this);
    }
    private void setUpHexes(HexagonButton J){
        add(J);
        J.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(gameButton)){
            KBWindow.setup();
        }
    }
}
