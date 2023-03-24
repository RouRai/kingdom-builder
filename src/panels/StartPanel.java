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
    private HexagonButton test;
    private HexagonButton[][] board;
    public StartPanel(CardLayout c){
        cl = c;
        setUpButtons();
        board = new HexagonButton[10][10];
        for(int r = 0; r < 10; r++){
            for(int co = 0; co < 10; co++){
                board[r][co] = new HexagonButton();
                setUpHexes(board[r][co]);
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon highlight = new ImageIcon(getClass().getResource("/images/graphicsExtra/Hex.png"));
        test.setBounds(0, 90, 46, 46);
        test.setIcon(highlight);
        Image img = highlight.getImage();
        Image newimg = img.getScaledInstance( 120, 120,  java.awt.Image.SCALE_SMOOTH ) ;
        highlight = new ImageIcon(newimg);
        test.setIcon(highlight);
        //gameButton.setIcon(icon);
        //test.setBounds(300, 300, 50, 50);
        //g.setColor(Color.yellow);
        double x = 423;
        double y = 6;
        for(int r = 0; r < 10; r++){
            for(int c = 0; c < 10; c++){
                if(r % 2 == 0){
                    board[r][c].setColor(Color.RED);
                    board[r][c].setBounds((int)(x + c * 41), (int)y, 46, 46);
                } else{
                    board[r][c].setColor(Color.yellow);
                    board[r][c].setBounds((int)(x + 21 + c * 41.3), (int)y, 46, 46);
                }
                board[r][c].setIcon(highlight);
                //gameButton.setIcon(icon);
            }
            y+=35.5;
        }

        //g.drawImage(tb, 300, 0, null);
        //test.setContentAreaFilled(true);
        /*Example of how to use the HexagonButton class
        follow exact same instructions as when using regular JButtons
        test.setBounds(0, 0, 50, 50);
        recommend using this type of setting bounds
        imagine that the point you provide is the top left corner of a rectangle that the hexagon is then inscribed into
        */
    }
    private void setUpButtons(){
        //gameButton = new JButton("Start");
        //gameButton = new JButton(new ImageIcon(getClass().getResource("images/settlementIcons/Black_Settlement - Copy.png")));
        gameButton = new JButton("Start");
        //gameButton.setIcon(icon);
        test = new HexagonButton();
        add(gameButton);
        add(test);
        gameButton.addActionListener(this);
        test.addActionListener(this);
    }
    private void setUpHexes(HexagonButton J){
        add(J);
        J.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(gameButton)){
            KBWindow.setup();
        } else if(e.getSource().equals(test)){
            KBWindow.setup();
        }
    }
}
