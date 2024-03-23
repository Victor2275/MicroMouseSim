import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import javax.swing.JPanel;

public class MyPanel extends JPanel{
    private Mouse mouse;
    
    MyPanel() {
        mouse = new Mouse();
        this.setPreferredSize(new Dimension(820, 820));
    }
    
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        
        
        
        g2D.setPaint(Color.blue);
        g2D.setStroke(new BasicStroke(3));
        
        final int size = 16;
        Square[][] maze = new Square[size][size];
        boolean[] line1 = new boolean[size];
        boolean[] line2 = new boolean[size + 1];
        boolean[] line3 = new boolean[size];

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
                createSquare(i, j, maze[i][j], g);
            }
        }
        g2D.setStroke(new BasicStroke(0));
        g2D.setPaint(new Color(250, 200, 50, 127));
        g2D.fillRect(360, 360, 100, 100);
        g2D.setStroke(new BasicStroke(5));
        g2D.setPaint(Color.blue);

        double wait = System.currentTimeMillis();
        mouse.display(g2D);
        while(wait + 1000 > System.currentTimeMillis()){
            
        }
        mouse.moveUp();




    
        






    }
 


    public void createSquare(int y, int x, Square square, Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.red);
        if(square.getWallDown()) {
            g2D.drawLine(50*x + 10, 50*y + 60, 50*x + 60, 50*y + 60);
        }
        if(square.getWallUp()) {
            g2D.drawLine(50*x + 10, 50*y + 10, 50*x + 60, 50*y + 10);
        }
        if(square.getWallLeft()) {
            g2D.drawLine(50*x + 10, 50*y + 10, 50*x + 10, 50*y + 60);
        }
        if(square.getWallRight()) {
            g2D.drawLine(50*x + 60, 50*y + 10, 50*x + 60, 50*y + 60);
        }

    }
}
