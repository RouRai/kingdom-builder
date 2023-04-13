package custom;
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
    private int quadNum, row, col;
    public HexagonButton(){
        super();
        settlement = null;
        n = 6;
        x = new int[n];
        y = new int [n];
        angle = 2*Math.PI/n;
        draw = false;
        //border = BorderFactory.createLineBorder(new Color(90, 219, 181), 10);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    public HexagonButton(int q, int r, int c){
        this();
        quadNum = q;
        row = r;
        col = c;
    }

    public void paintComponent(Graphics g) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        //this.setAlignmentX();
        g.setColor(color);
        int x0 = getSize().width/2;
        int y0 = getSize().height/2;
        for(int i=0; i<n; i++) {
            double v = i*angle;
            x[i] = x0 + (int)Math.round((getWidth()/2)*Math.cos(v + Math.PI/2));
            y[i ] = y0 + (int)Math.round((getHeight()/2)*Math.sin(v + Math.PI/2));
        }
        g.drawPolygon(x, y, n);
        super.paintComponent(g);
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
    public void setChangeVisible(boolean b){
        draw = b;
    }

    public void setColor(Color c){
        color = c;
    }
    public void drawHighlight(Graphics2D g, BufferedImage highlight){
        g.drawImage(highlight, this.getX() - 40, this.getY() - 35, 120, 120, null);
    }
    public void drawSettlement(Graphics2D g){
        if(settlement != null) {
            g.drawImage(settlement, this.getX() + 8, this.getY() + 10, 30, 20, null);
            System.out.println("Drew settlement");
        }
    }
    public BufferedImage getSettlement(){
        return settlement;
    }
    public void setSettlement(BufferedImage settle){
        settlement = settle;
    }
    public void setBoardLocation(int quad, int r, int c){
        quadNum = quad;
        row = r;
        col = c;
    }
    public String toString (){
        return "Hex button Clicked - quad"+ this.quadNum + " ("+ this.row+ ", "+ col+ ") ";
    }
}
