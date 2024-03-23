import java.awt.*;
import java.io.BufferedReader;


public class Mouse {
    private int xLocation;
    private int yLocation;

    
    public Mouse() {
        xLocation = 35;
        yLocation = 785;

    }


    public Mouse(int x, int y) {
        xLocation = x;
        yLocation = y;

    }

    public void moveUp() {
        yLocation -= 50;
    }

    public void moveDown() {
        yLocation += 50;
    }

    public void moveLeft() {
        xLocation -= 50;
    }

    public void moveRight() {
        xLocation += 50;
    }

    public void display(Graphics g) {
        g.fillOval(xLocation - 10, yLocation - 10, 20, 20);
    }

}
