import java.awt.*;
import java.io.BufferedReader;


public class Mouse {
    private int xLocation;
    private int yLocation;
    private final int scaleFactor = 1;

    /*
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

 */
        
    public Mouse() {
        xLocation = 0;
        yLocation = 0;

    }


    public Mouse(int x, int y) {
        xLocation = x;
        yLocation = y;

    }

    public void moveUp() {
        yLocation--;
    }

    public void moveDown() {
        yLocation++;
    }

    public void moveLeft() {
        xLocation--;
    }

    public void moveRight() {
        xLocation ++;
    }

    public void display(Graphics g) {
        g.fillOval(((xLocation * 50) + 25)/scaleFactor, ((yLocation * 50) + 25)/scaleFactor, 20/scaleFactor, 20/scaleFactor);
    }

}
