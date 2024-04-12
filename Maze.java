import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

public class Maze {
    private final int size = 16;
    private Square[][] mazeActual;
    private Square[][] mazeFound;
    private int[][] distance;
    private int[][] optimalPath;
    private final int scaleFactor = 2;
    private int direction;


    public Maze() {
                
        final int size = 16;

        optimalPath = new int[size][size];

        for(int i = 0; i < optimalPath.length; i++) {
            for(int j = 0; j < optimalPath[i].length; j++) {
                optimalPath[i][j] = 300;
            }
        }
        
        
        mazeActual = new Square[size][size];



        boolean[] line1 = new boolean[size];
        boolean[] line2 = new boolean[size + 1];
        boolean[] line3 = new boolean[size];
        direction = -1;

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
                boolean up = false;
                boolean down = false;
                boolean left = false;
                boolean right = false;
                if(i == 0) {
                    up = true;
                }
                if(i == mazeFound.length-1) {
                    down = true;
                }
                if(j == 0) {
                    left = true;
                }
                if(j == mazeFound.length-1) {
                    right = true;
                }
                mazeFound[i][j] = new Square(up,down,left,right);
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
    }  
    
    
    public boolean canGoUp(int x, int y) {
        return !mazeFound[y][x].getWallUp();
    }
    public boolean canGoDown(int x, int y) {
        return !mazeFound[y][x].getWallDown();
    }
    public boolean canGoLeft(int x, int y) {
        return !mazeFound[y][x].getWallLeft();
    }
    public boolean canGoRight(int x, int y) {
        return !mazeFound[y][x].getWallRight();
    }




    public int distanceFromCenter(int x, int y, int startingLength, Boolean[][] check, int value) {
        boolean checkUp = false;
        boolean checkDown = false;
        boolean checkRight = false;
        boolean checkLeft = false;
        
        

        int temp;

        Boolean[][] currentCheck = new Boolean[check.length][check.length];

        for(int i = 0; i < check.length; i++) {
            for(int j = 0; j < check[i].length; j++) {
                currentCheck[i][j] = check[i][j];
            }
        }


        if(x < 0 || x > size-1 || y < 0 || y > size-1) {
            return Integer.MAX_VALUE;
        }
        
        if((x == size/2 - 1 || x == size/2) && (y == size/2 - 1 || y == size/2)) {
            return startingLength;
        }

        if(check[y][x]) {
            return Integer.MAX_VALUE;
        }


        currentCheck[y][x] = true;

        if(startingLength > value) {
            return Integer.MAX_VALUE;
        }

        if(x <= 7) {
            checkRight = true;
            if(!mazeFound[y][x].getWallRight()) {
                temp = distanceFromCenter(x + 1, y, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 2;
                }
            }
        }
        else {
            checkLeft = true;
            if(!mazeFound[y][x].getWallLeft()) {
                temp = distanceFromCenter(x - 1, y, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 4;
                }
            }
        }

        if(y >= 7) {
            checkUp = true;
            if(!mazeFound[y][x].getWallUp()) {
                temp = distanceFromCenter(x, y - 1, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 1;
                }
            }
        }
        else {
            checkDown = true;
            if(!mazeFound[y][x].getWallDown()) {
                temp = distanceFromCenter(x, y + 1, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 3;
                }
            }
        }

        if(checkRight) {
            checkLeft = true;
            if(!mazeFound[y][x].getWallLeft()) {
                temp = distanceFromCenter(x - 1, y, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 4;
                }
            }
        }
        else {
            checkRight = true;
            if(!mazeFound[y][x].getWallRight()) {
                temp = distanceFromCenter(x + 1, y, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 2;
                }
            }
        }

        if(checkUp) {
            checkDown = true;
            if(!mazeFound[y][x].getWallDown()) {
                temp = distanceFromCenter(x, y + 1, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 3;
                }
            }  
        }
        else {
            checkUp = true;
            if(!mazeFound[y][x].getWallUp()) {
                temp = distanceFromCenter(x, y - 1, startingLength + 1, currentCheck, value);
                if(temp < value) {
                    value = temp;
                    direction = 1;
                }
            }
        }
        return value;
    }

    public void resetOptimalGoal(int goalX, int goalY){
        for(int i = 0; i < optimalPath.length; i++) {
            for(int j = 0; j < optimalPath[i].length; j++) {
                optimalPath[i][j] = 300;
            }
        }

        optimalPath[goalY][goalX] = 0;

    }

    public void resetOptimalCenter(){
        for(int i = 0; i < optimalPath.length; i++) {
            for(int j = 0; j < optimalPath[i].length; j++) {
                optimalPath[i][j] = 300;
            }
        }

        optimalPath[7][7] = 0;
        optimalPath[7][8] = 0;
        optimalPath[8][7] = 0;
        optimalPath[8][8] = 0;
    }


    public void dijikstraAlgorithm() {
        resetOptimalCenter();



        for(int i = 0; i < 255; i++) {
            boolean complete = true;
            for(int y = 0; y < optimalPath.length; y++) {
                for(int x = 0; x < optimalPath[y].length; x++) {
                    if(optimalPath[y][x] == i) {
                        if (!mazeFound[y][x].getWallUp() && optimalPath[y - 1][x] > i) {
                            optimalPath[y - 1][x] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallDown() && optimalPath[y + 1][x] > i) {
                            optimalPath[y + 1][x] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallLeft() && optimalPath[y][x - 1] > i) {
                            optimalPath[y][x - 1] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallRight() && optimalPath[y][x + 1] > i) {
                            optimalPath[y][x + 1] = i + 1;
                            complete = false;
                        }
                    }	
                }
            }
           if(complete) {
                break;
            }
            
        }
    }


    public void dijikstraAlgorithm(int goalX, int goalY) {
        resetOptimalGoal(goalX, goalY);



        for(int i = 0; i < 255; i++) {
            boolean complete = true;
            for(int y = 0; y < optimalPath.length; y++) {
                for(int x = 0; x < optimalPath[y].length; x++) {
                    if(optimalPath[y][x] == i) {
                        if (!mazeFound[y][x].getWallUp() && optimalPath[y - 1][x] > i) {
                            optimalPath[y - 1][x] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallDown() && optimalPath[y + 1][x] > i) {
                            optimalPath[y + 1][x] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallLeft() && optimalPath[y][x - 1] > i) {
                            optimalPath[y][x - 1] = i + 1;
                            complete = false;
                        }
                        if (!mazeFound[y][x].getWallRight() && optimalPath[y][x + 1] > i) {
                            optimalPath[y][x + 1] = i + 1;
                            complete = false;
                        }
                    }	
                }
            }
           if(complete) {
                break;
            }
            
        }
    }




    public int[][] getOptimal() {
        return optimalPath;
    }

    public int getSize() {
        return size;
    }

    public Square[][] getMazeActual() {
        return mazeActual;
    }

    public void setMazeActual(Square[][] mazeActual) {
        this.mazeActual = mazeActual;
    }

    public Square[][] getMazeFound() {
        return mazeFound;
    }

    public void setMazeFound(Square[][] mazeFound) {
        this.mazeFound = mazeFound;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
