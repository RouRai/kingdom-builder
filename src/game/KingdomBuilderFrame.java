package game;

import javax.swing.*;

public class KingdomBuilderFrame extends JFrame {
    private int HEIGHT = 1080;
    private int WIDTH = 1920;
    public KingdomBuilderFrame(String name){
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
    }
}
