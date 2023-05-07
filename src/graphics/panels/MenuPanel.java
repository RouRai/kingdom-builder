package graphics.panels;

import custom.TranslucentButton;
import graphics.frames.KBWindow;
import logic.constantFolder.Constants;
import logic.gameLogic.Game;

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
    private int pageNumber;
    private BufferedImage background;
    private BufferedImage[] RulebookPages;
    private TranslucentButton startPage, resume, leftButton, rightButton;
    private Graphics2D g2;
    private Constants constantClass;
    private BufferedImage ruleBookPage;
    private static String panel;
    public MenuPanel(CardLayout c, Game g ){

        pageNumber = 1;
        cl = c;
        constantClass = new Constants();        // for coordinates
        //775, 225
        //1250, 805

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mouse clicked on coord (" +e.getX()+ ", " +e.getY()+ ")");
            }});
        try{
            // 1 -- BACKGROUND - BOTTOM LAYER
            background = ImageIO.read(getClass().getResource("/images/backgroundImages/menu.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error -----------------------------------------");
        }
        updateImage();
        startPage = new TranslucentButton();
        resume = new TranslucentButton();
        setUpButtons();
        setUpResume();
        leftButton = new TranslucentButton();
        rightButton = new TranslucentButton();
        setUpLeft();
        setUpRight();
    }
    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        //Base Calls
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        // 1 -- BACKGROUND
        g2.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT - 8, null);
        g2.drawImage(ruleBookPage, 700, 22, 605, 800, null);


        startPage.setBounds(305,250,310,100);
        resume.setBounds(305,390,240,110);
        leftButton.setBounds(660,395,40,60);
        rightButton.setBounds(1305,405,40,60);
    }
    private void setUpButtons(){
        add(startPage);
        // cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        startPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("start page");
                KBWindow.terminate();
                cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
            }
        });
    }
    private void setUpResume(){
        add(resume);
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("resume");
                if(panel.equals("end")){
                    cl.show(Constants.PANEL_CONT, Constants.END_PANEL);
                } else {
                    cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
                }
            }
        });}
    private void updateImage (){
        try{  //5- landCards
            ruleBookPage = ImageIO.read(getClass().getResource("/images/rulBook/13-kingdom-builder-big-box-rulebook (1)-"+ pageNumber+".jpg"));

        } catch (Exception ex5) {
            System.out.println("rulebook error" + pageNumber);
        }
    }
    private void setUpLeft(){
        add(leftButton);
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Left");
                if(pageNumber > 0)
                    pageNumber--;

                updateImage();
                repaint();
            }
        });}
    private void setUpRight(){
        add(rightButton);
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Right");
                if(pageNumber <= 15)
                    pageNumber++;
                updateImage();
                repaint();
            }
        });}
        public static void setPanelCameFrom(String p){
            panel = p;
        }
}
