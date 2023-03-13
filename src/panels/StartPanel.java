package panels;

import game.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class StartPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private BufferedImage bg;
    private JButton gameButton, menuButton;
    public StartPanel(CardLayout c){
        cl = c;
        setUpButtons();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    private void setUpButtons(){
        menuButton = new JButton("Menu");
        gameButton = new JButton("Start");
        add(menuButton);
        add(gameButton);
        menuButton.addActionListener(this);
        gameButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(gameButton)){
            KBWindow.setup();
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        }
    }
}
