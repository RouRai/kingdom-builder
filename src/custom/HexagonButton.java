package custom;
import logic.cards.TerrainCard;
import logic.constantFolder.TerrainEnum;
import logic.placeables.Settlement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class HexagonButton extends JButton {
    private int n;
    private int x[];
    private int y[];
    private double angle;
    private boolean draw;
    private Polygon polygon;
    private Color color;
    private BufferedImage settlement;
    private Enum tileType;
    private int quadNum, row, col;
    private Boolean[] adjacents;
    public Boolean canClick;
    private Settlement settle;
    public HexagonButton(){
        super();
        canClick = true;
        settlement = null;
        settle = null;
        n = 6;
        x = new int[n];
        y = new int [n];
        angle = 2*Math.PI/n;
        draw = false;
        //border = BorderFactory.createLineBorder(new Color(90, 219, 181), 10);
        adjacents = new Boolean[6];
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    public HexagonButton(int q, int r, int c, Enum type){
        this();
        quadNum = q;
        row = r;
        col = c;
        tileType = type;
        //System.out.println(type);
    }

    public void paintComponent(Graphics g) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        //this.setAlignmentX();
        //g.setColor(color);
        int x0 = getSize().width/2;
        int y0 = getSize().height/2;
        for(int i=0; i<n; i++) {
            double v = i*angle;
            x[i] = x0 + (int)Math.round((getWidth()/2)*Math.cos(v + Math.PI/2));
            y[i ] = y0 + (int)Math.round((getHeight()/2)*Math.sin(v + Math.PI/2));
        }
        //g.drawPolygon(x, y, n);
        //super.paintComponent(g);
    }
    public boolean contains(int x1, int y1) {
        if (polygon == null || !polygon.getBounds().equals(getBounds())) {
            int x0 = getSize().width/2;
            int y0 = getSize().height/2;
            for(int i=0; i<n; i++) {
                double v = i*angle;
                x[i] = x0 + (int)Math.round((getWidth()/2)*Math.cos(v + Math.PI/2));
                y[i] = y0 + (int)Math.round((getHeight()/2)*Math.sin(v + Math.PI/2));
            }
            polygon = new Polygon(x,y,n);
        }
        return polygon.contains(x1, y1);
    }

    public void setColor(Color c){
        color = c;
    }
    public void drawHighlight(Graphics2D g, BufferedImage highlight, TerrainCard currentTerrain){
        if (tileType==null || tileType.equals(TerrainEnum.CITY)|| settlement != null || tileType.equals(TerrainEnum.MOUNTAIN) || !tileType.equals(currentTerrain.type())) {
            return;
        }
        else
            g.drawImage(highlight, this.getX() - 40, this.getY() - 40, 120, 133, null);
    }
    public void drawSettlement(Graphics2D g){
        if(settlement != null) {
            g.drawImage(settlement, this.getX() + 8, this.getY() + 10, 30, 20, null);
            //System.out.println("Drew settlement");
        }
    }
    public BufferedImage getSettlementImage(){
        return settlement;
    }
    public Enum getTileType(){
        return tileType;
    }
    public void setSettlementImage(BufferedImage settle){
        settlement = settle;
    }
    public void setSettlement(Settlement settle){
        this.settle = settle;
    }
    public void setBoardLocation(int quad, int r, int c){
        quadNum = quad;
        row = r;
        col = c;
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

    public void setAdjacents(Boolean[] adjacents) {
        this.adjacents = adjacents;
    }

    public String toString (){
        return "Hex button Clicked - quad"+ this.quadNum + " ("+ this.row+ ", "+ this.col+ ") ";
    }
}
