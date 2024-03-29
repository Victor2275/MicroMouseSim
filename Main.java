

import java.io.*;
import static java.lang.System.*;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.*;

public class Main {
    
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.checkSquare(2, 4);
        MyFrame window = new MyFrame(maze);
        MyPanel panel = new MyPanel(maze);
        

    }
}
