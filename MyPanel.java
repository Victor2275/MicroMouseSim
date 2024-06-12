import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import javax.swing.JPanel;

public class MyPanel extends JPanel{
    private Mouse mouse;
    private Maze maze;
    private final int scaleFactor = 1;
    
    MyPanel(Maze maze, Mouse mouse) {
        this.maze = maze;
        this.mouse = mouse;
        this.setPreferredSize(new Dimension(420/scaleFactor, 420/scaleFactor));
    }
    
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setStroke(new BasicStroke(3));
        maze.drawMaze(g2D);
        mouse.display(g2D);

    }
}
