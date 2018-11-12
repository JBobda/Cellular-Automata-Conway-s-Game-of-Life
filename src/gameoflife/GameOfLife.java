package gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOfLife implements Runnable{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final String TITLE = "Conway's Game of Life";
    
    private static final int CELLWIDTH = 10;
    private static final int CELLHEIGHT = 10;
    
    private Display display;
    private Cell[][] gameBoard;
    private Thread thread;
    
    private boolean running;
    
    public GameOfLife(){
        display = new Display(WIDTH, HEIGHT, TITLE);
        gameBoard = new Cell[WIDTH/CELLWIDTH][HEIGHT/CELLHEIGHT];
    }
    
    public void run(){
        init();
        
        while(running){
            display.render();
        }
        
        stop();
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void init(){
        //Loading game board with the different cells
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                gameBoard[i][j] = new Cell(i, j, Status.DEAD);
            }
        }
        
        //Set running to true
        running = true;
    }
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.start();
    }

}
