

import java.io.*;
import static java.lang.System.*;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {

        Maze maze = new Maze();
        Mouse mouse = new Mouse(0,15,maze);
        MyFrame window = new MyFrame(maze, mouse);
        Boolean[][] empty = new Boolean[16][16];
        for(int i = 0; i < empty.length; i++) {
            for(int j = 0; j < empty[i].length; j++) {
                empty[i][j] = false;
            }
        }


        
        while(!mouse.atCenter()) {
            //Thread.sleep(1000);
            
            int distance = maze.distanceFromCenter(mouse.getXLocation(), mouse.getYLocation(), 0, empty, 20);
            System.out.println(distance);
            if(maze.getDirection() == 1) {
                mouse.moveUp();
            }
            if(maze.getDirection() == 2) {
                mouse.moveRight();
            }
            if(maze.getDirection() == 3) {
                mouse.moveDown();
            }
            else if(maze.getDirection() == 4){
                mouse.moveLeft();
            }
            window.repaint();
        }

        /*for(int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            mouse.moveDown();
            window.repaint();
        }
        */
    }
    

}
