package custom;

import logic.cards.TerrainCard;
import logic.tiles.ActionTile;
import logic.tiles.TerrainTile;
import logic.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HexagonButton extends JButton {
    private final int n;
    private final int[] x;
    private final int[] y;
    private final double angle;
    private Polygon polygon;
    private BufferedImage settlement;
    private TerrainTile tileType;
    private ActionTile actiontileType;
    private int quadNum, row, col;
    public Boolean canClick;
    public HexagonButton(){
        super();
        canClick = true;
        settlement = null;
        n = 6;
        x = new int[n];
        y = new int [n];
        angle = 2*Math.PI/n;
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    public HexagonButton(int q, int r, int c, TerrainTile type){
        this();
        quadNum = q;
        row = r;
        col = c;
        tileType = type;
        //System.out.println(type);
    }
    public HexagonButton(int q, int r, int c, ActionTile type){
        this();
        quadNum = q;
        row = r;
        col = c;
        actiontileType = type;
        //System.out.println(type);
    }

    public void paintComponent(Graphics g) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        int x0 = getSize().width/2;
        int y0 = getSize().height/2;
        for(int i = 0; i < n; i++) {
            double v = i*angle;
            x[i] = x0 + (int)Math.round((getWidth()/2.0)*Math.cos(v + Math.PI/2));
            y[i] = y0 + (int)Math.round((getHeight()/2.0)*Math.sin(v + Math.PI/2));
        }
    }
    public boolean contains(int x1, int y1) {
        if (polygon == null || !polygon.getBounds().equals(getBounds())) {
            int x0 = getSize().width/2;
            int y0 = getSize().height/2;
            for(int i=0; i<n; i++) {
                double v = i*angle;
                x[i] = x0 + (int)Math.round((getWidth()/2.0)*Math.cos(v + Math.PI/2));
                y[i] = y0 + (int)Math.round((getHeight()/2.0)*Math.sin(v + Math.PI/2));
            }
            polygon = new Polygon(x,y,n);
        }
        return polygon.contains(x1, y1);
    }

    public void drawHighlight(Graphics2D g, BufferedImage highlight){
        g.drawImage(highlight, this.getX() - 40, this.getY() - 40, 120, 133, null);
    }
    public void drawSettlement(Graphics2D g){
        if(settlement != null) {
            g.drawImage(settlement, this.getX() + 8, this.getY() + 10, 30, 20, null);
            //System.out.println("Drew settlement");
        }
    }

    public Tile<?> getTileType(){
        return tileType;
    }
    public void setSettlementImage(BufferedImage settle){
        settlement = settle;
    }

    public int getquadNum(){
        return quadNum;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getTrueRow(){
        if(quadNum == 2 || quadNum == 3){
            return 10 + row;
        }
        return row;
    }
    public int getTrueCol(){
        if(quadNum == 1 || quadNum == 3){
            return 10 + col;
        }
        return col;
    }
    public BufferedImage getSettlement(){
        return settlement;
    }
    public String toString (){
        return "Hex button Clicked - quad"+ this.quadNum + " ("+ this.row+ ", "+ this.col+ ") ";
    }

    public ActionTile getActiontileType() {
        return actiontileType;
    }

}
