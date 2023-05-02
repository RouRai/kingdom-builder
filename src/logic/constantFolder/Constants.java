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
    public static DirectionEnum[] allDirections = new DirectionEnum[]{DirectionEnum.BOTTOM_RIGHT, DirectionEnum.BOTTOM_LEFT, DirectionEnum.LEFT, DirectionEnum.RIGHT, DirectionEnum.TOP_LEFT, DirectionEnum.TOP_RIGHT};

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

    public static final String[] boardNames = {"beach", "boat","farm", "paddock", "house", "oracle", "tower", "tavern"};

    // Gets Image from image folder
    public Constants(){
        readImages();
    }

    public void readImages(){
        readBoards();
        readSettlements();
        readActionTiles();
        readLandCards();
        readActionProcesses();
        readFlippedBoards();
        readCharCards();
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
        return switch (symbol % 8) {
            case 0 -> ActionEnum.OASIS;
            case 1 -> ActionEnum.HARBOR;
            case 2 -> ActionEnum.FARM;
            case 3 -> ActionEnum.PADDOCK;
            case 4 -> ActionEnum.BARN;
            case 5 -> ActionEnum.ORACLE;
            case 6 -> ActionEnum.TOWER;
            case 7 -> ActionEnum.TAVERN;
            default -> null;
        };
    }
    public static ObjectiveEnum getObjectiveType(int symbol){
        return switch (symbol) {
            case 0 -> ObjectiveEnum.CITIZEN;
            case 1 -> ObjectiveEnum.DISCOVERER;
            case 2 -> ObjectiveEnum.FARMER;
            case 3 -> ObjectiveEnum.FISHERMAN;
            case 4 -> ObjectiveEnum.KNIGHT;
            case 5 -> ObjectiveEnum.MERCHANT;
            case 6 -> ObjectiveEnum.MINER;
            case 7 -> ObjectiveEnum.WORKER;
            case 8 -> ObjectiveEnum.LORD;
            case 9 -> ObjectiveEnum.HERMIT;
            default -> null;
        };
    }

    public static DirectionEnum getOppositeDirection (DirectionEnum directionEnum) {
        if (directionEnum == DirectionEnum.LEFT) return DirectionEnum.RIGHT;
        if (directionEnum == DirectionEnum.RIGHT) return DirectionEnum.LEFT;
        if (directionEnum == DirectionEnum.TOP_LEFT) return DirectionEnum.BOTTOM_RIGHT;
        if (directionEnum == DirectionEnum.TOP_RIGHT) return DirectionEnum.BOTTOM_LEFT;
        if (directionEnum == DirectionEnum.BOTTOM_LEFT) return DirectionEnum.TOP_RIGHT;
        return DirectionEnum.TOP_LEFT;
    }

    private void readActionProcesses () {
        try {
            actionProcess[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Oasis.png")));
            actionProcess[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Boat.png")));
            actionProcess[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Farm.png")));
            actionProcess[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Horse.png")));
            actionProcess[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Barn.png")));
            actionProcess[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Oracle.png")));
            actionProcess[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Tower.png")));
            actionProcess[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionProcess/Tavern.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFlippedBoards () {
        try {
            flippedBoards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/beach_flipped.png")));
            flippedBoards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/boat_flipped.png")));
            flippedBoards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/farm_flipped.png")));
            flippedBoards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/paddock_flipped.png")));
            flippedBoards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/house_flipped.png")));
            flippedBoards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/oracle_flipped.png")));
            flippedBoards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tower_flipped.png")));
            flippedBoards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tavern_flipped.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readBoards () {
        try {
            boards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/beach.png")));
            boards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/boat.png")));
            boards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/farm.png")));
            boards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/horse.png")));
            boards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/house.png")));
            boards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/stone.png")));
            boards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/tower.png")));
            boards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/boards/Tavern.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readSettlements () {
        try {
            settlements[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Black_Settlement - Copy.png")));
            settlements[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Blue_Settlement - Copy.png")));
            settlements[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/Red_Settlement - Copy.png")));
            settlements[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/settlementIcons/White_Settlement - Copy.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readActionTiles () {
        try {
            actionTiles[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/oasis_Tile.png")));
            actionTiles[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/harbor_Tile.png")));
            actionTiles[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/farm_Tile.png")));
            actionTiles[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/paddock_Tile.png")));
            actionTiles[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/barn_Tile.png")));
            actionTiles[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/oracle_Tile.png")));
            actionTiles[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/tower_Tile.png")));
            actionTiles[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/actionTiles/tavern_Tile.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCharCards () {
        try {
            charCards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Citizen.png")));
            charCards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Discoverers.png")));
            charCards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Farmers.png")));
            charCards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Fishermen.png")));
            charCards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Knights.png")));
            charCards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Merchants.png")));
            charCards[6] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Miners.png")));
            charCards[7] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Workers.png")));
            charCards[8] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Lords.png")));
            charCards[9] = ImageIO.read(requireNonNull(getClass().getResource("/images/characterCards/Hermits.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readLandCards () {
        try {
            landCards[5] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Back.png")));
            landCards[1] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Canyon.png")));
            landCards[2] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Desert.png")));
            landCards[3] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Flower.png")));
            landCards[4] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Forest.png")));
            landCards[0] = ImageIO.read(requireNonNull(getClass().getResource("/images/landscapeCards/KB-Card-Meadow.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
