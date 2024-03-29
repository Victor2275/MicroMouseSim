import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

public class Maze {
    private final int size = 16;
    private Square[][] mazeActual;
    private Square[][] mazeFound;
    private final int scaleFactor = 1;

    public Maze() {
                
        final int size = 16;
        mazeActual = new Square[size][size];
        boolean[] line1 = new boolean[size];
        boolean[] line2 = new boolean[size + 1];
        boolean[] line3 = new boolean[size];

        try {
            FileReader fr = new FileReader("Maze.txt");
            BufferedReader br = new BufferedReader(fr);


            String str;
            str = br.readLine();
            int currentLine = 1;
            int mazeActualLine = 0;
 


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
                        if(str.substring(i-1,i).equals("|")) {
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
                        mazeActual[mazeActualLine][i] = new Square(line1[i], line3[i], line2[i], line2[i+1]);
                        line1[i] = line3[i];
                    }
                    mazeActualLine++;
                }
                str = br.readLine(); 
                currentLine++;
            }
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }


        mazeFound = new Square[size][size];
        for(int i = 0; i < mazeFound.length; i++) {
            for(int j = 0; j < mazeFound[i].length; j++) {
                mazeFound[i][j] = new Square();
            }
        }

    }

    public void drawMaze(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        for(int i = 0; i < mazeActual.length; i++) {
            for(int j = 0; j < mazeActual[i].length; j++) {
                createSquare(i, j, mazeActual[i][j], g2D, new Color(255,0,0));
                createSquare(i, j, mazeFound[i][j], g2D, new Color(0,255,0));
            }
        }
        g2D.setStroke(new BasicStroke(0));
        g2D.setPaint(new Color(250, 200, 50, 127));
        g2D.fillRect(360/scaleFactor, 360/scaleFactor, 100/scaleFactor, 100/scaleFactor);
        g2D.setStroke(new BasicStroke(5));
        g2D.setPaint(Color.blue);
    }

    public void createSquare(int y, int x, Square square, Graphics g, Color color) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        if(square.getWallDown()) {
            g2D.drawLine((50*x + 10)/scaleFactor, (50*y + 60)/scaleFactor, (50*x + 60)/scaleFactor, (50*y + 60)/scaleFactor);
        }
        if(square.getWallUp()) {
            g2D.drawLine((50*x + 10)/scaleFactor, (50*y + 10)/scaleFactor, (50*x + 60)/scaleFactor, (50*y + 10)/scaleFactor);
        }
        if(square.getWallLeft()) {
            g2D.drawLine((50*x + 10)/scaleFactor, (50*y + 10)/scaleFactor, (50*x + 10)/scaleFactor, (50*y + 60)/scaleFactor);
        }
        if(square.getWallRight()) {
            g2D.drawLine((50*x + 60)/scaleFactor, (50*y + 10)/scaleFactor, (50*x + 60)/scaleFactor, (50*y + 60)/scaleFactor);
        }
    }

    public void checkSquare(int x, int y) {
        mazeFound[y][x] = mazeActual[y][x];
        if(x < size-1) {mazeFound[y][x+1].setWallLeft(mazeActual[y][x+1].getWallLeft());}
        if(x > 0) {mazeFound[y][x-1].setWallRight(mazeActual[y][x-1].getWallRight());}
        if(y < size-1) {mazeFound[y+1][x].setWallUp(mazeActual[y+1][x].getWallUp());}
        if(y > 0) {mazeFound[y-1][x].setWallDown(mazeActual[y-1][x].getWallDown());}
        
        //if (x > 0) { mazeFound[y][x-1].setWallRight(mazeActual[y][x-1].getWallRight()); }
        //if (x < size-1) { mazeFound[y][x+1].setWallLeft(mazeActual[y][x+1].getWallLeft()); }
        //if (y > 0) { mazeFound[y-1][x].setWallUp(mazeActual[y-1][x].getWallUp()); }
        //if (y < size-1) { mazeFound[y+1][x].setWallDown(mazeActual[y+1][x].getWallDown()); }
    }   
}
