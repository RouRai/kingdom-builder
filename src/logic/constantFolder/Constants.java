package logic.constantFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

public class Constants {
    // Images
        public static BufferedImage[] boards = new BufferedImage[8];
        public static BufferedImage[] flippedBoards = new BufferedImage[16];
        public static BufferedImage[] actionTiles = new BufferedImage[8];
        public static BufferedImage[] charCards = new BufferedImage[10];
        public static BufferedImage[] landCards = new BufferedImage [6];
        public static BufferedImage[] settlements = new BufferedImage[4];
        public static BufferedImage[] actionProcess = new BufferedImage[8];

    // Screen dimensions
        public static final int WIDTH = 1536;
        public static final int HEIGHT = 864;
    // Panel Constants
        public static final JPanel PANEL_CONT = new JPanel();
        public static final String START_PANEL = "startPanel";
        public static final String GAME_PANEL = "gamePanel";
        public static final String END_PANEL = "endPanel";
        public static final String CARD_PANEL = "cardPanel";
        public static final String MENU_PANEL = "menuPanel";
        // Image Directory
        public static final String IMG_DIRECTORY = "/images/";

    public static final String[] boardNames = {"beach", "boat","farm", "paddock", "house", "oracle", "tower", "tavern"};

    // Gets Image from image folder
    public Constants(){
        readImages();
    }

    public void readImages(){
        try{
            // 2 -- BOARDS
            actionProcess[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Oasis.png")));
            actionProcess[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Boat.png")));
            actionProcess[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Farm.png")));
            actionProcess[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Horse.png")));
            actionProcess[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Barn.png")));
            actionProcess[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Oracle.png")));
            actionProcess[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Tower.png")));
            actionProcess[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Tavern.png")));

            boards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/beach.png")));
            boards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/boat.png")));
            boards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/farm.png")));
            boards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/horse.png")));
            boards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/house.png")));
            boards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/stone.png")));
            boards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tower.png")));
            boards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/Tavern.png")));

             flippedBoards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/beach_flipped.png")));
             flippedBoards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/boat_flipped.png")));
             flippedBoards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/farm_flipped.png")));
             flippedBoards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/paddock_flipped.png")));
             flippedBoards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/house_flipped.png")));
             flippedBoards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/oracle_flipped.png")));
             flippedBoards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tower_flipped.png")));
             flippedBoards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tavern_flipped.png")));

            settlements[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Black_Settlement - Copy.png")));
            settlements[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Blue_Settlement - Copy.png")));
            settlements[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Red_Settlement - Copy.png")));
            settlements[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/White_Settlement - Copy.png")));

            actionTiles[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/oasis_Tile.png")));
            actionTiles[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/harbor_Tile.png")));
            actionTiles[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/farm_Tile.png")));
            actionTiles[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/paddock_Tile.png")));
            actionTiles[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/barn_Tile.png")));
            actionTiles[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/oracle_Tile.png")));
            actionTiles[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/tower_Tile.png")));
            actionTiles[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/tavern_Tile.png")));

            // 4 - charCards
            charCards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Citizen.png")));
            charCards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Discoverers.png")));
            charCards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Farmers.png")));
            charCards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Fishermen.png")));
            charCards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Knights.png")));
            charCards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Marchants.png")));
            charCards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Miners.png")));
            charCards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Workers.png")));
            charCards[8] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Lords.png")));
            charCards[9] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Hermits.png")));

          //5- landCards
            landCards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Back.png")));
            landCards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Canyon.png")));
            landCards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Desert.png")));
            landCards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Flower.png")));
            landCards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Forest.png")));
            landCards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Meadow.png")));
        } catch (Exception ex5) {
            System.out.println("image error");
        }
    }
    public static BufferedImage getImage(String name) {
        try{
            return ImageIO.read(requireNonNull(Constants.class.getResource(Constants.IMG_DIRECTORY + name + ".png")));
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
    public static BufferedImage[] getLandCardsCards(){
        return landCards;
    }

    public static BufferedImage[] getSettlements() {
        return settlements;
    }

    public static BufferedImage[] getActionProcess() {
        return actionProcess;
    }
    public static ActionEnum getActionType(int symbol){
        
        switch(symbol % 8){
            case 0 :return  ActionEnum.OASIS;
            case 1 :return  ActionEnum.HARBOR;
            case 2 :return  ActionEnum.FARM;
            case 3 :return  ActionEnum.PADDOCK;
            case 4 :return  ActionEnum.BARN;
            case 5 :return  ActionEnum.ORACLE;
            case 6 :return  ActionEnum.TOWER;
            case 7 :return  ActionEnum.TAVERN;
            default: return null;
        }
    }
    public static ObjectiveEnum getObjectiveType(int symbol){

        switch(symbol){
            case 0 :return  ObjectiveEnum.CITIZEN;
            case 1 :return  ObjectiveEnum.DISCOVERER;
            case 2 :return  ObjectiveEnum.FARMER;
            case 3 :return  ObjectiveEnum.FISHERMAN;
            case 4 :return  ObjectiveEnum.KNIGHT;
            case 5 :return  ObjectiveEnum.MERCHANT;
            case 6 :return  ObjectiveEnum.MINER;
            case 7 :return  ObjectiveEnum.WORKER;
            case 8 :return  ObjectiveEnum.LORD;
            case 9 :return  ObjectiveEnum.HERMIT;
            default: return null;
        }
    }
    
}
