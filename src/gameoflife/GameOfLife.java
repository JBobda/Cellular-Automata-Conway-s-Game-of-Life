package gameoflife;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameOfLife implements Runnable{
    //Static game info
    private static final String TITLE = "Conway's Game of Life";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int CELLWIDTH = 10;
    private static final int CELLHEIGHT = 10;
    
    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    
    private Cell[][] gameBoard;
    private Thread thread;
    
    private boolean running;
    
    public GameOfLife(){
        display = new Display(WIDTH, HEIGHT, TITLE);
        gameBoard = new Cell[WIDTH/CELLWIDTH][HEIGHT/CELLHEIGHT];
    }
    
    @Override
    public void run(){
        init();
        
        while(running){
            update();
            render();
        }
        
        stop();
    }
    
    public void update(){
        
    }
    
    public void render(){
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        graphics = bufferStrategy.getDrawGraphics();
        //RENDER HERE
        for (Cell[] gameBoard1 : gameBoard) {
            for (Cell gameBoard11 : gameBoard1) {
                gameBoard11.render(graphics, CELLWIDTH, CELLHEIGHT);
            }
        }
        //STOP RENDERING HERE
        graphics.dispose();
        bufferStrategy.show();
        
    }
    
    public synchronized void start(){
        if(!running) running = true;
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
                Status stat;
                int choice = (int)(Math.random() * 2);
                if(choice == 1)
                    stat = Status.DEAD;
                else
                    stat = Status.ALIVE;
                
                gameBoard[i][j] = new Cell(i, j, stat);
            }
        }
    }
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.start();
    }

}
