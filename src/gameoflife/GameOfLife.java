package gameoflife;

public class GameOfLife {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final String TITLE = "Conway's Game of Life";
    
    private Display display;
    
    public GameOfLife(){
        display = new Display(WIDTH, HEIGHT, TITLE);
    }
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        
    }

}
