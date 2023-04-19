package logic.constantFolder;
import logic.tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
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
        public static final String MENU_PANEL = "menuPanel";
        // Image Directory
        public static final String IMG_DIRECTORY = "/images/";
        public static Tile<?>[] boardTileTypes;

        public static final String[] boardNames = {"beach", "boat","farm", "paddock", "house", "oracle", "tower", "tavern"};

    // Gets Image from image folder
    //srgusdfgh
    public Constants(){
        boards = new BufferedImage [8];
        flippedBoards = new BufferedImage [16];
        settlements = new BufferedImage [4];
        landCards = new BufferedImage [6];
        charCards = new BufferedImage [10];
        actionTiles = new BufferedImage[8];
        actionProcess = new BufferedImage[8];
        readImages();
    }

    public static String [][] readNormalFile (int quadNumber) {
        String [][]boardText = new String[10][10];
        try {
            File myObj = new File(Objects.requireNonNull(Constants.class.getResource("/files/textFiles/" + boardNames[quadNumber] + "")).getFile());
            Scanner myReader = new Scanner(myObj);
            int r = 0;
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                String [] arr = data.split(" ");
                int c = 0;
                for (String str: arr){
                    String temp = "*";
                    if (str.equals("d")){
                        temp = "DESERT";}
                    else if (str.equals("g")) {
                        temp = "GRASS";}
                    else if (str.equals("f")){
                        temp = "FOREST";}
                    else if (str.equals("fl")){
                        temp = "FLOWER_FIELD";}
                    else if (str.equals("w")){
                        temp = "WATER";}
                    else if (str.equals("v")){
                        temp = "CANYON";}
                    else if (str.equals("m")){
                        temp = "MOUNTAIN";}
                    else if (str.equals("c")){
                        temp = "CITY";}
                    else if (str.equals("a")){
                        temp = null;}
                    boardText[r][c] = temp;
                    c++;
                }
                r++;
            }
            //System.out.println("-----------");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return boardText;

    }

    public static String[][] readFlippedFile (int quadNumber){
        String [][] boardText = readNormalFile(quadNumber);
        String [][] tempBoard = new String [10][10];
        System.out.println();
        for (int r = 9; r >=0; r--){
            for (int c = 9; c>=0;c--){
                //System.out.println(r +" "+ c + " - " +boardText[r][c]);
                tempBoard [9-r][9-c] = boardText[r][c];
            }
        }
        return tempBoard;
    }

    private void readImages(){
        try{
            // 2 -- BOARDS
            actionProcess[0] = ImageIO.read(getClass().getResource("/images/actionProcess/Oasis.png"));
            actionProcess[1] = ImageIO.read(getClass().getResource("/images/actionProcess/Boat.png"));
            actionProcess[2] = ImageIO.read(getClass().getResource("/images/actionProcess/Farm.png"));
            actionProcess[3] = ImageIO.read(getClass().getResource("/images/actionProcess/Horse.png"));
            actionProcess[4] = ImageIO.read(getClass().getResource("/images/actionProcess/Barn.png"));
            actionProcess[5] = ImageIO.read(getClass().getResource("/images/actionProcess/Oracle.png"));
            actionProcess[6] = ImageIO.read(getClass().getResource("/images/actionProcess/Tower.png"));
            actionProcess[7] = ImageIO.read(getClass().getResource("/images/actionProcess/Tavern.png"));
        } catch (Exception ex) {
            System.out.println(" actionProcess error");}
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
        } catch (Exception ex) {
            System.out.println("board error");}
        try{
            // 2.5 -- FLIPPED BOARDS
             flippedBoards[0] = ImageIO.read(getClass().getResource("/images/boards/beach_flipped.png"));
             flippedBoards[1] = ImageIO.read(getClass().getResource("/images/boards/boat_flipped.png"));
             flippedBoards[2] = ImageIO.read(getClass().getResource("/images/boards/farm_flipped.png"));
             flippedBoards[3] = ImageIO.read(getClass().getResource("/images/boards/paddock_flipped.png"));
             flippedBoards[4] = ImageIO.read(getClass().getResource("/images/boards/house_flipped.png"));
             flippedBoards[5] = ImageIO.read(getClass().getResource("/images/boards/oracle_flipped.png"));
             flippedBoards[6] = ImageIO.read(getClass().getResource("/images/boards/tower_flipped.png"));
             flippedBoards[7] = ImageIO.read(getClass().getResource("/images/boards/tavern_flipped.png"));
        } catch (Exception ex1) {
            System.out.println("board flipped error");}

        try{
            // 3 -- SETTLEMENTS
            settlements[0] = ImageIO.read(getClass().getResource("/images/settlementIcons/Black_Settlement - Copy.png"));
            settlements[1] = ImageIO.read(getClass().getResource("/images/settlementIcons/Blue_Settlement - Copy.png"));
            settlements[2] = ImageIO.read(getClass().getResource("/images/settlementIcons/Red_Settlement - Copy.png"));
            settlements[3] = ImageIO.read(getClass().getResource("/images/settlementIcons/White_Settlement - Copy.png"));
        }
        catch (Exception ex2) {
            System.out.println("settlement error");}

        try{
            // 4 - ACTIONTILES
            actionTiles[0] = ImageIO.read(getClass().getResource("/images/actionTiles/barn_Tile.png"));
            actionTiles[1] = ImageIO.read(getClass().getResource("/images/actionTiles/farm_Tile.png"));
            actionTiles[2] = ImageIO.read(getClass().getResource("/images/actionTiles/harbor_Tile.png"));
            actionTiles[3] = ImageIO.read(getClass().getResource("/images/actionTiles/oasis_Tile.png"));
            actionTiles[4] = ImageIO.read(getClass().getResource("/images/actionTiles/oracle_Tile.png"));
            actionTiles[5] = ImageIO.read(getClass().getResource("/images/actionTiles/paddock_Tile.png"));
            actionTiles[6] = ImageIO.read(getClass().getResource("/images/actionTiles/tavern_Tile.png"));
            actionTiles[7] = ImageIO.read(getClass().getResource("/images/actionTiles/tower_Tile.png"));
        }
        catch (Exception ex3) {
            System.out.println("actionTile error");}
        try{
            // 4 - charCards
            charCards[0] = ImageIO.read(getClass().getResource("/images/characterCards/Citizen.png"));
            charCards[1] = ImageIO.read(getClass().getResource("/images/characterCards/Discoverers.png"));
            charCards[2] = ImageIO.read(getClass().getResource("/images/characterCards/Farmers.png"));
            charCards[3] = ImageIO.read(getClass().getResource("/images/characterCards/Fishermen.png"));
            charCards[4] = ImageIO.read(getClass().getResource("/images/characterCards/Knights.png"));
            charCards[5] = ImageIO.read(getClass().getResource("/images/characterCards/Marchants.png"));
            charCards[6] = ImageIO.read(getClass().getResource("/images/characterCards/Miners.png"));
            charCards[7] = ImageIO.read(getClass().getResource("/images/characterCards/Workers.png"));
            charCards[8] = ImageIO.read(getClass().getResource("/images/characterCards/Lords.png"));
            charCards[9] = ImageIO.read(getClass().getResource("/images/characterCards/Hermits.png"));
        }
        catch (Exception ex4) {
            System.out.println("char card error");}
        try{  //5- landCards
            landCards[5] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Back.png"));
            landCards[1] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Canyon.png"));
            landCards[2] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Desert.png"));
            landCards[3] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Flower.png"));
            landCards[4] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Forest.png"));
            landCards[0] = ImageIO.read(getClass().getResource("/images/landscapeCards/KB-Card-Meadow.png"));
        } catch (Exception ex5) {
            System.out.println("landscape error");
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

    public static BufferedImage[] getActionProcess() {
        return actionProcess;
    }

}
