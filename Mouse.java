import java.awt.*;
import java.io.BufferedReader;


public class Mouse {
    private int xLocation;
    private int yLocation;
    

    private final int scaleFactor = 1;
    private Maze maze;

        
    public Mouse(Maze m) {
        xLocation = 0;
        yLocation = 0;
        maze = m;


    }

    public Mouse(int x, int y, Maze m) {
        xLocation = x;
        yLocation = y;
        maze = m;

    }


    public int getXLocation() {
        return xLocation;
    }


    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }


    public int getYLocation() {
        return yLocation;
    }


    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    

    public void moveUp() {
        yLocation--;
        maze.checkSquare(xLocation, yLocation);
    }

    public void moveDown() {
        yLocation++;
        maze.checkSquare(xLocation, yLocation);
    }

    public void moveLeft() {
        xLocation--;
        maze.checkSquare(xLocation, yLocation);
    }

    public void moveRight() {
        xLocation ++;
        maze.checkSquare(xLocation, yLocation);
    }


    public void display(Graphics g) {
        g.fillOval(((xLocation * 50) + 25)/scaleFactor, ((yLocation * 50) + 25)/scaleFactor, 20/scaleFactor, 20/scaleFactor);
    }

}
