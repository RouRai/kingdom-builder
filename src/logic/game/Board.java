package logic.game;

import datastructures.gameDatastructures.BoardGraph;
import logic.constantFolder.ActionEnum;
import logic.constantFolder.TerrainEnum;
import logic.tiles.ActionTile;
import logic.tiles.TerrainTile;
import logic.tiles.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private final BoardGraph board;
    private String simpName;
    private String enumName;
    public Tile [][] tiles;


    public Board (String simpName) {
        board = new BoardGraph(null);
        tiles = new Tile[10][10];
        addTiles();
    }

    public BoardGraph getBoard () {
        return board;
    }

    public void addTiles () {
        try {
            File myObj = new File(getClass().getResource("/textFiles/Boat").getFile());
            Scanner myReader = new Scanner(myObj);
            int r = 0;
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                System.out.println(data);
                String [] arr = data.split(" ");
                for (String str: arr){
                    int c = 0;
                    Tile temp = null;
                    switch (str){
                        case "d": temp = new TerrainTile(TerrainEnum.DESERT);break;
                        case "g": temp = new TerrainTile(TerrainEnum.GRASS);break;
                        case "f": temp = new TerrainTile(TerrainEnum.FOREST);break;
                        case "fl": temp = new TerrainTile(TerrainEnum.FLOWER_FIELD);break;
                        case "w": temp = new TerrainTile(TerrainEnum.WATER);break;
                        case "v": temp = new TerrainTile(TerrainEnum.CANYON);break;
                        case "m": temp = new TerrainTile(TerrainEnum.MOUNTAIN);break;
                        case "c": temp = new ActionTile(ActionEnum.CITY);break;
                        case "a": temp = null;break;
                    }
                    //BARN, FARM, HARBOR, OASIS, ORACLE, PADDOCK, TAVERN, TOWER
                    tiles[r][c] = temp;
                    c++;
                }
                r++;
            }
            System.out.println("-----------");
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public ArrayList<TerrainTile> playerCanUseTiles (Player player) {
        // Returns arraylist of tiles player can use
        return null;
    }
}
