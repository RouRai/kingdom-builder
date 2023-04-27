package custom;

import logic.constantFolder.ActionEnum;
import logic.constantFolder.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

public class ActionProcessButton extends HexagonButton{
    private BufferedImage front, process;
    private int numUses;
    private int numTiles;
    private ActionEnum type;
    public ActionProcessButton(int boardNum){
        super();
        numTiles = 0;
        numUses = 0;
        setUP(boardNum);
    }
    private void setUP(int symbol){
        setFront(symbol);
        setProcess(symbol);
        setType(symbol);
    }
    private void setFront(int symbol){
        front = Constants.getActionTiles()[symbol%8];
    }
    private void setProcess(int symbol){
        process = Constants.getActionProcess()[symbol%8];
    }
    private void setType(int symbol){
        switch(symbol % 8){
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
    public boolean canUse(){
        if(numUses < numTiles){
            return true;
        }
        return false;
    }
    public ActionEnum getType(){
        return type;
    }
    public void setNumTiles(int num){
        numTiles = num;
    }
    public void resetNumUses(){
        numUses = numTiles;
    }
}
