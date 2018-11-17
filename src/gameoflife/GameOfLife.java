package gameoflife;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameOfLife implements Runnable{
    //Static game info
    private static final String TITLE = "Conway's Game of Life";
    private static final int WIDTH = 750;
    private static final int HEIGHT = WIDTH*9/16 ;
    private static final int CELLWIDTH = 15;
    private static final int CELLHEIGHT = 15;
    
    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    
    private Cell[][] gameBoard;
    private Thread thread;
    
    private boolean running;
    
    public GameOfLife(){
        display = new Display(WIDTH, HEIGHT, TITLE);
        gameBoard = new Cell[HEIGHT/CELLHEIGHT][WIDTH/CELLWIDTH];
    }
    
    @Override
    public void run(){
        init();
        render();
        while(running){
            update();
            
            render();
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        stop();
    }
    
    public void update(){
        Cell[][] tempBoard = cloneBoard(gameBoard);

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                int aliveCount = 0;
                
                if((i != 0) && (j != 0) && (j < gameBoard[i].length-1) && (i < gameBoard.length-1)){
                    //Check the surrounding cells
                    if(gameBoard[i-1][j].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    
                    //Diagonals
                    
                    if(gameBoard[i-1][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i-1][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                       
                }
                
                if(gameBoard[i][j].getStatus() == Status.ALIVE){
                    //Death by isolation: Each live cell with one or fewer live neighbors will die in the next generation.
                    if(aliveCount < 2)
                        tempBoard[i][j].setStatus(Status.DEAD);
                    //Death by overcrowding: Each live cell with four or more live neighbors will die in the next generation.
                    else if(aliveCount > 3)
                        tempBoard[i][j].setStatus(Status.DEAD);
                    //Survival: Each live cell with either two or three live neighbors will remain alive for the next generation.
                    else if(aliveCount == 2 || aliveCount == 3)
                        tempBoard[i][j].setStatus(Status.ALIVE);
                }else if(gameBoard[i][j].getStatus() == Status.DEAD){
                    //Births: Each dead cell adjacent to exactly three live neighbors will become live in the next generation.
                    if(aliveCount == 3)
                        tempBoard[i][j].setStatus(Status.ALIVE);
                }
            }
        }
        gameBoard = cloneBoard(tempBoard);
    }
    
    public void render(){
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, WIDTH, HEIGHT);
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
        //Prepare canvas
        display.getCanvas().createBufferStrategy(3);
        bufferStrategy = display.getCanvas().getBufferStrategy();
        
        //Loading game board with the different cells
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                Status stat = Status.DEAD;                
                gameBoard[i][j] = new Cell(i, j, stat);
            }   
        }
        gameBoard[2][2].setStatus(Status.ALIVE);
        gameBoard[2][3].setStatus(Status.ALIVE);
        gameBoard[2][4].setStatus(Status.ALIVE);
        //gameBoard[3][2].setStatus(Status.ALIVE);
        
    }
    
    private Cell[][] cloneBoard(Cell[][] old){
        Cell[][] copy = new Cell[old.length][old[0].length];
        
        for (int i = 0; i < old.length; i++) {
            for (int j = 0; j < old[i].length; j++) {
                copy[i][j] = new Cell(i, j, old[i][j].getStatus());
            }
        }  
        return copy;
    }
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.start();
    }

}
