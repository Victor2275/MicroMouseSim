import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame{
    
    private MyPanel panel;
    
    MyFrame(Maze maze, Mouse mouse) {
        panel = new MyPanel(maze, mouse);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

}
