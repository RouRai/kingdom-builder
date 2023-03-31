package game;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Constants {
    // Images
        public static BufferedImage[] boards, flippedBoards, actionTiles, charCards, landCards, settlements;
    // Screen dimensions
         public static final int WIDTH = 1536;
        public static final int HEIGHT = 864;
    // Panel Constants
        public static final JPanel PANEL_CONT = new JPanel();
        public static final String START_PANEL = "startPanel";
        public static final String GAME_PANEL = "gamePanel";
        public static final String END_PANEL = "endPanel";
        public static final String MENU_PANEL = "menuPanel";
        // Image Directory
        public static final String IMG_DIRECTORY = "/images/";
    // Gets Image from image folder
    public Constants(){
        boards = new BufferedImage [8];
        flippedBoards = new BufferedImage [16];
        settlements = new BufferedImage [4];
        landCards = new BufferedImage [5];
        charCards = new BufferedImage [10];
        actionTiles = new BufferedImage[8];

        try{
            // 2 -- BOARDS
            boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach.png"));
            boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat.png"));
            boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm.png"));
            boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse.png"));
            boards[4] = ImageIO.read(getClass().getResource("/images/boards/house.png"));
            boards[5] = ImageIO.read(getClass().getResource("/images/boards/stone.png"));
            boards[6] = ImageIO.read(getClass().getResource("/images/boards/tower.png"));
            boards[7] = ImageIO.read(getClass().getResource("/images/boards/Tavern.png"));
            // 2.5 -- FLIPPED BOARDS
            boards[0] = ImageIO.read(getClass().getResource("/images/boards/beach_flipped.png"));
            boards[1] = ImageIO.read(getClass().getResource("/images/boards/boat_flipped.png"));
            boards[2] = ImageIO.read(getClass().getResource("/images/boards/farm_flipped.png"));
            boards[3] = ImageIO.read(getClass().getResource("/images/boards/horse_flipped.png"));
            boards[4] = ImageIO.read(getClass().getResource("/images/boards/house_flipped.png"));
            boards[5] = ImageIO.read(getClass().getResource("/images/boards/oracle_flipped.png"));
            boards[6] = ImageIO.read(getClass().getResource("/images/boards/tower_flipped.png"));
            boards[7] = ImageIO.read(getClass().getResource("/images/boards/tavern_flipped.png"));
            // 3 -- SETTLEMENTS
            settlements[0] = ImageIO.read(getClass().getResource("/images/settlementIcons/Black_Settlement - Copy.png"));
            settlements[1] = ImageIO.read(getClass().getResource("/images/settlementIcons/Blue_Settlement - Copy.png"));
            settlements[2] = ImageIO.read(getClass().getResource("/images/settlementIcons/Red_Settlement - Copy.png"));
            settlements[3] = ImageIO.read(getClass().getResource("/images/settlementIcons/White_Settlement - Copy.png"));
            // 4 - ACTIONTILES
            actionTiles[0] = ImageIO.read(getClass().getResource("/images/actionTiles/barn_Tile.png"));
            actionTiles[1] = ImageIO.read(getClass().getResource("/images/actionTiles/farm_Tile.png"));
            actionTiles[2] = ImageIO.read(getClass().getResource("/images/actionTiles/harbor_Tile.png"));
            actionTiles[3] = ImageIO.read(getClass().getResource("/images/actionTiles/oasis_Tile.png"));
            actionTiles[4] = ImageIO.read(getClass().getResource("/images/actionTiles/oracle_Tile.png"));
            actionTiles[5] = ImageIO.read(getClass().getResource("/images/actionTiles/paddock_Tile.png"));
            actionTiles[6] = ImageIO.read(getClass().getResource("/images/actionTiles/tavern_Tile.png"));
            actionTiles[7] = ImageIO.read(getClass().getResource("/images/actionTiles/tower_Tile.png"));
            // 4 - charCards
            charCards[0] = ImageIO.read(getClass().getResource("/images/characterCards/Citizen.png"));
            charCards[1] = ImageIO.read(getClass().getResource("/images/characterCards/Discoverers.png"));
            charCards[2] = ImageIO.read(getClass().getResource("/images/characterCards/Farmers.png"));
            charCards[3] = ImageIO.read(getClass().getResource("/images/characterCards/Fishermen.png"));
            charCards[4] = ImageIO.read(getClass().getResource("/images/characterCards/Knights.png"));
            charCards[5] = ImageIO.read(getClass().getResource("/images/characterCards/Marchants.png"));
            charCards[6] = ImageIO.read(getClass().getResource("/images/characterCards/Miners.png"));
            charCards[7] = ImageIO.read(getClass().getResource("/images/characterCards/Workers.png"));
            charCards[8] = ImageIO.read(getClass().getResource("/images/characterCards/Lord.png"));
            charCards[9] = ImageIO.read(getClass().getResource("/images/characterCards/Hermits.png"));
            //5- landCards
            landCards[0] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Back.png"));
            landCards[1] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Canyon.png"));
            landCards[2] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Desert.png"));
            landCards[3] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Flower.png"));
            landCards[4] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Forest.png"));
            landCards[5] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Meadow.png"));
        } catch (Exception ex) {
            System.out.println("----------------------------------------- Image Error -----------------------------------------");
        }
    }
    public static BufferedImage getImage(String name) {
        try{
            return ImageIO.read(Constants.class.getResource(Constants.IMG_DIRECTORY + name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static BufferedImage[] getBoards(){
        return boards;
    }
    public static BufferedImage[] getFlippedBoards(){
        return flippedBoards;
    }
    public static BufferedImage[] getActionTiles(){
        return actionTiles;
    }
    public static BufferedImage[] getCharCards(){
        return charCards;
    }

    public static BufferedImage[] getLandCards() {
        return landCards;
    }

    public static BufferedImage[] getSettlements() {
        return settlements;
    }
}
