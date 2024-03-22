import java.awt.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
    MyPanel() {
        this.setPreferredSize(new Dimension(500, 500));
    }
    
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setPaint(Color.blue);
        g2D.setStroke(new BasicStroke(1));
        //g2D.drawLine(0, 0, 500, 500);
        

    }

    public void createSquare(int x, int y, Square square, Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.red);
        if(square.getWallUp()) {
            g2D.drawLine(50*x, 50*y + 50, 50*x + 50, 50*y + 50);
        }
        if(square.getWallDown()) {
            g2D.drawLine(50*x, 50*y, 50*x + 50, 50*y);
        }
        if(square.getWallLeft()) {
            g2D.drawLine(50*x, 50*y, 50*x, 50*y + 50);
        }
        if(square.getWallRight()) {
            g2D.drawLine(50*x + 50, 50*y, 50*x + 50, 50*y + 50);
        }

    }
}
