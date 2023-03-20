package game;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Constants {
    // Screen dimensions
    public static final int WIDTH = 1536;
    public static final int HEIGHT = 864;
    /*
    public static final int WIDTH = 1536;
    public static final int HEIGHT = 864;
     */
    // Panel Constants
    public static final JPanel PANEL_CONT = new JPanel();
    public static final String START_PANEL = "startPanel";
    public static final String GAME_PANEL = "gamePanel";
    public static final String END_PANEL = "endPanel";
    public static final String MENU_PANEL = "menuPanel";
    // Image Directory
    public static final String IMG_DIRECTORY = "/images/";
    // Gets Image from image folder
    public static BufferedImage getImage(String name) {
        try{
            return ImageIO.read(Constants.class.getResource(Constants.IMG_DIRECTORY + name + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
