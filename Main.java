
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


        for(int i = 0; i < 5; i++) {        
            while(!mouse.atCenter()) {
                Thread.sleep(100);
                maze.dijikstraAlgorithm();
                int[][] dist = maze.getOptimal();

                if(!(mouse.getYLocation() == 0) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation() - 1][mouse.getXLocation()] && maze.canGoUp(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveUp();
                }
                else if(!(mouse.getXLocation() == 15) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation()][mouse.getXLocation() + 1]  && maze.canGoRight(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveRight();
                }
                else if((mouse.getYLocation() != 15) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation() + 1][mouse.getXLocation()]  && maze.canGoDown(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveDown();
                }
                else if(!(mouse.getXLocation() == 0) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation()][mouse.getXLocation() - 1]  && maze.canGoLeft(mouse.getXLocation(), mouse.getYLocation())){
                    mouse.moveLeft();
                }
                window.repaint();
            }

            while(!mouse.atLocation(0, 15)) {
                Thread.sleep(100);
                maze.dijikstraAlgorithm(0, 15);
                int[][] dist = maze.getOptimal();

                if(!(mouse.getYLocation() == 0) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation() - 1][mouse.getXLocation()] && maze.canGoUp(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveUp();
                }
                else if(!(mouse.getXLocation() == 15) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation()][mouse.getXLocation() + 1]  && maze.canGoRight(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveRight();
                }
                else if((mouse.getYLocation() != 15) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation() + 1][mouse.getXLocation()]  && maze.canGoDown(mouse.getXLocation(), mouse.getYLocation())) {
                    mouse.moveDown();
                }
                else if(!(mouse.getXLocation() == 0) && dist[mouse.getYLocation()][mouse.getXLocation()] > dist[mouse.getYLocation()][mouse.getXLocation() - 1]  && maze.canGoLeft(mouse.getXLocation(), mouse.getYLocation())){
                    mouse.moveLeft();
                }
                window.repaint();
            }

        }
    }
    
    

}
