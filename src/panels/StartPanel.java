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
    private JButton gameButton;
    private HexagonButton test;
    public StartPanel(CardLayout c){
        cl = c;
        setUpButtons();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(true){
            test.setColor(Color.YELLOW);
        } else {

        }
        g.setColor(Color.yellow);
        test.setBounds(0, 0, 50, 50);
        test.paintBorder(g);
        //test.setContentAreaFilled(true);
        /*Example of how to use the HexagonButton class
        follow exact same instructions as when using regular JButtons
        test.setBounds(0, 0, 50, 50);
        recommend using this type of setting bounds
        imagine that the point you provide is the top left corner of a rectangle that the hexagon is then inscribed into
        */
    }
    private void setUpButtons(){
        gameButton = new JButton("Start");
        test = new HexagonButton();
        add(gameButton);
        add(test);
        gameButton.addActionListener(this);
        test.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(gameButton)){
            KBWindow.setup();
        } else if(e.getSource().equals(test)){
            KBWindow.setup();
        }
    }
}
