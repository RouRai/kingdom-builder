package panels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HexagonButton extends JButton {
    private int n;
    private int x[];
    private int y[];
    private double angle;
    private Polygon polygon;
    private Color c;
    public HexagonButton(){
        super();
        n = 6;
        x = new int[n];
        y = new int [n];
        angle = 2*Math.PI/n;
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    public void paintComponent(Graphics g) {
        if (c == null) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(c);
        }
        int x0 = getSize().width/2;
        int y0 = getSize().height/2;
        for(int i=0; i<n; i++) {
            double v = i*angle;
            x[i] = x0 + (int)Math.round((getWidth()/2)*Math.cos(v + Math.PI/2));
            y[i ] = y0 + (int)Math.round((getHeight()/2)*Math.sin(v + Math.PI/2));
        }
        g.fillPolygon(x, y, n);

        super.paintComponent(g);
    }

    public void paintBorder(Graphics g) {
        g.setColor(c);
        int x0 = getSize().width/2;
        int y0 = getSize().height/2;
        for(int i=0; i<n; i++) {
            double v = i*angle;
            x[i] = x0 + (int)Math.round((getWidth()/2)*Math.cos(v + Math.PI/2));
            y[i] = y0 + (int)Math.round((getHeight()/2)*Math.sin(v + Math.PI/2));
        }
        g.drawPolygon(x, y, n);
    }

    public boolean contains(int x1, int y1) {
        if (polygon == null ||
                !polygon.getBounds().equals(getBounds())) {
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
    //this method sets the color of the Hexagon
    public void setColor(Color col){
        c = col;
    }
}
