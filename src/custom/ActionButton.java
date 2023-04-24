package custom;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;
import logic.tiles.CityTile;
import logic.tiles.TerrainTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

public class ActionButton extends HexagonButton{
    private BufferedImage front, back, process;
    private boolean canUse;
    private int numUses;
    private int numTiles;
    private ActionEnum type;
    public ActionButton(int boardNum){
        super();
        numTiles = 0;
        numUses = 0;
        canUse = false;
        setUP(boardNum);
    }
    private void setUP(int symbol){
        setFront(symbol);
        setProcess(symbol);
        setType(symbol);
    }
    private void setFront(int symbol){
        front = Constants.getActionTiles()[symbol];
    }
    private void setProcess(int symbol){
        process = Constants.getActionProcess()[symbol];
    }
    private void setType(int symbol){
        switch(symbol){
            case 0 -> type = ActionEnum.OASIS;
            case 1 -> type = ActionEnum.HARBOR;
            case 2 -> type = ActionEnum.FARM;
            case 3 -> type = ActionEnum.PADDOCK;
            case 4 -> type = ActionEnum.BARN;
            case 5 -> type = ActionEnum.ORACLE;
            case 6 -> type = ActionEnum.TOWER;
            case 7 -> type = ActionEnum.TAVERN;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public BufferedImage getFront(){
        return front;
    }
    public BufferedImage getProcess(){
        return process;
    }
    //method to return number of action tiles of that type the player has
    public int getNumTiles(){
        return numTiles;
    }
    //method to check if the current action tile is usable
    public boolean isCanUse(){
        return canUse;
    }
    public ActionEnum getType(){
        return type;
    }
    public void setNumTiles(int num){
        numTiles = num;
    }
}
