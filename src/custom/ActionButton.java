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
    private ActionEnum type;
    public ActionButton(int boardNum){
        super();
        setUP(boardNum);
    }
    private void setUP(int symbol){
        setFront(symbol);
        setProcess(symbol);
        setType(symbol);
    }
    private void setFront(int symbol){
        switch(symbol){
            case 0 -> front = Constants.getActionTiles()[3];
            case 1 -> front = Constants.getActionTiles()[2];
            case 2 -> front = Constants.getActionTiles()[1];
            case 3 -> front = Constants.getActionTiles()[5];
            case 4 -> front = Constants.getActionTiles()[0];
            case 5 -> front = Constants.getActionTiles()[4];
            case 6 -> front = Constants.getActionTiles()[7];
            case 7 -> front = Constants.getActionTiles()[6];
        }
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
}
