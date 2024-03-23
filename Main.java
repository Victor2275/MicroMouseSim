

import java.io.*;
import static java.lang.System.*;

import java.awt.Graphics;
import java.awt.Image;

public class Main {
    public static void main(String[] args) {
        final int size = 16;
        Square[][] maze = new Square[size][size];
        boolean[] line1 = new boolean[size];
        boolean[] line2 = new boolean[size + 1];
        boolean[] line3 = new boolean[size];

        MyFrame window = new MyFrame();
        MyPanel panel = new MyPanel();
        Graphics g = window.getGraphics();
        
        
            

/*  
        try {
            FileReader fr = new FileReader("Maze.txt");
            BufferedReader br = new BufferedReader(fr);


            String str;
            str = br.readLine();
            int currentLine = 1;
            int mazeLine = 0;
 


            while ((str != null)) {
                int count = 0;
                for(int i = 1; i < str.length(); i+=4) {

                    if(currentLine == 1) {
                        if(str.substring(i,i+3).equals("---")) {
                            line1[count] = true;
                        }
                        else{
                            line1[count] = false;
                        }
                    }

                    else if(currentLine%2 != 0) {
                        if(str.substring(i,i+3).equals("---")) {
                            line3[count] = true;
                        }
                        else{
                            line3[count] = false;
                        }
                    }

                    else if(currentLine%2 == 0) {
                        if(str.substring(i-1,i) == "|") {
                            line2[count] = true;
                        }
                        else {
                            line2[count] = false;
                        }
                    }

                    count++;
                }

                if(currentLine%2 == 0) {
                    line2[size] = true;
                }

                if(currentLine % 2 != 0 && currentLine != 1) {
                    for(int i = 0; i < size; i++){
                        maze[mazeLine][i] = new Square(line1[i], line3[i], line2[i], line2[i+1]);
                        line1[i] = line3[i];
                    }
                    mazeLine++;
                }





                str = br.readLine(); 
                currentLine++;
            }
        

        } catch (IOException e) {

            e.printStackTrace();
        }

        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                window.squareDraw(i, j, maze[i][j], g);
            }
        }
        





    }
*/
    }
}
